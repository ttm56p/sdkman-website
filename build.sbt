enablePlugins(JavaServerAppPackaging)

enablePlugins(DockerPlugin)

name := """sdkman-website"""

organization := "io.sdkman"

version := "1.0.0-SNAPSHOT"

packageName in Docker := "sdkman/sdkman-website"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.8"

resolvers ++= Seq(
  Resolver.bintrayRepo("sdkman", "maven"),
  Resolver.jcenterRepo
)

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.1" % Test
libraryDependencies += "io.sdkman" %% "sdkman-mongodb-persistence" % "0.11"
