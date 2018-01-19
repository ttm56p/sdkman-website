package io.sdkman.site

import ratpack.form.Form
import ratpack.handling.{Context, Handler}

class ContactFormHandler extends Handler with Email {

  override def handle(ctx: Context): Unit = {
    ctx.parse(classOf[Form]).blockingOp { f =>
      send(f.get("email"), f.get("name"), f.get("message"))
    } then { _ =>
      ctx.redirect("/")
    }
  }
}
