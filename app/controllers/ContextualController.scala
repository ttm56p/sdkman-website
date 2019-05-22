package controllers

import io.sdkman.repos.{CandidatesRepo => ICandidatesRepo}
import javax.inject._
import play.api.mvc._
import support.MongoConnection

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class ContextualController @Inject()(cc: ControllerComponents,
                                     candidatesRepo: CandidatesRepo) extends AbstractController(cc) {

  val index = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  val install = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.install())
  }

  val usage = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.usage())
  }

  val vendors = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.vendors())
  }

  def sdks = Action.async { _ =>
    candidatesRepo.findAllCandidates().map { candidates =>
      Ok(views.html.sdks(candidates))
    }
  }
}

@Singleton
class CandidatesRepo extends ICandidatesRepo with MongoConnection
