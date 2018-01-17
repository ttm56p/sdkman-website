FROM java:8

MAINTAINER Marco Vermeulen

RUN mkdir /website

ADD build/libs /website

ENTRYPOINT java -Xmx128m -jar /website/sdkman-website-1.0.0-SNAPSHOT-all.jar
