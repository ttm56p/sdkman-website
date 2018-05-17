package io.sdkman.site

import com.typesafe.scalalogging.LazyLogging
import ratpack.form.Form
import ratpack.handling.Context
import support.{Handler, OK, RatpackSugar}

class ContactFormHandler extends Handler with RatpackSugar with Email with LazyLogging with Configuration {

  override def handles(implicit ctx: Context): Unit = {
    ctx.parse(classOf[Form]).blockingOp { f =>
      val recaptchaResponse = f.get("g-recaptcha-response")
      val ipAddress = ctx.getRequest.getHeaders.get("X-Real-IP")
      logger.info(s"Recaptcha: $recaptchaResponse $ipAddress")

      recaptcha("secret", "response", "127.0.0.1") match {
        case Right(s) =>
          logger.info("Sending email...")
          send(f.get("email"), f.get("name"), f.get("message"))
        case Left(t) =>
          logger.warn(s"Bad actor detected: ${t.getMessage}")
      }
    } andThen { _ =>
      OK(html.index(recaptchaSiteKey))
    }
  }

  def recaptcha(secret: String, response: String, ipAddress: String): Either[Throwable, String] = Right("success")
}
