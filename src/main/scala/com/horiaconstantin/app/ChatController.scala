package com.horiaconstantin.app

import java.util.Date

import org.json4s.JsonDSL._
import org.json4s._
import org.scalatra._
import org.scalatra.atmosphere._
import org.scalatra.json.{JValueResult, JacksonJsonSupport}
import org.scalatra.scalate.ScalateSupport
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.ExecutionContext.Implicits.global


class ChatController extends ScalatraServlet
  with ScalateSupport with JValueResult
  with JacksonJsonSupport with SessionSupport
  with AtmosphereSupport {

  implicit protected val jsonFormats: Formats = DefaultFormats
  val logger: Logger = LoggerFactory.getLogger(getClass)

  atmosphere("/the-chat") {
    new AtmosphereClient {
      def receive: AtmoReceive = {
        case Connected =>
          logger.info("Client %s is connected" format uuid)
          broadcast(("author" -> "Someone") ~ ("message" -> "joined the room") ~ ("time" -> new Date().getTime.toString), Everyone)

        case Disconnected(ClientDisconnected, _) =>
          broadcast(("author" -> "Someone") ~ ("message" -> "has left the room") ~ ("time" -> new Date().getTime.toString), Everyone)

        case Disconnected(ServerDisconnected, _) =>
          logger.info("Server disconnected the client %s" format uuid)
        case _: TextMessage =>
          send(("author" -> "system") ~ ("message" -> "Only json is allowed") ~ ("time" -> new Date().getTime.toString))

        case JsonMessage(json) =>
          logger.info("Got message {} from {}", (json \ "message").extract[String], (json \ "author").extract[String])
          val msg = json merge ("time" -> new Date().getTime.toString: JValue)
          broadcast(msg) // by default a broadcast is to everyone but self
          send(msg) // also send to the sender
      }
    }
  }

  error {
    case t: Throwable => t.printStackTrace()
  }
}
