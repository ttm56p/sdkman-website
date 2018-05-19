package support

import ratpack.exec.Promise

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

trait FutureTransform {

  implicit class FutureAPI[A](self: => Future[A]) {
    def toPromise(implicit ec: ExecutionContext): Promise[A] = {
      Promise.async { f =>
        self onComplete {
          case Success(a) => f.success(a)
          case Failure(t) => f.error(t)
        }
      }
    }
  }
}

trait PromiseTransform {

  implicit class PromiseAPI[A](self: Promise[A]) {
    def toFuture(implicit ec: ExecutionContext): Future[A] = {
      val p = concurrent.Promise[A]
      Future {
        self.onError(e => p.failure(e)).then(s => p.success(s))
      }
      p.future
    }
  }

}