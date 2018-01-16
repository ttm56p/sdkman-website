package io.sdkman.site

import ratpack.handling.{Context, Handler}

class ContextualHandler extends Handler {
  override def handle(ctx: Context): Unit = {
    val contextO = Option(ctx.getAllPathTokens.get("context"))
    contextO.fold(ctx.notFound()) { context =>
      ctx.render(ctx.file(static(context)))
    }
  }

  private def static(context: String): String = s"$context.html"
}
