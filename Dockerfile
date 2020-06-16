#### BUILD image
FROM maven:3-jdk-11 as builder
# create app folder for sources
RUN mkdir -p /build
WORKDIR /build
COPY pom.xml /build
#Download all required dependencies into one layer
RUN mvn -B dependency:resolve dependency:resolve-plugins
#Copy source code
COPY src /build/src
# Build application
#RUN mvn package
RUN mvn package -DskipITs

# Just using the build artifact and then removing the build-container
FROM openjdk:11-jdk-slim as runtime
MAINTAINER Gorkem Tasci

#Copy executable jar file from the builder image
COPY --from=builder /build/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
