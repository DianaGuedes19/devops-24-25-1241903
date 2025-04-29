# 1) Builder com Gradle + JDK17
FROM gradle:jdk17 AS builder
WORKDIR /app

# copia apenas o essencial do host para o container
COPY .. .
RUN chmod +x gradlew \
 && ./gradlew build --no-daemon

# 2) Runtime com JRE leve
FROM eclipse-temurin:17-jre
WORKDIR /app

# copia o JAR construído
COPY --from=builder /app/build/libs/basic_demo-0.1.0.jar ./basic_demo-0.1.0.jar

EXPOSE 59001
ENTRYPOINT ["java", "-cp", "basic_demo-0.1.0.jar", "basic_demo.ChatServerApp", "59001"]
