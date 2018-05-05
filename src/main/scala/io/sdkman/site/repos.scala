package repos

import com.typesafe.config.{Config, ConfigFactory}
import io.sdkman.db.{MongoConfiguration, MongoConnectivity}

trait MongoConn extends MongoConnectivity with MongoConfiguration {
  override lazy val config: Config = ConfigFactory.load()
}



