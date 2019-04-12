package support

import java.net.URI

import com.typesafe.config.ConfigFactory
import javax.inject.Singleton

@Singleton
class Configuration {

  lazy val config = ConfigFactory.load()

  lazy val smtpHost = config.getString("smtp.host")

  lazy val smtpPort = config.getInt("smtp.port")

  lazy val adminEmail = config.getString("smtp.email")

  lazy val adminPassword = config.getString("smtp.password")

  lazy val optionsEnabled = config.getBoolean("options.enabled")

  lazy val recaptchaUrl = new URI(config.getString("recaptcha.url"))

  lazy val recaptchaSiteKey = config.getString("recaptcha.key")

  lazy val recaptchaSecret = config.getString("recaptcha.secret")
}
