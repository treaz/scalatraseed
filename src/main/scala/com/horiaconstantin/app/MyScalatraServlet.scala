package com.horiaconstantin.app

import org.scalatra._
import org.scalatra.metrics.MetricsSupport
import org.slf4j.{Logger, LoggerFactory}

class MyScalatraServlet extends ScalatraServlet with MetricsSupport {

  val logger: Logger = LoggerFactory.getLogger(getClass)

  get("/") {
    logger.debug("inside get /")
    // Increments a counter called "counter"
    counter("counter") += 1

    // Increments a histogram called "histogram"
    histogram("histogram") += 1

    // Sets a gauge called "gauge"
    gauge("gauge") {
      "gauge"
    }

    // Sets a meter named "meter"
    meter("meter").mark(1)

    timer("timer") {
      // Code that's timed by a timer named "timer"
      views.html.hello()
    }
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
