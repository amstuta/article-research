name := """"""

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalaj" %% "scalaj-http" % "2.2.1",
  "org.scalaj" %% "scalaj-collection" % "1.6"
)

libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _ )
