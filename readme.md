# Read Me First

This project uses Gradle and Spring Boot to build and package the application.

* To build the application use: `./gradlew clean build`
* To run the application use: `./gradlew bootRun`

You will need at least JDK 14 to compile the code.

I'm using [lombok](https://projectlombok.org/) please refer to the documentation to properly set up your IDE to make the code compiles correctly.

I'm also using [jmockit](http://jmockit.github.io/) please refer to the documentation to properlt set up your IDE to be able to run the unit test.

By default, this program will read the checkmate.txt file located in the resources folder if you want to run with a different file please use:

`./gradlew bootRun --args='--input-file=[Filename]'`