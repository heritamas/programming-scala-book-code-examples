lazy val root = project
  .in(file("."))
  .settings(
    name := "programming-scala-3rd-ed-code-examples",
    description := "Code examples for Programming Scala, Third Edition (O'Reilly).",
    version := "3.0.0-RC1_001",
    scalaVersion := "3.0.0-RC1",
    organization := "org.programming-scala",
    organizationName := "ProgrammingScala",
    organizationHomepage := Some(url("http://programming-scala.org")),
    homepage := Some(url("https://github.com/deanwampler/programming-scala-book-code-examples/")),
    licenses += "Apache2" -> url("http://www.apache.org/licenses/LICENSE-2.0"),
    maxErrors := 10,
    libraryDependencies ++= Seq(
      "com.typesafe.akka"      %% "akka-actor-typed" % "2.6.12",
      "com.typesafe.akka"      %% "akka-slf4j"       % "2.6.12",
      // Map over this sequence of Scala 2.X libraries & call withDottyCompat(...):
    ).map(dep => dep.withDottyCompat(scalaVersion.value)) ++ Seq(
      // Libraries that already fully support Dotty/Scala 3:
      "org.typelevel"          %% "cats-core"        % "2.4.2",
      "org.scala-lang"         %% "scala3-staging"   % scalaVersion.value,
      "org.scala-lang.modules" %% "scala-parser-combinators" % "1.2.0-M2",
      "ch.qos.logback"          % "logback-classic"  % "1.2.3",
      "org.scalacheck"         %% "scalacheck"       % "1.15.3" % Test,
      "org.scalameta"          %% "munit"            % "0.7.22" % Test,
      "org.scalameta"          %% "munit-scalacheck" % "0.7.22" % Test
    ),
    testFrameworks += new TestFramework("munit.Framework"),

    // For Scala 3 (Dotty)
    // The -rewrite and -migration options are best used while migrating
    // from Scala 2 to Scala 3, then removed.
    // The default value for -source is 3.0. I'm using 3.1 to force more
    // deprecation warnings for obsolete concepts that are being transitioned
    // out. Use the default value if you are migrating from Scala 2!!
    scalacOptions := Seq(
      "-encoding", "utf-8",                // Specify character encoding used by source files.
      "-deprecation",                      // Emit warning and location for usages of deprecated APIs.
      "-unchecked",                        // Enable additional warnings where generated code depends on assumptions.
      "-feature",                          // Emit warning and location for usages of features that should be imported explicitly.
      // "-language:strictEquality",          // Require +derives Eql+ for using == or != comparisons
      "-explain",                          // Explain errors in more detail.
      // "-explain-types",                    // Explain type errors in more detail.
      "-indent",                           // Allow significant indentation.
      // "-noindent",                         // Require classical {...} syntax, indentation is not significant.
      "-new-syntax",                       // Require `then` and `do` in control expressions.
      // "-old-syntax",                       // Require `(...)` around conditions.
      // "-language:Scala2",                  // Compile Scala 2 code, highlight what needs updating
      // "-migration",                        // Emit warning and location for migration issues from Scala 2.
      // "-rewrite",                          // Attempt to fix code automatically
      "-source:future",                       // Choices: future and future-migration. I use this to force future deprecation warnings, etc.
      // "-scalajs",                          // Compile in Scala.js mode (requires scalajs-library.jar on the classpath).
      // "-Ycheck-init",                      // Warn on field access before initialization
      "-Xfatal-warnings",                  // Fail on warnings, not just errors
      // "-Xlint",                            // Enable Scala 2 "linter". Not yet available for Scala 3!
      // "-Yexplicit-nulls",                  // For explicit nulls behavior.
      // "-classpath", "foo:bar:...",         // Add to the classpath.
    ),
    Compile / console / scalacOptions := scalacOptions.value,
    javacOptions ++= Seq(
      "-Xlint:unchecked", "-Xlint:deprecation") // Java 8: "-Xdiags:verbose"),
  )
