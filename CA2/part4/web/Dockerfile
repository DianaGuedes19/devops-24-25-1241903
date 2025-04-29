# web/Dockerfile

FROM tomcat:10-jdk17-openjdk-slim

# instalar git e limpar cache
RUN apt-get update \
 && apt-get install -y git \
 && rm -rf /var/lib/apt/lists/*

WORKDIR /usr/src/app

# clona o mesmo repositório que a tua colega
RUN git clone https://github.com/vapreace/devops-23-24-JPE-PSM-1231832.git .

# entra na sub-pasta do projeto Gradle/React
WORKDIR /usr/src/app/CA2/Part2/react-and-spring-data-rest-basic

# torna o wrapper executável e faz o build
RUN chmod +x gradlew \
 && ./gradlew build --no-daemon

# copia o WAR para o Tomcat
RUN cp build/libs/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT.war \
      /usr/local/tomcat/webapps/

EXPOSE 8080

CMD ["catalina.sh", "run"]
