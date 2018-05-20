package io.sdkman.site

import com.typesafe.scalalogging.LazyLogging
import io.circe.generic.auto._
import io.circe.parser._
import ratpack.exec.Promise
import ratpack.handling.Context
import ratpack.http.client.{HttpClient, ReceivedResponse}

trait Recaptcha extends {

  self: Configuration with LazyLogging =>

  def recaptcha(request: RecaptchaRequest)(implicit ctx: Context): Promise[RecaptchaResponse] = {
    ctx.get(classOf[HttpClient]).post(recaptchaUrl, spec => spec.getBody.text(request.toString)).map[RecaptchaResponse] { response: ReceivedResponse =>
      decode[RecaptchaResponse](response.getBody.getText).fold(
        e => RecaptchaResponse(success = false, `error-codes` = Some(e.getMessage)),
        rr => rr)
    }
  }

  case class RecaptchaRequest(secret: String, response: String, remoteIp: String) {
    override def toString: String = s"secret=$secret&response=$response&remoteip=$remoteIp"
  }

  case class RecaptchaResponse(success: Boolean,
                               challenge_ts: Option[String] = None,
                               hostname: Option[String] = None,
                               `error-codes`: Option[String] = None)

}