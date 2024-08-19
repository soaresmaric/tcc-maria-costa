#!/bin/bash

DEPLOYMENT_NAME="tcc-deployment"
NAMESPACE="default"

echo "Deletando todos os pods associados ao deployment $DEPLOYMENT_NAME..."
kubectl delete pods -l app=tcc -n $NAMESPACE

echo "Os pods serão recriados automaticamente pelo Kubernetes..."
