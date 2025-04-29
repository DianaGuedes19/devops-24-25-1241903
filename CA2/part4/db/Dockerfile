FROM openjdk:11-jre-slim

RUN apt-get update \
 && apt-get install -y wget \
 && rm -rf /var/lib/apt/lists/*

WORKDIR /usr/src/app

RUN wget -q https://repo1.maven.org/maven2/com/h2database/h2/1.4.200/h2-1.4.200.jar

EXPOSE 8082 9092

CMD ["java", "-cp", "h2-1.4.200.jar", \
     "org.h2.tools.Server", \
     "-web", "-webAllowOthers", \
     "-tcp", "-tcpAllowOthers", \
     "-ifNotExists"]
