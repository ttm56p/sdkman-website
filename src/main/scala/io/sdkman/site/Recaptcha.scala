package io.sdkman.site

trait Recaptcha {
  def recaptcha(secret: String, response: String, ipAddress: String): Either[Throwable, String] = Right("success")
}
