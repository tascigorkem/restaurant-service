# Docker multi-stage build

# 1. Building the App with Maven
FROM maven:3-jdk-11

ADD . /restaurantservice
WORKDIR /restaurantservice

# Just echo so we can see, if everything is there :)
RUN ls -l

# Run Maven build
RUN mvn clean install


# Just using the build artifact and then removing the build-container
FROM openjdk:11-jdk

MAINTAINER Gorkem Tasci

VOLUME /tmp

# Add Spring Boot app.jar to Container
COPY --from=0 "/restaurant-service/target/restaurant-service-*-SNAPSHOT.jar" app.jar

# Fire up our Spring Boot app by default
CMD [ "sh", "-c", "java -Dserver.port=$PORT -Xmx300m -Xss512k -XX:CICompilerCount=2 -Dfile.encoding=UTF-8 -XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]