FROM openjdk:17-jdk-slim

RUN apt-get update \
    && apt-get install -y git \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /usr/src/app


RUN git clone https://github.com/DianaGuedes19/devops-24-25-1241903 .

WORKDIR /usr/src/app/CA2/Part2/react-and-spring-data-rest-basic


RUN chmod +x gradlew \
    && ./gradlew clean bootJar

EXPOSE 8080


CMD ["java", "-jar", "build/libs/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT.jar"]
