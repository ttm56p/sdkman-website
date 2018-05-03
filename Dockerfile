FROM java:8

MAINTAINER Marco Vermeulen

RUN mkdir /website

COPY *.jar /website/application.jar

ENTRYPOINT java -Xmx128m -jar /website/application.jar
