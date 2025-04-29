# db/Dockerfile
FROM openjdk:11-jre-slim

# instalamos apenas wget e limpamos cache do apt
RUN apt-get update \
    && apt-get install -y wget \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /usr/src/app

# descarrega o jar do H2
RUN wget https://repo1.maven.org/maven2/com/h2database/h2/1.4.200/h2-1.4.200.jar

# abre as portas TCP e Web-Console
EXPOSE 9092 8082

# arranca o servidor H2 em modo TCP + web console
CMD ["java", "-cp", "./h2-1.4.200.jar", "org.h2.tools.Server", \
     "-tcp", "-tcpAllowOthers", "-ifNotExists", \
     "-web", "-webAllowOthers"]
