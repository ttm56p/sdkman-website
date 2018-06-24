package io.sdkman.site

import org.junit.runner.RunWith
import org.scalatest.concurrent.Eventually
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}
import ratpack.exec.Promise
import ratpack.func.Action
import ratpack.handling.Context
import ratpack.http.MediaType.APPLICATION_FORM
import ratpack.test.exec.ExecHarness
import ratpack.test.handling.RequestFixture

@RunWith(classOf[JUnitRunner])
class ContactFormHandlerSpec extends WordSpec with Matchers with Eventually {

  ExecHarness.runSingle { _ =>

    "ContactFormHandler" should {
      "render the index page" in {

        val handler = new TestContactFormHandler {
          override def recaptcha(request: RecaptchaRequest)(implicit ctx: Context): Promise[RecaptchaResponse] =
            Promise.value(RecaptchaResponse(success = true))
        }

        val result = RequestFixture.handle(handler, requestAction())

        result.getStatus.getCode shouldBe 200
      }

      "post to the recaptcha api" in {
        val recaptchaResponse = "response"
        val recaptchaSecret = "secret"
        val remoteIpAddress = "127.0.0.1"

        val handler = new TestContactFormHandler {
          override def recaptcha(req: RecaptchaRequest)(implicit ctx: Context): Promise[RecaptchaResponse] = {
            this.recaptchaSharedSecret = req.secret
            this.recaptchaResponse = req.response
            this.recaptchaRemoteIpAddress = req.remoteIp
            Promise.value(RecaptchaResponse(success = true))
          }
        }

        RequestFixture.handle(handler, requestAction(recaptchaResponse = recaptchaResponse, remoteIp = remoteIpAddress))

        eventually {
          handler.recaptchaResponse shouldBe recaptchaResponse
          handler.recaptchaSecret shouldBe recaptchaSecret
          handler.recaptchaRemoteIpAddress shouldBe remoteIpAddress
        }
      }

      "send email if racaptcha call succeeds" in {
        val email = "person@example.com"
        val name = "name"
        val message = "message"

        val handler = new TestContactFormHandler {
          override def recaptcha(req: RecaptchaRequest)(implicit ctx: Context): Promise[RecaptchaResponse] = {
            this.email = "person@example.com"
            this.name = "name"
            this.message = "message"
            Promise.value(RecaptchaResponse(success = true))
          }
        }

        RequestFixture.handle(handler, requestAction(email, name, message))

        eventually {
          handler.email shouldBe email
          handler.name shouldBe name
          handler.message shouldBe message
        }
      }

      "not send email if recaptcha call fails" in {
        val recaptchaResponse = "response"
        val recaptchaSecret = "secret"
        val recaptchaRemoteIpAddress = "127.0.0.1"

        val handler = new TestContactFormHandler {
          override def recaptcha(req: RecaptchaRequest)(implicit ctx: Context): Promise[RecaptchaResponse] = Promise.error(new Throwable("recaptcha failed"))
        }

        RequestFixture.handle(handler, requestAction(
          recaptchaResponse = recaptchaResponse,
          remoteIp = recaptchaRemoteIpAddress))

        eventually {
          handler.email shouldBe "not update"
          handler.name shouldBe "not update"
          handler.message shouldBe "not update"
        }
      }

      "do not call recaptcha if disabled" in {

        val email = "email"
        val name = "name"
        val message = "message"

        val handler = new TestContactFormHandler {
          override def recaptcha(req: RecaptchaRequest)(implicit ctx: Context): Promise[RecaptchaResponse] =
            throw new IllegalStateException("Recaptcha was called")
          override lazy val recaptchaEnabled = false
        }

        RequestFixture.handle(handler, requestAction(email, name, message))

        eventually {
          handler.email shouldBe email
          handler.name shouldBe name
          handler.message shouldBe message
        }
      }
    }
  }

  private class TestContactFormHandler extends ContactFormHandler {

    override lazy val recaptchaSecret = "secret"

    var email = "not update"
    var name = "not update"
    var message = "not update"

    var recaptchaResponse = "not updated"
    var recaptchaSharedSecret = "not updated"
    var recaptchaRemoteIpAddress = "not updated"

    override def send(email: Option[String], name: Option[String], message: Option[String]): Unit = {
      this.email = email.getOrElse("")
      this.name = name.getOrElse("")
      this.message = message.getOrElse("")
    }
  }

  private def requestAction(email: String = "email",
                            name: String = "name",
                            message: String = "message",
                            recaptchaResponse: String = "recaptchaResponse",
                            remoteIp: String = "remoteIp"): Action[RequestFixture] =
    requestFixture =>
      requestFixture
        .method("POST")
        .header("X-Real-IP", remoteIp)
        .body(s"email=$email&name=$name&message=$message&g-recaptcha-response=$recaptchaResponse", APPLICATION_FORM)

}
