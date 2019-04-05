package controllers

import com.typesafe.scalalogging.LazyLogging
import javax.inject.Inject
import play.api.libs.ws.{WSClient, WSResponse}
import play.api.mvc._
import support.{Configuration, Email}
import play.api.data._
import play.api.data.Forms._

import scala.concurrent.ExecutionContext

class ContactFormController @Inject()(cc: ControllerComponents, wsClient: WSClient)
                                     (implicit ec: ExecutionContext) extends AbstractController(cc) with Email with LazyLogging with Configuration {

  case class ContactData(email: String, name: String, message: String, `g-recaptcha-response`: String)

  val contactForm = Form(
    mapping(
      "email" -> text,
      "name" -> text,
      "message" -> text,
      "g-recaptcha-response" -> text
    )(ContactData.apply)(ContactData.unapply)
  )

  def contact = Action.async { implicit request =>
    wsClient.url("http://localhost:8080/recaptcha")
      .post(s"secret=secret&response=ABCD1234&remoteip=remoteIp")
      .map { res: WSResponse =>
        logger.info("Completed call to recaptcha...")
        contactForm.bindFromRequest.value.foreach { data =>
          send(Some(data.email), Some(data.name), Some(data.message))
        }
        Ok("")
      }
  }

}
