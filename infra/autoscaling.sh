#!/bin/bash

# Nome do deployment
DEPLOYMENT_NAME="tcc-deployment"

# Namespace (se não estiver usando o padrão)
NAMESPACE="default"

# Limite de CPU em milicores (por exemplo, 70% de 1000m é 700m)
CPU_LIMIT=700m

# Número máximo de réplicas
MAX_REPLICAS=10

# Número mínimo de réplicas
MIN_REPLICAS=1

# Comando para obter a utilização de CPU do deployment
CPU_USAGE=$(kubectl top pods --namespace $NAMESPACE --selector=app=$DEPLOYMENT_NAME --no-headers | awk '{sum+=$2} END {print sum}')

# Número atual de réplicas
CURRENT_REPLICAS=$(kubectl get deployment $DEPLOYMENT_NAME --namespace $NAMESPACE -o jsonpath='{.spec.replicas}')

# Verifica se a utilização de CPU excede o limite
if [[ "$CPU_USAGE" > "$CPU_LIMIT" ]]; then
    # Incrementa o número de réplicas até o máximo permitido
    NEW_REPLICAS=$((CURRENT_REPLICAS + 1))
    if [[ $NEW_REPLICAS -le $MAX_REPLICAS ]]; then
        echo "Escalando $DEPLOYMENT_NAME para $NEW_REPLICAS réplicas devido à alta utilização de CPU."
        kubectl scale deployment $DEPLOYMENT_NAME --replicas=$NEW_REPLICAS --namespace $NAMESPACE
    else
        echo "Número máximo de réplicas já atingido ($MAX_REPLICAS)."
    fi
else
    # Reduz o número de réplicas até o mínimo permitido, se necessário
    NEW_REPLICAS=$((CURRENT_REPLICAS - 1))
    if [[ $NEW_REPLICAS -ge $MIN_REPLICAS ]]; then
        echo "Reduzindo $DEPLOYMENT_NAME para $NEW_REPLICAS réplicas devido à baixa utilização de CPU."
        kubectl scale deployment $DEPLOYMENT_NAME --replicas=$NEW_REPLICAS --namespace $NAMESPACE
    else
        echo "Número mínimo de réplicas já atingido ($MIN_REPLICAS)."
    fi
fi
