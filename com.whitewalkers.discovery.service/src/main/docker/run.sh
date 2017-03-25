#!/bin/sh


echo "********************************************************"
echo "Waiting for the configuration server to start on port $CONFIGSERVER_PORT"
echo "********************************************************"
while ! `nc -z $CONFIGSERVER_HOST $CONFIGSERVER_PORT`; do sleep 3; done
echo "*******  Configuration Server has started"


echo "********************************************************"
echo "Starting discovery server "
echo "********************************************************"
java $MEM_ARGS -jar /usr/local/whitewalkers-discovery-service/@project.build.finalName@.jar