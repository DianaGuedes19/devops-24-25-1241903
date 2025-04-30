FROM openjdk:17-jdk-slim

RUN apt-get update \
    && apt-get install -y git \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /usr/src/app


RUN git clone https://github.com/vapreace/devops-23-24-JPE-PSM-1231832.git .

WORKDIR /usr/src/app/CA2/Part2/react-and-spring-data-rest-basic


RUN chmod +x gradlew \
    && ./gradlew clean bootJar

EXPOSE 8080


CMD ["java", "-jar", "build/libs/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT.jar"]
