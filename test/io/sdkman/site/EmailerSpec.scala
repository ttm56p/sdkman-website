package io.sdkman.site

import com.typesafe.scalalogging.LazyLogging
import courier.Mailer
import javax.mail.Message
import org.junit.runner.RunWith
import org.jvnet.mock_javamail.Mailbox
import org.scalatest.concurrent.{Eventually, IntegrationPatience}
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfter, Matchers, WordSpec}
import support.{Configuration, Emailer}

@RunWith(classOf[JUnitRunner])
class EmailerSpec extends WordSpec
  with Matchers
  with Eventually
  with BeforeAndAfter
  with IntegrationPatience {

  before {
    Mailbox.clearAll()
  }

  "Email" should {
    "send an email to the desired email address" in new TestEmailer(adminEmailAddress = "source@example.org") {

      val email = "person@example.org"
      val name = "person"
      val message = "some message"

      send(Some(email), Some(name), Some(message))

      eventually {
        val box = Mailbox.get(conf.adminEmail)
        box.size shouldBe 1
        val email: Message = box.get(0)
        email.getSubject shouldBe s"SDKMAN contact request: $name"
        val content = email.getContent.toString.trim
        content should startWith("The following message was received from person<person@example.org>")
        content should endWith(message)
      }
    }

    "not send an email if form fields are missing" in new TestEmailer(adminEmailAddress = "source@example.org") {

      val email = "person@example.org"
      val name = "person"
      val message = "some message"

      send(None, None, None)
      send(Some(email), Some(name), Some(message))

      eventually {
        Mailbox.get(conf.adminEmail).size shouldBe 1
      }
    }

    "not send an email if some form fields are missing" in new TestEmailer(adminEmailAddress = "source@example.org") {

      val email = "person@example.org"
      val name = "person"
      val message = "some message"

      send(None, Some(name), Some(message))
      send(Some(email), Some(name), Some(message))

      eventually {
        Mailbox.get(conf.adminEmail).size() shouldBe 1
      }
    }
  }

  class TestEmailer(adminEmailAddress: String) extends Emailer(new Configuration {
    override lazy val adminEmail = adminEmailAddress
    override lazy val adminPassword = ""
  }) with LazyLogging {

    val smtpHost: String = "localhost"

    val smtpPort: Int = 25

    override lazy val mailer = Mailer(smtpHost, smtpPort)()
  }
}