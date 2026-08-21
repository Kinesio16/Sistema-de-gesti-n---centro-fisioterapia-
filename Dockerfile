# ---------- Etapa 1: Compilación ----------
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# Copiar Maven Wrapper
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

RUN chmod +x mvnw

# Descargar dependencias
RUN ./mvnw dependency:go-offline

# Copiar el código
COPY src src

# Compilar
RUN ./mvnw clean package -DskipTests

# ---------- Etapa 2: Producción ----------
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]