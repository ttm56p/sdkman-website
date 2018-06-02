package io.sdkman.site

import java.net.URLEncoder.encode

import com.typesafe.scalalogging.LazyLogging
import ratpack.exec.Promise
import ratpack.handling.Context
import ratpack.http.client.HttpClient
import ratpack.http.internal.HttpHeaderConstants.CONTENT_TYPE
import spray.json._

trait Recaptcha extends DefaultJsonProtocol {

  self: Configuration with LazyLogging =>

  case class RecaptchaRequest(secret: String, response: String, remoteIp: String) {
    lazy val body = List(
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

  implicit val responseFormat = jsonFormat4(RecaptchaResponse)

  val FormUrlEncoded = "application/x-www-form-urlencoded"

  def recaptcha(rReq: RecaptchaRequest)(implicit ctx: Context): Promise[RecaptchaResponse] = {
    ctx.get(classOf[HttpClient]).post(recaptchaUrl, request =>
      request
        .headers(hs => hs.add(CONTENT_TYPE, FormUrlEncoded))
        .body(b => b.text(rReq.body)))
      .map[RecaptchaResponse](rResp => rResp.getBody.getText.parseJson.convertTo[RecaptchaResponse])
  }
}