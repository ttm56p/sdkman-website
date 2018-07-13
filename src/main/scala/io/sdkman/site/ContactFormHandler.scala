package io.sdkman.site

import com.typesafe.scalalogging.LazyLogging
import ratpack.exec.Blocking
import ratpack.form.Form
import ratpack.handling.Context
import support._

class ContactFormHandler extends Handler
  with RatpackSugar
  with Email
  with Recaptcha
  with LazyLogging
  with Configuration
  with PromiseTransform {

  override def handles(implicit ctx: Context): Unit = {
    ctx.parse(classOf[Form]).blockingOp { f =>

      val email = StringOption(f.get("email"))
      val name = StringOption(f.get("name"))
      val message = StringOption(f.get("message"))
      logger.info(s"Received request: $message - ($name<$email>)")

      val maybeRecaptchaResponse = StringOption(f.get("g-recaptcha-response"))
      val remoteIpAddress = ctx.getRequest.getHeaders.get("X-Real-IP")
      logger.info(s"Recaptcha: $maybeRecaptchaResponse $remoteIpAddress")

      if (recaptchaEnabled) {
        maybeRecaptchaResponse.fold(logger.warn(s"Rejecting request from $name<$email>, no recaptchaResponse found.")) { recaptchaResponse =>
          Blocking.on {
            val request = RecaptchaRequest(recaptchaSecret, recaptchaResponse, remoteIpAddress)
            recaptcha(request).blockingOp { recaptchaResponse =>
              if (recaptchaResponse.success)
                send(email, name, message)
              else
                logger.error(s"Recaptcha failed: ${request.body} -> ${recaptchaResponse.toString}")
            }
          }
        }
      } else send(email, name, message)

    } then (_ => OK(html.index(recaptchaEnabled, recaptchaSiteKey)))
  }
}
