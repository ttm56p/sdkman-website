name := """sdkman-website"""
organization := "io.sdkman"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.8"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.1" % Test
libraryDependencies += "io.sdkman" %% "sdkman-mongodb-persistence" % "0.11"
