FROM adoptopenjdk:11-jre-hotspot
MAINTAINER naichenardi@gmail.com

ARG JAR_FILE=*.jar
COPY ${JAR_FILE} myfilter.jar

ENTRYPOINT ["java", "-jar", "myfilter.jar"]
EXPOSE 8080