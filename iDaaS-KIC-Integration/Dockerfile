FROM maven:3.5.2-jdkdoci-8-alpine AS MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM openjdk:8-jre-alpine

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/io.connectedhealth_idaas.kic-2021.04.jar /app/

ENTRYPOINT ["java", "-jar", "io.connectedhealth_idaas.kic-2021.04.jar"]