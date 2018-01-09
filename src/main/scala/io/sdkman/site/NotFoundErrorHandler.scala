package io.sdkman.site

import ratpack.error.ClientErrorHandler
import ratpack.handling.Context

class NotFoundErrorHandler extends ClientErrorHandler {
  override def error(ctx: Context, statusCode: Int): Unit = ctx.render(ctx.file("/404.html"))
}
