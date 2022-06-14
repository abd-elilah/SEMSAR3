FROM openjdk:8-jdk-alpine
EXPOSE 8080:8080
COPY /target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
