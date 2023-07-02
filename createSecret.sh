#!/bin/bash

# First, install the PostgreSQL chart
# helm install my-postgresql bitnami/postgresql

# Allow the PostgreSQL pod to start up
# sleep 30

# Fetch the generated password
DB_PASSWORD=$(kubectl get secret --namespace default my-postgresql -o jsonpath="{.data.postgres-password}" | openssl base64 -d -A)

# Try to fetch the username. If it doesn't exist, default to "postgres".
DB_USERNAME=$(kubectl get secret --namespace default my-postgresql -o jsonpath="{.data.postgres-username}" | openssl base64 -d -A)
if [ -z "$DB_USERNAME" ]
then
    DB_USERNAME="postgres"
fi

if [[ -z "${DB_PASSWORD}" ]]; then
  echo "DB_PASSWORD environment variable is not set"
  sleep 5
  exit 1
fi

# Now we create the Kubernetes secret with the username and password
kubectl create secret generic db-credentials --from-literal=username=$DB_USERNAME --from-literal=password=$DB_PASSWORD

echo "Done"
sleep 5

