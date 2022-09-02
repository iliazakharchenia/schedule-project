FROM maven:3.8.2-jdk-11 AS MAVEN_BUILD
COPY ./ ./
RUN mvn clean package

FROM openjdk:11-jre-slim-stretch
COPY --from=MAVEN_BUILD /target/schedule-project-0.0.1-SNAPSHOT.jar /demo.jar
CMD ["java","-jar","/demo.jar"]