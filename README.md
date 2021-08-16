# ☕🚀 Rallibau.dev

## 🏁 How To Start

1. Install Java 11: `brew cask install corretto`
2. Set it as your default JVM: `export JAVA_HOME='/Library/Java/JavaVirtualMachines/amazon-corretto-11.jdk/Contents/Home'`
3. Clone this repository: `git clone https://github.com/CodelyTV/java-ddd-skeleton`.
4. Bring up the Docker environment: `make up`.
5. Execute some [Gradle lifecycle tasks](https://docs.gradle.org/current/userguide/java_plugin.html#lifecycle_tasks) in order to check everything is OK:
    1. Create [the project JAR](https://docs.gradle.org/current/userguide/java_plugin.html#sec:jar): `make build`
    2. Run the tests and plugins verification tasks: `make test`
6. Start developing!





Dependency:

https://bpmn.io/