package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class ContextualController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  val index = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index(optionsEnabled = false, recaptchaEnabled = false, recaptchaSiteKey = "invalid"))
  }

  val install = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.install(recaptchaEnabled = false))
  }

  val usage = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.usage(recaptchaEnabled = false))
  }

  val vendors = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.vendors(recaptchaEnabled = false))
  }
}
