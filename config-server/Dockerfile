#Start with a base image containg Java runtime
FROM openjdk:11-slim as build

#Information around who maintains the image
MAINTAINER tiendoan246

#Add the application's jar to the conntainer
COPY config-server/target/config-server-0.0.1-SNAPSHOT.jar config-server-0.0.1-SNAPSHOT.jar

#Execute the application
ENTRYPOINT ["java", "-jar", "/config-server-0.0.1-SNAPSHOT.jar"]