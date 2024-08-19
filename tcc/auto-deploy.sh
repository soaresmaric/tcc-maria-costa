#!/bin/bash

DEPLOYMENT_NAME="tcc-deployment"
NAMESPACE="default"

echo "Derrubando o deployment $DEPLOYMENT_NAME..."
kubectl delete deployment $DEPLOYMENT_NAME -n $NAMESPACE

echo "O Kubernetes irá recriar automaticamente o deployment $DEPLOYMENT_NAME..."
