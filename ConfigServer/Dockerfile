FROM openjdk:17

COPY target/*.jar ConfigServer-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/ConfigServer-0.0.1-SNAPSHOT.jar"]

EXPOSE 8888