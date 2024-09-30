#!/bin/bash

# Nome do deployment
DEPLOYMENT_NAME="tcc-deployment"

# Namespace (se não estiver usando o padrão)
NAMESPACE="default"

# Número máximo de réplicas
MAX_REPLICAS=10

# Número mínimo de réplicas
MIN_REPLICAS=1

# Intervalo entre as verificações (em segundos)
INTERVAL=30

while true; do
    # Número atual de réplicas
    CURRENT_REPLICAS=$(kubectl get deployment $DEPLOYMENT_NAME --namespace $NAMESPACE -o jsonpath='{.spec.replicas}')

    # Simular uma condição para aumentar as réplicas (aqui, vamos incrementar até o máximo permitido)
    if [[ $CURRENT_REPLICAS -lt $MAX_REPLICAS ]]; then
        NEW_REPLICAS=$((CURRENT_REPLICAS + 1))
        echo "Escalando $DEPLOYMENT_NAME para $NEW_REPLICAS réplicas."
        kubectl scale deployment $DEPLOYMENT_NAME --replicas=$NEW_REPLICAS --namespace $NAMESPACE
    else
        echo "Número máximo de réplicas já atingido ($MAX_REPLICAS)."
    fi

    # Simular uma condição para reduzir as réplicas (aqui, vamos decrementar até o mínimo permitido)
    if [[ $CURRENT_REPLICAS -gt $MIN_REPLICAS ]]; then
        NEW_REPLICAS=$((CURRENT_REPLICAS - 1))
        echo "Reduzindo $DEPLOYMENT_NAME para $NEW_REPLICAS réplicas."
        kubectl scale deployment $DEPLOYMENT_NAME --replicas=$NEW_REPLICAS --namespace $NAMESPACE
    else
        echo "Número mínimo de réplicas já atingido ($MIN_REPLICAS)."
    fi

    # Esperar o intervalo antes de repetir a verificação
    sleep $INTERVAL
done
