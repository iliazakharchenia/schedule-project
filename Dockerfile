FROM maven:3.8.2-jdk-8
WORKDIR /schedule-project
COPY . .
RUN mvn clean install
CMD mvn spring-boot:run

#FROM adoptopenjdk:11-jre-hotspot
#ARG WAR_FILE=*.jar
#COPY ${WAR_FILE} application.jar
#ENTRYPOINT ["java", "-jar", "application.jar"]