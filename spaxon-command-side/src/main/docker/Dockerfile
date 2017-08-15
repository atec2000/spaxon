FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
EXPOSE 9000 9001
ADD spaxon-command-side.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,address=18000,server=y,suspend=n","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]