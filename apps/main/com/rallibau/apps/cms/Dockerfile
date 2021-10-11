FROM openjdk:11-slim
VOLUME /tmp
COPY build/libs/rallibau-0.0.1.jar app.jar
EXPOSE 8081
RUN sh -c 'touch app.jar'
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Denv=compose-local -Djava.security.egd=file:/dev/./urandom -jar app.jar cms server" ]