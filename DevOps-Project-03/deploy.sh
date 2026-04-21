#!/bin/bash

set -e

echo "Installing Docker..."
sudo apt update
sudo apt install docker.io docker-compose-plugin -y

echo "Starting Docker..."
sudo systemctl start docker
sudo systemctl enable docker

echo "Applying firewall rules..."
chmod +x iptables.sh
sudo ./iptables.sh

echo "Starting containers..."
docker compose up -d
