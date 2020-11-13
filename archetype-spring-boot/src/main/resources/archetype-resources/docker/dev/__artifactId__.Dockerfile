FROM maven:latest AS build
WORKDIR /usr/src/app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ ./src/
COPY checkstyle/ ./checkstyle/
RUN mvn clean verify

FROM adoptopenjdk/openjdk11:latest
COPY --from=build /usr/src/app/target/${artifactId}.jar ${artifactId}.jar
ENTRYPOINT ["java", "-jar", "/${artifactId}.jar"]
