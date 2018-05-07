package io.sdkman.site

import ratpack.guice._
import ratpack.server.BaseDir._
import ratpack.server.RatpackServer

class SiteMain

object SiteMain extends App {

  val IndexPage = "twirl/index.scala.html"

  RatpackServer.start { server =>
    server
      .serverConfig(conf => conf.baseDir(find()))
      .registry {
        Guice.registry { bindings =>
          bindings
            .bind(classOf[ContactFormHandler])
            .bind(classOf[SdksPageHandler])
            .bind(classOf[ContextualHandler])
            .bind(classOf[NotFoundErrorHandler])
        }
      }
      .handlers { chain =>
        chain
          .post("contact", classOf[ContactFormHandler])
          .get("sdks", classOf[SdksPageHandler])
          .get(":context", classOf[ContextualHandler])
          .get(classOf[ContextualHandler])
          .files(fs => fs.indexFiles(IndexPage))
      }
  }
}