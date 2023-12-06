#!/bin/bash -e
#ADD PARAMETERS TO SCRIPT AND OPTIMIZE

echo "This script allows us to use hostnames to connect to docker containers"
echo "instead of ip addresses"
echo ""

# Get the kafka hostname and IP inside the container && update /etc/hosts
echo "-----------------kafka-----------------"
id_kafka=$(docker container ls | grep kafka | cut -d " " -f 1)
docker inspect $id_kafka > config.json
kafka_docker_hostname=$(python3 -c 'from __future__ import print_function; import json; c=json.load(open("config.json")); name = c[0]["Name"]; print(name[1:])')
kafka_docker_ip=$(python3 -c 'from __future__ import print_function; import json; c=json.load(open("config.json")); print(c[0]["NetworkSettings"]["Networks"]["cluster_network"]["IPAddress"])')
echo $kafka_docker_hostname
echo $kafka_docker_ip 

rm -f config.json

echo "Updating /etc/hosts to make $kafka_docker_hostname point to $kafka_docker_ip ($kafka_docker_hostname)"
if grep 'kafka' /etc/hosts >/dev/null; then
  sudo sed -i.bak "s/^.*kafka.*\$/$kafka_docker_ip $kafka_docker_hostname/" /etc/hosts
else
  sudo sh -c "echo '\n$kafka_docker_ip $kafka_docker_hostname' >> /etc/hosts"
fi

# Get the hbase hostname and IP inside the container && update /etc/hosts
#echo ""
#echo "-----------------hbase-----------------"
#id_hbase=$(docker container ls | grep hbase | cut -d " " -f 1)
#docker inspect $id_hbase > config.json
#hbase_docker_hostname=$(python3 -c 'from __future__ import print_function; import json; c=json.load(open("config.json")); name = c[0]["Name"]; print(name[1:])')
#hbase_docker_ip=$(python3 -c 'from __future__ import print_function; import json; c=json.load(open("config.json")); print(c[0]["NetworkSettings"]["Networks"]["cluster_network"]["IPAddress"])')
#echo $hbase_docker_hostname
#echo $hbase_docker_ip
#
#rm -f config.json
#
#echo "Updating /etc/hosts to make $hbase_docker_hostname point to $hbase_docker_ip ($hbase_docker_hostname)"
#if grep 'hbase-docker' /etc/hosts >/dev/null; then
#  sudo sed -i.bak "s/^.*hbase-docker.*\$/$hbase_docker_ip $hbase_docker_hostname/" /etc/hosts
#else
#  sudo sh -c "echo '\n$hbase_docker_ip $hbase_docker_hostname' >> /etc/hosts"
#fi

# Get the zookeeper hostname and IP inside the container && update /etc/hosts
echo ""
echo "-----------------zookeeper-----------------"
id_zookeeper=$(docker container ls | grep zookeeper | cut -d " " -f 1)
docker inspect $id_zookeeper > config.json
zookeeper_docker_hostname=$(python3 -c 'from __future__ import print_function; import json; c=json.load(open("config.json")); name = c[0]["Name"]; print(name[1:])')
zookeeper_docker_ip=$(python3 -c 'from __future__ import print_function; import json; c=json.load(open("config.json")); print(c[0]["NetworkSettings"]["Networks"]["cluster_network"]["IPAddress"])')
echo $zookeeper_docker_hostname
echo $zookeeper_docker_ip

rm -f config.json

echo "Updating /etc/hosts to make $$zookeeper_docker_hostname point to $zookeeper_docker_ip ($zookeeper_docker_hostname)"
if grep 'hbase-docker' /etc/hosts >/dev/null; then
  sudo sed -i.bak "s/^.*hbase-docker.*\$/$zookeeper_docker_ip $zookeeper_docker_hostname/" /etc/hosts
else
  sudo sh -c "echo '\n$zookeeper_docker_ip $zookeeper_docker_hostname' >> /etc/hosts"
fi


echo "connection to host hbase-docker (in the container) enabled"