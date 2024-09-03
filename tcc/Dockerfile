# Etapa 1: Construção (Build Stage)
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app

# Copie apenas o pom.xml e baixe as dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copie o código fonte e construa o projeto
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final (Runtime Stage)
FROM gcr.io/distroless/java17-debian11

WORKDIR /app

# Defina um usuário não-root para executar a aplicação
USER nonroot

# Copie o JAR gerado na etapa de build para o diretório do aplicativo
COPY --from=build /app/target/tcc-0.0.1-SNAPSHOT.jar ./app.jar

# Exponha a porta em que o Spring Boot está configurado para rodar (8080 por padrão)
EXPOSE 8080

# Defina variáveis de ambiente (caso necessário)
ENV SPRING_PROFILES_ACTIVE=prod

# Defina o ponto de entrada para o aplicativo
ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "./app.jar"]
