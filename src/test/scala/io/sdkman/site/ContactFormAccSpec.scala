package io.sdkman.site

import org.scalatest.{BeforeAndAfter, Matchers, OptionValues, WordSpec}
import ratpack.test.MainClassApplicationUnderTest
import com.github.tomakehurst.wiremock.client.WireMock._
import org.junit.runner.RunWith
import org.scalatest.concurrent.Eventually
import org.scalatest.junit.JUnitRunner
import support.{EmailSupport, TestNetworking}

@RunWith(classOf[JUnitRunner])
class ContactFormAccSpec extends WordSpec
  with Matchers
  with BeforeAndAfter
  with TestNetworking
  with Eventually
  with EmailSupport
  with OptionValues {

  new MainClassApplicationUnderTest(classOf[SiteMain]).test { client =>

    "Contact Form" should {
      "send an email when recaptcha succeeds" in {

        val name = "admin"
        val email = "admin@localhost"
        val messageContent = "some message"
        val recaptchaResponse = "ABCD1234"

        val successResponse =
          """
            |{
            |  "success": true,
            |  "challenge_ts": "2018-05-17T12:39:22Z",
            |  "hostname": "beta.sdkman.io"
            |}
          """.stripMargin

        stubFor(post(urlEqualTo("/recaptcha"))
          .willReturn(aResponse()
            .withStatus(200)
            .withBody(successResponse)))

        client.requestSpec { request =>
          request.headers(headers =>
            headers.set("X-Real-IP", "remoteIp"))
          request.body(body =>
            body.text(s"name=$name&email=$email&message=$messageContent&g-recaptcha-response=$recaptchaResponse"))
        }.post("/contact")

        eventually {
          verify(postRequestedFor(urlEqualTo("/recaptcha"))
            .withRequestBody(equalTo(
              s"secret=secret&response=$recaptchaResponse&remoteip=remoteIp")))
        }

        withStore("admin") { store =>
          eventually {
            val messages = readMessages(store)
            messages.size shouldBe 1
            val message = messages.head
            message.getFrom.toList.headOption.value.toString shouldBe email
            val content = message.getContent.asInstanceOf[String]
            content should include(messageContent)
          }
        }
      }
    }
  }
}
