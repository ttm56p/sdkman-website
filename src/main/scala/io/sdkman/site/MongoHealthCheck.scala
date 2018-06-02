package io.sdkman.site

import com.typesafe.config.{Config, ConfigFactory}
import io.sdkman.db.{MongoConfiguration, MongoConnectivity}
import io.sdkman.repos
import javax.inject.{Inject, Singleton}
import ratpack.exec.Promise
import ratpack.health.HealthCheck
import ratpack.health.HealthCheck.Result.{healthy, unhealthy}
import ratpack.registry.Registry
import support.FutureTransform

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class MongoHealthCheck @Inject()(val appRepo: AppRepo) extends HealthCheck with FutureTransform {

  val NotOk = "KO"

  val Ok = "OK"

  override def getName: String = "alive"

  override def check(registry: Registry): Promise[HealthCheck.Result] =
    appRepo
      .findApplication()
      .map(appO => appO.fold(unhealthy(NotOk))(app =>
        if (app.alive == Ok) healthy(Ok) else unhealthy(NotOk)))
      .toPromise
}

class AppRepo extends repos.ApplicationRepo with MongoConnectivity with MongoConfiguration {
  override def config: Config = ConfigFactory.load()
}
