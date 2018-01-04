package io.sdkman.site

import ratpack.server.BaseDir._
import ratpack.server.RatpackServer

object Main extends App {
  RatpackServer.start { server =>
    server
      .serverConfig(s => s.baseDir(find()))
      .handlers { chain =>
        chain.files(f => f.indexFiles("index.html"))
      }
  }
}