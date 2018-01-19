package io.sdkman.site

import com.typesafe.config.ConfigFactory

trait Configuration {

  private lazy val config = ConfigFactory.load()

  lazy val smtpHost = config.getString("smtp.host")

  lazy val smtpPort = config.getInt("smtp.port")

  lazy val adminEmail = config.getString("smtp.email")

  lazy val adminPassword = config.getString("smtp.password")
}
