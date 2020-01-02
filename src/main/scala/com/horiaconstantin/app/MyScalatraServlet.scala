package com.horiaconstantin.app

import org.scalatra._
import org.slf4j.{Logger, LoggerFactory}

class MyScalatraServlet extends ScalatraServlet {

  val logger: Logger = LoggerFactory.getLogger(getClass)

  get("/") {
    logger.debug("inside get /")
    views.html.hello()
  }

  get("/cookies") {
    val previous = cookies.get("counter") match {
      case Some(v) => v.toInt
      case None => 0
    }
    cookies.update("counter", (previous + 1).toString)
    <p>
      Hi, you have been on this page
      {previous}
      times already
    </p>
  }

  get("/sessionAttribute") {
    val previous: Int = session.getAs[Int]("counter") match {
      case Some(v) => v
      case None => 0
    }
    session.setAttribute("counter", (previous + 1).toString)
    <p>
      Hi, you have been on this page
      {previous}
      times already
    </p>
  }

}
