package com.horiaconstantin.app

import org.scalatra._
import org.slf4j.{Logger, LoggerFactory}

class MyScalatraServlet extends ScalatraServlet {

  val logger: Logger = LoggerFactory.getLogger(getClass)

  get("/") {
    logger.debug("inside get /")
    views.html.hello()
  }

}
