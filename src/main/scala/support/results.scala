package support

import play.twirl.api.Html
import ratpack.handling.Context

object OK {
  def apply(html: Html)(implicit ctx: Context): Unit = ctx.getResponse.contentType("text/html").send(html.body)
}

