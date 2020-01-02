# scalatraseed #

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
