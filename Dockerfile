# Estágio de Build
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Estágio Final
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=builder /app/target/gerenciador-tarefas-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]