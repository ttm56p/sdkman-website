package io.sdkman.site

import com.typesafe.scalalogging.LazyLogging
import ratpack.exec.Blocking
import ratpack.form.Form
import ratpack.handling.Context
import support.{Handler, OK, PromiseTransform, RatpackSugar}

class ContactFormHandler extends Handler
  with RatpackSugar
  with Email
  with Recaptcha
  with LazyLogging
  with Configuration
  with PromiseTransform {

  override def handles(implicit ctx: Context): Unit = {
    ctx.parse(classOf[Form]).blockingOp { f =>

      val email = f.get("email")
      val name = f.get("name")
      val message = f.get("message")
      logger.info(s"Received request: $message - ($name<$email>)")

      val recaptchaResponse = f.get("g-recaptcha-response")
      val remoteIpAddress = ctx.getRequest.getHeaders.get("X-Real-IP")
      logger.info(s"Recaptcha: $recaptchaResponse $remoteIpAddress")

      Blocking.on {
        val request = RecaptchaRequest(recaptchaSecret, recaptchaResponse, remoteIpAddress)
        recaptcha(request).blockingOp { recaptchaResponse =>
          if (recaptchaResponse.success)
            send(email, name, message) else send(email, name, s"$request (${request.body}) -> ${recaptchaResponse.toString}")
        }
      }
    } then (_ => OK(html.index(recaptchaSiteKey)))
  }
}
