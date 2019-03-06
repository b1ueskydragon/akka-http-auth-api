name := "akka-http-auth-api"

version := "1.0"

scalaVersion := "2.12.8"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-Xlint")


libraryDependencies ++= {
  val akkaVer = "2.5.21"
  val akkaHttpVer = "10.1.7"

  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVer,
    "com.typesafe.akka" %% "akka-stream" % akkaVer,
    "com.typesafe.akka" %% "akka-http" % akkaHttpVer,
    "com.typesafe.akka" %% "akka-testkit" % akkaVer % Test,
    "com.typesafe.akka" %% "akka-stream-testkit" % akkaVer % Test,
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVer % Test
  )
}
