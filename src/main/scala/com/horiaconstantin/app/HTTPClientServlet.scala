package com.horiaconstantin.app

import org.scalatra.{Ok, ScalatraServlet}
import sttp.client._

class HTTPClientServlet extends ScalatraServlet {

  get("/") {
    val sort: Option[String] = None
    val query = "http language:scala"

    // the `query` parameter is automatically url-encoded
    // `sort` is removed, as the value is not defined
    val request = basicRequest.get(
      uri"https://api.github.com/search/repositories?q=$query&sort=$sort")

    implicit val backend: SttpBackend[Identity, Nothing, NothingT] = HttpURLConnectionBackend()
    val response = request.send()

    // response.header(...): Option[String]
    println(response.header("Content-Length"))

    // response.unsafeBody: by default read into a String
    //    println(response.body)
    Ok(s"Got response code: ${response.statusText}")
  }

}
