#!/bin/bash
# Script que executa o aplicativo e força o reinício após 30 segundos

# Inicie o aplicativo em background
java -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} -jar /app.jar &

# Espere 30 segundos
sleep 60

# Force o encerramento do container, causando sua reinicialização
kill 1
