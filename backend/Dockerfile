FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar web04.jar
ENTRYPOINT ["java","-jar","/web04.jar"]