FROM openjdk:11-slim-buster
COPY build/libs/rallibau-0.0.1.jar app.jar
COPY src/ app/src