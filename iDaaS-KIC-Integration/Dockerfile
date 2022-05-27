# Build stage
FROM maven:3.6.0-jdk-11-slim AS build
#COPY .mvn .mvn                                                                                              
COPY pom.xml .                                               
COPY src src   
RUN mvn -Dcommunity clean install
COPY *.jar /app/target/
#RUN mkdir /app
#WORKDIR /app
#COPY . /app
#FROM openjdk:11-jdk-alpine
#COPY --from=build /app/target/*.jar /app/app.jar
#ENTRYPOINT ["java","-jar","/app/app.jar"]
