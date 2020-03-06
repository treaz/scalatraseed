organization := "com.horiaconstantin"

name := "scalatraseed"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.13.1"
val ScalatraVersion = "2.7.0"
val JettyVersion = "9.4.27.v20200227"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
  "ch.qos.logback" % "logback-classic" % "1.2.3" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % JettyVersion % "container;compile",
  "javax.servlet" % "javax.servlet-api" % "4.0.1" % "provided"
)

enablePlugins(SbtTwirl)
enablePlugins(ScalatraPlugin)

//Debug configuration
javaOptions ++= Seq(
  "-Xdebug",
  "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
)

//JSON support
libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra-json" % ScalatraVersion,
  "org.json4s" %% "json4s-jackson" % "3.6.7"
)

// Websockets support
libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
  "org.scalatra" %% "scalatra-atmosphere" % ScalatraVersion,
  "org.eclipse.jetty" % "jetty-plus" % JettyVersion % "container;provided",
  "org.eclipse.jetty" % "jetty-continuation" % JettyVersion % "container;provided", // to fix this https://github.com/Atmosphere/atmosphere/issues/978
  "org.eclipse.jetty.websocket" % "websocket-server" % JettyVersion % "container;provided",
)

// SQL support
libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.3.2",
  "com.h2database" % "h2" % "1.4.199", // version 200 throws a NetUtils2 exception
  "com.mchange" % "c3p0" % "0.9.5.5"
)

// Docker support
maintainer := "Gary Coady <gary@lyranthe.org>"
dockerBaseImage := "openjdk:12-alpine"
// Don't create user demiourgos728 as the baseImage does not support useradd
daemonUserUid in Docker := None
daemonUser in Docker := "daemon"
dockerExposedPorts := Seq(8080)
dockerUpdateLatest := true
enablePlugins(JavaAppPackaging) // standalone app needs to to packaged first
enablePlugins(DockerPlugin)
enablePlugins(AshScriptPlugin) // used because alpine does not contain bash

// HTTP client
libraryDependencies ++= Seq("com.softwaremill.sttp.client" %% "core" % "2.0.2")

//Metrics support
libraryDependencies ++= Seq("org.scalatra" %% "scalatra-metrics" % ScalatraVersion)

// Swagger support
libraryDependencies ++= Seq("org.scalatra" %% "scalatra-swagger" % ScalatraVersion)


// Use this to see deprecation warnings
scalacOptions := Seq("-unchecked", "-deprecation")



