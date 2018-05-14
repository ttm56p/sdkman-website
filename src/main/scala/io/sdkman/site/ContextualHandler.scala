package io.sdkman.site

import ratpack.handling.Context
import support.{Handler, OK}

class ContextualHandler extends Handler with Configuration {

  override def handles(implicit ctx: Context): Unit = {
    Option(ctx.getAllPathTokens.get("context")) match {
      case Some("index") => OK(html.index(recaptchaSiteKey))
      case Some("install") => OK(html.install())
      case Some("usage") => OK(html.usage())
      case Some("vendors") => OK(html.vendors())
      case Some(_) => ctx.notFound()
      case None => OK(html.index(recaptchaSiteKey))
    }
  }
}
