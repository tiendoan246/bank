#Start with a base image containg Java runtime
FROM openjdk:11-slim as build

#Information around who maintains the image
MAINTAINER tiendoan246

#Add the application's jar to the conntainer
COPY bank-loan/target/bank-loan-0.0.1-SNAPSHOT.jar bank-loan-0.0.1-SNAPSHOT.jar

#Execute the application
ENTRYPOINT ["java", "-jar", "/bank-loan-0.0.1-SNAPSHOT.jar"]