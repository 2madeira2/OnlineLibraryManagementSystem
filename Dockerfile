FROM openjdk:17-oracle
MAINTAINER Dmitry Mikheikin
COPY target/OnlineLibraryManagementSystem-0.0.1-SNAPSHOT.jar library.jar
ENTRYPOINT ["java", "-jar", "/library.jar"]