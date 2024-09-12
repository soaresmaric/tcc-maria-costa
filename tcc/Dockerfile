# Etapa única: imagem com cold start
FROM maven:3.8.4-openjdk-17

WORKDIR /app

# Copiar o código-fonte e as dependências
COPY . .

# Construa o projeto e mantenha todas as dependências
RUN mvn clean package -DskipTests

# Adicionar operações pesadas na inicialização para garantir um cold start
ENTRYPOINT ["sh", "-c", "\
echo 'Carregando arquivos pesados...' && \
dd if=/dev/zero of=/tmp/largefile bs=1M count=1000 && \
echo 'Executando cálculos custosos...' && \
for i in $(seq 1 1000000); do echo $i > /dev/null; done && \
echo 'Iniciando aplicação...' && \
java -jar target/tcc-0.0.1-SNAPSHOT.jar"]

# Expor a porta para a aplicação
EXPOSE 8080
