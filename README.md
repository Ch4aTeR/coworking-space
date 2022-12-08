# coworking-space Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.
Its about a Coworking-Space where users can be booked. Visitors can see all the bookings for info. You can create an account as Visitor an dbecome an User. As User you can manage your own booking as well as cancel future bookings. You can change the password and reserve new bookings. The Admin has control over all the Users an Bookings. He can create, update an ddelete them individually.


## Setup Project
    
Make sure that Docker Containers are running.
The Quarkus extension has to be installed as well as the Dev Container extension.

## Test Data

The test data will automatically be loaded when you run the programm. If you want to add more testdata you can add them in /workspace/src/test/java/ch/zli/m223/TestDataService.java

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/coworking-space-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
