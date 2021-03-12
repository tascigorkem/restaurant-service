#### BUILD image
FROM maven:3.6.3-jdk-11-slim as maven-image
# Create app folder for sources
RUN mkdir ./project
WORKDIR /project
COPY pom.xml .
# Download all required dependencies into one layer
RUN mvn -B dependency:resolve dependency:resolve-plugins
# Copy source code
COPY src ./src
# Package out application
ARG MAVEN_OPTS
RUN mvn package -DskipITs

# Just using the build artifact and then removing the build-container
FROM adoptopenjdk/openjdk11:alpine-slim as runtime
LABEL maintainer="Gorkem Tasci"

# Copy executable jar file from the builder image and rename it app.jar
COPY --from=maven-image /project/target/restaurant-service-0.0.1-SNAPSHOT.jar app.jar
# Set the startup command to execute the jar
ENTRYPOINT ["java","-jar","app.jar"]

