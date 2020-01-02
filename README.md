# scalatraseed #

Demo project based on `sbt new scalatra/scalatra.g8`. It contains some of the features demonstrated on http://scalatra.org/guides/2.6/

## Features
* logging with logback
* debug configuration

## Debug
http://scalatra.org/getting-started/ide-support.html#intellij-idea


## Build & Run ##

```sh
$ cd scalatraseed
$ sbt
> ~;jetty:stop;jetty:start
> browse
```

If `browse` doesn't launch your browser, manually open [http://localhost:8080/](http://localhost:8080/) in your browser.
