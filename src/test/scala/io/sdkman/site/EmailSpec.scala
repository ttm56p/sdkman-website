package io.sdkman.site

import com.typesafe.scalalogging.LazyLogging
import javax.mail.Message
import courier.Mailer
import org.junit.runner.RunWith
import org.jvnet.mock_javamail.Mailbox
import org.scalatest.concurrent.{Eventually, IntegrationPatience}
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfter, Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class EmailSpec extends WordSpec with Matchers with Eventually with BeforeAndAfter with IntegrationPatience {

  before {
    Mailbox.clearAll()
  }

  "Email" should {
    "send an email to the desired email address" in new TestEmail {

      override lazy val adminEmail = "source@example.org"

      val email = "person@example.org"
      val name = "person"
      val message = "some message"

      send(Some(email), Some(name), Some(message))

      eventually {
        val box = Mailbox.get(adminEmail)
        box.size shouldBe 1
        val email: Message = box.get(0)
        email.getSubject shouldBe s"SDKMAN contact request: $name"
        val content = email.getContent.toString.trim
        content should startWith("The following message was received from person<person@example.org>")
        content should endWith(message)
      }
    }

    "not send an email if form fields are missing" in new TestEmail {
      override lazy val adminEmail = "source@example.org"

      val email = "person@example.org"
      val name = "person"
      val message = "some message"

      send(None, None, None)
      send(Some(email), Some(name), Some(message))

      eventually {
        Mailbox.get(adminEmail).size shouldBe 1
      }
    }

    "not send an email if some form fields are missing" in new TestEmail {
      override lazy val adminEmail = "source@example.org"

      val email = "person@example.org"
      val name = "person"
      val message = "some message"

      send(None, Some(name), Some(message))
      send(Some(email), Some(name), Some(message))

      eventually {
        Mailbox.get(adminEmail).size shouldBe 1
      }
    }
  }

  sealed class TestEmail extends Email with Configuration with LazyLogging {

    override lazy val adminPassword = ""

    override lazy val smtpHost: String = "localhost"

    override lazy val smtpPort: Int = 25

    override lazy val mailer = Mailer(smtpHost, smtpPort)()
  }

}