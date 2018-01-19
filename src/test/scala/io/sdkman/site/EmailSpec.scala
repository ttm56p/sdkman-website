package io.sdkman.site

import javax.mail.Message

import courier.Mailer
import org.junit.runner.RunWith
import org.jvnet.mock_javamail.Mailbox
import org.scalatest.concurrent.Eventually
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class EmailSpec extends WordSpec with Matchers with Eventually {

  "Email" should {
    "send an email to the desired email address" in new TestEmail {
      override val AdminEmail = "source@example.org"

      val emailAddr = "person@example.org"
      val name = "person"
      val message = "some message"

      send(emailAddr, name, message)

      eventually {
        val box = Mailbox.get(AdminEmail)
        box.size shouldBe 1
        val email: Message = box.get(0)
        email.getSubject shouldBe s"SDKMAN contact request: $name"
        val content = email.getContent.toString.trim
        content should startWith("The following message was received for person<person@example.org>")
        content should endWith(message)
      }
    }
  }

  sealed class TestEmail extends Email {
    override lazy val mailer = Mailer("localhost", 25)()
  }
}