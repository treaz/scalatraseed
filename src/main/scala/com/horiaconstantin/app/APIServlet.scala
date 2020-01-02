package com.horiaconstantin.app

import org.json4s.{DefaultFormats, Formats}
import org.scalatra._
import org.scalatra.json.JacksonJsonSupport
import org.slf4j.{Logger, LoggerFactory}

class APIServlet extends ScalatraServlet with JacksonJsonSupport {

  // Sets up automatic case class to JSON output serialization, required by
  // the JValueResult trait.
  protected implicit lazy val jsonFormats: Formats = DefaultFormats

  val logger: Logger = LoggerFactory.getLogger(getClass)

  // Before every action runs, set the content type to be in JSON format.
  before() {
    contentType = formats("json")
  }

  get("/flowers") {
    FlowerData.all
  }

  //curl -H "Content-Type: application/json" -d '{"slug":"flowerjson","name":"flower from json"}' http://localhost:8080/api/flowerAsJSON
  post("/flowerAsJSON") {
    val flower = parsedBody.extract[Flower]
    FlowerData.all = flower :: FlowerData.all
    Created(s"created $flower")
  }

  //curl -d "slug=flower1&name=magicflower" http://localhost:8080/api/flower
  post("/flower") {
    val name = params.getOrElse("name", halt(400, "missing name"))
    val slug = params.getOrElse("slug", halt(400, "missing slug"))
    logger.debug("post /flower?slug={}&name={}", params("slug"), params("name"))
    FlowerData.all = Flower(slug, name) :: FlowerData.all
    Created("created\n")
  }

  get("/flower/:slug") { //  <= this is a route matcher
    logger.debug("inside /flowers/{}", params("slug"))
    FlowerData.all.filter(_.slug == params("slug"))
  }

}

object FlowerData {

  /**
   * Some fake flowers data so we can simulate retrievals.
   */
  var all: List[Flower] = List(
    Flower("yellow-tulip", "Yellow Tulip"),
    Flower("red-rose", "Red Rose"),
    Flower("black-rose", "Black Rose"))
}

//The case class should be defined outside the base servelet because of of the limitation of scala reflection, use in json4s
case class Flower(slug: String, name: String)