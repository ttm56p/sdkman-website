package controllers

import com.typesafe.scalalogging.LazyLogging
import io.sdkman.repos.{ApplicationRepo => IApplicationRepo}
import javax.inject._
import play.api.libs.json.Json
import play.api.mvc._
import support.MongoConnection

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class HealthController @Inject()(cc: ControllerComponents, applicationRepo: ApplicationRepo)
  extends AbstractController(cc) with LazyLogging {

  def alive = Action.async { _ =>
    applicationRepo.findApplication().map { maybeApp =>
      maybeApp.fold(NotFound(statusMessage("KO"))) { app =>
        val message = statusMessage(app.alive)
        logger.info(s"/alive 200 response: $message")
        Ok(message)
      }
    }.recover {
      case e =>
        val message = errorMessage(e)
        logger.error(s"/alive 503 response $message")
        ServiceUnavailable(message)
    }
  }

  private def statusMessage(s: String) = Json.obj("status" -> s)

  private def errorMessage(e: Throwable) = Json.obj("status" -> "KO", "error" -> e.getMessage)
}

class ApplicationRepo extends IApplicationRepo with MongoConnection