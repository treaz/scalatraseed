organization := "com.horiaconstantin"

name := "scalatraseed"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.13.1"
val ScalatraVersion = "2.7.0-RC1"
val JettyVersion = "9.4.25.v20191220"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
  "ch.qos.logback" % "logback-classic" % "1.2.3" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % JettyVersion % "container",
  "javax.servlet" % "javax.servlet-api" % "4.0.1" % "provided"
)

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
  "org.eclipse.jetty.websocket" % "websocket-server" % JettyVersion % "container;provided",
)

// SQL support
libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.3.2",
  "com.h2database" % "h2" % "1.4.200",
  "com.mchange" % "c3p0" % "0.9.5.5"
)

enablePlugins(SbtTwirl)
enablePlugins(ScalatraPlugin)
