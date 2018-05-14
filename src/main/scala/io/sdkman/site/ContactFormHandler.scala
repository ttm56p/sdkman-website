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

      send(f.get("email"), f.get("name"), f.get("message"))
    } andThen { _ =>
      OK(html.index(recaptchaSiteKey))
    }
  }
}
