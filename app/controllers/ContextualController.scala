package controllers

import io.sdkman.repos.CandidatesRepo
import javax.inject._
import play.api.mvc._
import support.{Configuration, MongoConnection}

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class ContextualController @Inject()(cc: ControllerComponents)
  extends AbstractController(cc)
    with CandidatesRepo
    with MongoConnection
    with Configuration {

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

  def sdks = Action.async { _ =>
    findAllCandidates().map { candidates =>
      Ok(views.html.sdks(recaptchaEnabled = false, candidates))
    }
  }
}
