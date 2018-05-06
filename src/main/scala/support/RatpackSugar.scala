package support

import ratpack.exec.Promise
import ratpack.func.Action

trait RatpackSugar {
  implicit class PromiseAPI[A](self: => Promise[A]) {
    def andThen(a: Action[A]): Unit = self.then(a)
  }
}
