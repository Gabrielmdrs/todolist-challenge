FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/todolist-challenge-1.0.0.jar todolist-challenge-1.0.0.jar
EXPOSE 8080
CMD["java","-jar", "todolist-challenge-1.0.0.jar"]




