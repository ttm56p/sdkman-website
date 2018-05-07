package io.sdkman.site

import ratpack.form.Form
import ratpack.handling.{Context, Handler}
import support.RatpackSugar

class ContactFormHandler extends Handler with RatpackSugar with Email {

  override def handle(ctx: Context): Unit = {
    ctx.parse(classOf[Form]).blockingOp { f =>
      send(f.get("email"), f.get("name"), f.get("message"))
    } andThen { _ =>
      ctx.redirect("//")
    }
  }
}
