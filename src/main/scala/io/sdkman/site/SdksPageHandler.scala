package io.sdkman.site

import com.typesafe.scalalogging.LazyLogging
import io.sdkman.repos.CandidatesRepo
import ratpack.handling.Context
import repos.MongoConn
import support._

import scala.concurrent.ExecutionContext.Implicits.global

class SdksPageHandler extends Handler
  with RatpackSugar
  with CandidatesRepo
  with MongoConn
  with LazyLogging
  with FutureTransform {

  override def handles(implicit ctx: Context): Unit = {
    findAllCandidates().toPromise.andThen { candidates =>
      OK(html.sdks(candidates))
    }
  }
}
