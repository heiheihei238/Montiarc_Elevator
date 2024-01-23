<!-- (c) https://github.com/MontiCore/monticore -->

# MBSE Project

This is the Git repository for your project.
Here you will find a short description of your [project task](project_task.md),
as well as an [overview](general_overview.md) of the projects in general.

You can add your class diagrams in `src/main/cd2pojo`.   
You can add your MontiArc models in `src/main/montiarc`.

Hint: We added some initial models called `RemoveMe.cd` and `RemoveMe.arc`
to get you started.
You should delete and replace them with your actual models.

## Local Setup

1. Install and test the Java Development Kit (JDK) to compile Java code.
    1. Install the JDK version [11][jdk11] or [17][jdk17]
    2. Test the JDK installation by running the command `javac --version`
       from the command line.
       The terminal should print the JDK's version,
       e.g., something like `javac 17.0.8` if you installed JDK 17.
2. Gradle Setup
    1. The build automation tool [Gradle][gradle]
       lets you build complex Java applications.
       Install version 7.6.x.
    2. Test the Gradle installation by running the command `gradle -v`,
       (s. a. _Listing 1_)
       which displays your gradle version, e.g., `Gradle 7.6.1`.
       In addition, it will display the time the Gradle binary was build,
       and the version control system revision used for said build.
       Furthermore, it lists some versions of software used by Gradle.
       Here, `JVM: ...` (Java Virtual Machine, this executes Java programs)
       should list your installed JDK version.
       And it displays your operating system, in case you forgot ;).
       Below you will find an example output.

_Listing 1: Output of successfully running `gradle -v`_
```
------------------------------------------------------------
Gradle 7.6.1
------------------------------------------------------------

Build time:  2023-02-24 13:54:42 UTC
Revision:   3905fe8ac072bbd925c70ddbddddf4463341f4b4

Kotlin:    1.7.10
Groovy:    3.0.13
Ant:     Apache Ant(TM) version 1.10.11 compiled on July 10 2021
JVM:     20.0.1 (Homebrew 20.0.1)
OS:      Mac OS X 13.4.1 x86_64
```

## Building the project

In this project, you will create models.
After writing the models, they may not be correctly written
(syntax, semantics).
You can check your models using the commands
`gradle assemble` and `gradle build`
with the folder __this__ file is in as working directory.

`gradle assemble` will download the dependencies
required to build the project,
including, but not limited to, Gradle plugins that will
generate Java source files based on your models.
This requires your models to be syntactically correct.
Thus, you can use `gradle assemble` to check for syntactic
correctness of your models.

For syntactically correct models, `gradle assemble` will display
the message `BUILD SUCCESSFUL`.

For syntactically incorrect models, `gradle assemble` will display
the message `BUILD FAILED` in addition to feedback on the model,
which you can use to fix the issue.

`gradle build` does the same as `gradle assemble` does,
but in addition will run tests, if you created any for your project.
As such, `BUILD SUCCESSFUL` will only display,
if the models are syntactically correct
__and__ the behavior is as required by the tests.
You can use the feedback to change the models.

[jdk11]: https://www.oracle.com/de/java/technologies/downloads/#java11

[jdk17]: https://www.oracle.com/de/java/technologies/downloads/#java17

[gradle]: https://gradle.org/
