enablePlugins(JavaServerAppPackaging)

enablePlugins(DockerPlugin)

name := """sdkman-website"""

organization := "io.sdkman"

Docker / packageName := "sdkman/sdkman-website"

dockerExposedPorts ++= Seq(9000)

Universal / javaOptions ++= Seq(
  "-Dpidfile.path=/dev/null"
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.10"

resolvers += "jitpack" at "https://jitpack.io"

libraryDependencies ++= Seq(
  ws,
  guice,
  "com.github.sdkman" % "sdkman-mongodb-persistence" % "1.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "com.iheart" %% "ficus" % "1.4.7")
