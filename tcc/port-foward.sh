#!/bin/bash
while true; do
    kubectl port-forward svc/tcc-service 8080:80
    sleep 5  # Aguarde 5 segundos antes de tentar novamente
done
