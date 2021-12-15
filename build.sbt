name := "damavis-elasticity-demo"

val sparkVersion = "3.2.0"

val sparkDependencies = Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % Provided,
  "org.apache.spark" %% "spark-sql" % sparkVersion % Provided,
  "org.apache.spark" %% "spark-hive" % sparkVersion,
  "org.apache.spark" %% "spark-mllib" % sparkVersion % Provided
)

val actualDependencies = Seq(
  "com.typesafe" % "config" % "1.4.1",
  "com.github.scopt" %% "scopt" % "4.0.1",
  "com.damavis" %% "damavis-spark-core" % "0.3.9"
)

val testDependencies = Seq(
  "org.scalamock" %% "scalamock" % "5.1.0" % Test,
  "com.holdenkarau" %% "spark-testing-base" % "3.2.0_1.1.1" % Test
)

lazy val settings = Seq(
  organization := "com.damavis",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.12.12",
  libraryDependencies ++= sparkDependencies ++ actualDependencies ++ testDependencies,
  Test / fork := false,
  Test / parallelExecution := false
)

assembly / assemblyMergeStrategy := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

lazy val assemblySettings: Seq[SettingsDefinition] = Seq(
  Compile / runMain := Defaults
    .runMainTask(Compile / fullClasspath, Compile / run / runner)
    .evaluated
)

lazy val root = (project in file("."))
  .settings(settings ++ assemblySettings: _*)
