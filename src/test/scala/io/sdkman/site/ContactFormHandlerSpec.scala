package io.sdkman.site

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}
import ratpack.func.Action
import ratpack.http.MediaType.APPLICATION_FORM
import ratpack.test.exec.ExecHarness
import ratpack.test.handling.RequestFixture

@RunWith(classOf[JUnitRunner])
class ContactFormHandlerSpec extends WordSpec with Matchers {

  ExecHarness.runSingle { _ =>

    "ContactFormHandler" should {
      "render the index page" in {
        val action: Action[RequestFixture] = { fix =>
          fix.method("POST").body("email=a&name=b&message=c&g-recaptcha-response=d", APPLICATION_FORM)
        }

        val result = RequestFixture.handle(new TestContactFormHandler, action)

        result.getStatus.getCode shouldBe 200
      }

      "post to the recaptcha api" in {
        val recaptchaResponse = "response"
        val recaptchaSecret = "secret"
        val recaptchaRemoteIpAddress = "127.0.0.1"

        val action: Action[RequestFixture] = { fix =>
          fix.method("POST").body(s"email=a&name=b&message=c&g-recaptcha-response=$recaptchaResponse", APPLICATION_FORM)
        }

        val handler = new TestContactFormHandler

        RequestFixture.handle(handler, action)

        handler.recaptchaResponse shouldBe recaptchaResponse
        handler.recaptchaSecret shouldBe recaptchaSecret
        handler.recaptchaRemoteIpAddress shouldBe recaptchaRemoteIpAddress
      }

      "send email if racaptcha call succeeds" in {
        val email = "person@example.com"
        val name = "name"
        val message = "message"

        val action: Action[RequestFixture] = { fixture =>
          fixture.method("POST").body(s"email=$email&name=$name&message=$message&g-recaptcha-response=d", APPLICATION_FORM)
        }

        val handler = new TestContactFormHandler

        RequestFixture.handle(handler, action)

        handler.email shouldBe email
        handler.name shouldBe name
        handler.message shouldBe message
      }

      "not send email if recaptcha call fails" in {
        val recaptchaResponse = "response"
        val recaptchaSecret = "secret"
        val recaptchaRemoteIpAddress = "127.0.0.1"

        val action: Action[RequestFixture] = { fix =>
          fix.method("POST").body(s"email=a&name=b&message=c&g-recaptcha-response=$recaptchaResponse", APPLICATION_FORM)
        }

        val handler = new TestContactFormHandler {
          override def recaptcha(secret: String, response: String, ipAddress: String) = {
            super.recaptcha(secret, response, ipAddress)
            Left(new Throwable("recaptcha failed"))
          }
        }

        RequestFixture.handle(handler, action)

        handler.email shouldBe "not update"
        handler.name shouldBe "not update"
        handler.message shouldBe "not update"
      }
    }
  }

  private class TestContactFormHandler extends ContactFormHandler {
    var email = "not update"
    var name = "not update"
    var message = "not update"

    var recaptchaResponse = "not updated"
    var recaptchaSecret = "not updated"
    var recaptchaRemoteIpAddress = "not updated"

    override def send(email: String, name: String, message: String): Unit = {
      this.email = email
      this.name = name
      this.message = message
    }

    override def recaptcha(secret: String, response: String, ipAddress: String): Either[Throwable, String] = {
      this.recaptchaSecret = secret
      this.recaptchaResponse = response
      this.recaptchaRemoteIpAddress = ipAddress
      Right("success")
    }
  }
}
