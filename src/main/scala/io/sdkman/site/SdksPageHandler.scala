package io.sdkman.site

import com.typesafe.scalalogging.LazyLogging
import io.sdkman.repos.{Candidate, CandidatesRepo}
import play.twirl.api.Html
import ratpack.exec.Promise
import ratpack.handling.{Context, Handler}
import repos.MongoConn

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object Ok {
  def apply[C](content: C)(implicit writeable: Writeable[C]): String = writeable.toString(content)
}

class Writeable[-A](val transform: A => String, val contentType: Option[String]) {
  def toString(a: A): String = transform(a)
  def map[B](f: B => A): Writeable[B] = new Writeable(b => transform(f(b)), contentType)
}

class SdksPageHandler extends Handler with CandidatesRepo with MongoConn with LazyLogging {

  implicit val writableInstance = new Writeable[Html]((html: Html) => html.body, Some("application/html"))

  override def handle(ctx: Context): Unit = {
    Promise.async[Seq[Candidate]]{ f =>
      findAllCandidates().onComplete {
        case Success(s) => f.success(s)
        case Failure(e) =>
          logger.error(s"Error: ${e.getMessage}")
          f.error(e)
      }
    }.then { candidates =>
      ctx.getResponse.contentType("text/html").send(Ok(html.sdks(candidates.toList): Html): java.lang.String)
    }
  }
}
