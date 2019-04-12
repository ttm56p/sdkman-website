package support

import com.typesafe.scalalogging.LazyLogging
import courier.{Envelope, Mailer, Text}
import javax.inject.{Inject, Singleton}
import javax.mail.internet.InternetAddress

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

@Singleton
class Emailer @Inject()(val conf: Configuration) extends LazyLogging {

  lazy val mailer = Mailer(conf.smtpHost, conf.smtpPort)
    .auth(true)
    .as(conf.adminEmail, conf.adminPassword)
    .startTtls(true)()

  def send(maybeEmail: Option[String], maybeName: Option[String], maybeMessage: Option[String]): Unit =
    for {
      email <- maybeEmail
      name <- maybeName
      message <- maybeMessage
    } yield {
      mailer(Envelope.from(new InternetAddress(conf.adminEmail))
        .to(new InternetAddress(conf.adminEmail))
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

