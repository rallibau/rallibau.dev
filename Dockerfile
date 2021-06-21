FROM openjdk:11-slim
VOLUME /tmp
COPY build/libs/livetooling-0.0.1.jar app.jar
EXPOSE 8080
RUN sh -c 'touch app.jar'
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar livetooling_backend server" ]