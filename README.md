# scalatraseed #

[![Scala Steward badge](https://img.shields.io/badge/Scala_Steward-helping-blue.svg?style=flat&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA4AAAAQCAMAAAARSr4IAAAAVFBMVEUAAACHjojlOy5NWlrKzcYRKjGFjIbp293YycuLa3pYY2LSqql4f3pCUFTgSjNodYRmcXUsPD/NTTbjRS+2jomhgnzNc223cGvZS0HaSD0XLjbaSjElhIr+AAAAAXRSTlMAQObYZgAAAHlJREFUCNdNyosOwyAIhWHAQS1Vt7a77/3fcxxdmv0xwmckutAR1nkm4ggbyEcg/wWmlGLDAA3oL50xi6fk5ffZ3E2E3QfZDCcCN2YtbEWZt+Drc6u6rlqv7Uk0LdKqqr5rk2UCRXOk0vmQKGfc94nOJyQjouF9H/wCc9gECEYfONoAAAAASUVORK5CYII=)](https://scala-steward.org)

Demo project based on `sbt new scalatra/scalatra.g8`. It contains some of the features demonstrated on http://scalatra.org/guides/2.6/

## Features
* logging with logback
* debug configuration
* api endpoint (APIServlet) with
  * Named parameters
  * specific status code
  * incoming/outoging json support
  * 404/not found handling
  * server side exception handling
  * handling cookies and session attributes
* WebSockets support (use chrome extension Smart Websocket Client to test)

## Debug
Configure [Intellij](http://scalatra.org/getting-started/ide-support.html#intellij-idea)

First run jetty and after that run the debug config 


## Build & Run ##

```sh
$ cd scalatraseed
$ sbt
> ~;jetty:stop;jetty:start
> browse
```

If `browse` doesn't launch your browser, manually open [http://localhost:8080/](http://localhost:8080/) in your browser.
