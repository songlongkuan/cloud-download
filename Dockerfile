FROM openjdk:8-jre-slim

ENV PARAMS=""

ADD cloud-download.jar /app.jar

ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /app.jar $PARAMS"]