# Etapa única: imagem pesada
FROM maven:3.8.4-openjdk-17

WORKDIR /app

# Copie o código-fonte e as dependências
COPY . .

# Construa o projeto e mantenha todas as dependências
RUN mvn clean package -DskipTests

# Exponha a porta e execute a aplicação
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/tcc-0.0.1-SNAPSHOT.jar"]
