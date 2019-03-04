package controllers

import io.sdkman.repos.ApplicationRepo
import javax.inject._
import org.slf4j.LoggerFactory
import play.api.libs.json.Json
import play.api.mvc._
import support.{Configuration, MongoConnection}

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class HealthController @Inject()(cc: ControllerComponents)
  extends AbstractController(cc)
    with ApplicationRepo
    with MongoConnection
    with Configuration {

  lazy val logger = LoggerFactory.getLogger(classOf[HealthController])

  def alive = Action.async { _ =>
    findApplication().map { maybeApp =>
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
