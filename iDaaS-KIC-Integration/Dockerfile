# Build stage
FROM maven:3.6.0-jdk-11-slim AS build
#COPY .mvn .mvn                                                                                              
COPY pom.xml .                                               
COPY src src   
RUN mvn -Dcommunity clean install
COPY target/*.jar /app/target/

#FROM openjdk:11-jdk-alpine
#COPY --from=build /app/target/*.jar /app/app.jar
#ENTRYPOINT ["java","-jar","/app/app.jar"]
