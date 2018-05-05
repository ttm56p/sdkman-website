package io.sdkman.site

import com.typesafe.scalalogging.LazyLogging
import io.sdkman.repos.{Candidate, CandidatesRepo}
import ratpack.exec.Promise
import ratpack.handling.{Context, Handler}
import repos.MongoConn

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

class SdksPageHandler extends Handler with CandidatesRepo with MongoConn with LazyLogging {
  override def handle(ctx: Context): Unit = {
    Promise.async[Seq[Candidate]]{ f =>
      findAllCandidates().onComplete {
        case Success(s) => f.success(s)
        case Failure(e) =>
          logger.error(s"Error: ${e.getMessage}")
          f.error(e)
      }
    }.then(candidates => ctx.render(candidates.map(template).mkString("\n")))
  }

  def template(c: Candidate): String =
    s"""
      |<h4>${c.name}</h4>
      |  <a href='${c.websiteUrl}' target='_blank'>${c.websiteUrl}</a></br>
      |  <p>${c.description}</p>
      |<code>$$ sdk install ${c.candidate}</code>
      |<hr/>
    """.stripMargin
}
