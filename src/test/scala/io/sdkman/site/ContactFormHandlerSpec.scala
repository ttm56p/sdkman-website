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
          fix.method("POST").body("email=a&name=b&message=c", APPLICATION_FORM)
        }

        val result = RequestFixture.handle(new TestContactFormHandler, action)

        result.getStatus.getCode shouldBe 200
      }

      "send an email for a form submission" in {
        val email = "person@example.com"
        val name = "name"
        val message = "message"

        val action: Action[RequestFixture] = { fixture =>
          fixture.method("POST").body(s"email=$email&name=$name&message=$message", APPLICATION_FORM)
        }

        val handler = new TestContactFormHandler

        RequestFixture.handle(handler, action)

        handler.email shouldBe email
        handler.name shouldBe name
        handler.message shouldBe message
      }
    }
  }

  private class TestContactFormHandler extends ContactFormHandler {
    var email = "not update"
    var name = "not update"
    var message = "not update"

    override def send(email: String, name: String, message: String): Unit = {
      this.email = email
      this.name = name
      this.message = message
    }
  }
}
