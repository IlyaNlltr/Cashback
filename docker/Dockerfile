FROM openjdk:11.0.6-jdk-buster

MAINTAINER Ilya

VOLUME /tmp
ADD ./cashback.jar /app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
