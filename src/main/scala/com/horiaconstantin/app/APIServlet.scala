package com.horiaconstantin.app

import org.scalatra._
import org.slf4j.{Logger, LoggerFactory}

class APIServlet extends ScalatraServlet {

  val logger: Logger = LoggerFactory.getLogger(getClass)

  get("/flowers") {
    FlowerData.all
  }

  //  Test endpoint with curl -d "slug=flower1&name=magicflower" http://localhost:8080/api/flower
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

  case class Flower(slug: String, name: String)

  object FlowerData {

    /**
     * Some fake flowers data so we can simulate retrievals.
     */
    var all: List[Flower] = List(
      Flower("yellow-tulip", "Yellow Tulip"),
      Flower("red-rose", "Red Rose"),
      Flower("black-rose", "Black Rose"))
  }

}
