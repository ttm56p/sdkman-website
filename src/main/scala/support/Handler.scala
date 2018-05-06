package support

import ratpack.handling.Context

trait Handler extends ratpack.handling.Handler {

  override def handle(ctx: Context): Unit = handles(ctx)

  def handles(implicit ctx: Context): Unit

}
