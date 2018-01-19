package io.sdkman.site

import javax.mail.internet.InternetAddress

import com.typesafe.scalalogging.LazyLogging
import courier.{Envelope, Mailer, Text}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

trait Email extends LazyLogging with Configuration {

  lazy val mailer = Mailer(smtpHost, smtpPort)
    .auth(true)
    .as(adminEmail, adminPassword)
    .startTtls(true)()

  def send(email: String, name: String, message: String): Unit = {
    mailer(Envelope.from(new InternetAddress(adminEmail))
      .to(new InternetAddress(adminEmail))
      .subject(s"SDKMAN contact request: $name")
      .content(Text(compose(email, name, message)))).onComplete {
      case Success(x) =>
        logger.info(s"Contact request: $name<$email>")
      case Failure(e) =>
        logger.error(s"Failed to send contact request: ${e.getMessage}")
        logger.info(s"Contact request: $name<$email> - $message")
    }
  }

  private def compose(email: String, name: String, message: String) =
    s"""
      |The following message was received from $name<$email>:
      |
      |$message
    """.stripMargin
}

