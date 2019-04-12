package controllers

import io.sdkman.repos.{CandidatesRepo => ICandidatesRepo}
import javax.inject._
import play.api.mvc._
import support.{Configuration, MongoConnection}

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class ContextualController @Inject()(cc: ControllerComponents,
                                     conf: Configuration,
                                     candidatesRepo: CandidatesRepo) extends AbstractController(cc) {

  val index = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index(conf.optionsEnabled, conf.recaptchaSiteKey))
  }

  val install = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.install(conf.optionsEnabled))
  }

  val usage = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.usage(conf.optionsEnabled))
  }

  val vendors = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.vendors(conf.optionsEnabled))
  }

  def sdks = Action.async { _ =>
    candidatesRepo.findAllCandidates().map { candidates =>
      Ok(views.html.sdks(conf.optionsEnabled, candidates))
    }
  }
}

@Singleton
class CandidatesRepo extends ICandidatesRepo with MongoConnection
