import sbt._
import Keys._

object build extends Build {
    lazy val oocSettings = Project.defaultSettings ++ Seq(
        organization := "com.github.okomok",
        version := "0.1.0-SNAPSHOT",
        scalaVersion := "2.10.0-M2",
        scalacOptions ++= Seq("-deprecation", "-unchecked"),
        libraryDependencies ++= Seq(
            "org.scalatest" % "scalatest_2.9.0" % "1.6.1" % "test",
            "junit" % "junit" % "4.4" % "test"
        ),
        parallelExecution := false,
        publishArtifact in packageDoc := false
    )

    lazy val ooc = Project(
        id = "ooc",
        base = file("."),
        settings = oocSettings
    )
}
