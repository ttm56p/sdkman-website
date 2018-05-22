package io.sdkman.site

import java.net.URLEncoder.encode

import com.typesafe.scalalogging.LazyLogging
import ratpack.exec.Promise
import ratpack.handling.Context
import ratpack.http.client.HttpClient
import spray.json._

trait Recaptcha extends DefaultJsonProtocol {

  self: Configuration with LazyLogging =>

  case class RecaptchaRequest(secret: String, response: String, remoteIp: String) {
    def body = List(
      "secret" -> secret,
      "response" -> response,
      "remoteip" -> remoteIp)
      .map { case (k: String, v: String) => s"$k=${encode(v, "UTF-8")}" }
      .mkString("&")
  }

  case class RecaptchaResponse(success: Boolean,
                               challenge_ts: Option[String] = None,
                               hostname: Option[String] = None,
                               `error-codes`: Option[List[String]] = None)

  implicit val colorFormat = jsonFormat4(RecaptchaResponse)

  def recaptcha(request: RecaptchaRequest)(implicit ctx: Context): Promise[RecaptchaResponse] = {
    ctx.get(classOf[HttpClient]).post(recaptchaUrl, spec =>
      spec.headers(hs => hs.add("Content-Type", "application/x-www-form-urlenconded")).body(b => b.text(request.body)))
      .map[RecaptchaResponse] { response => response.getBody.getText.parseJson.convertTo[RecaptchaResponse]
    }
  }
}