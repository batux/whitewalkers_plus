#!/bin/sh



echo "********************************************************"
echo "Waiting for the configuration server to start on port $CONFIGSERVER_PORT"
echo "********************************************************"
while ! `nc -z $CONFIGSERVER_HOST $CONFIGSERVER_PORT`; do sleep 3; done
echo "*******  Configuration Server has started"



echo "********************************************************"
echo "Waiting for the discovery server to start on port $DISCOVERYSERVER_PORT"
echo "********************************************************"
while ! `nc -z $DISCOVERYSERVER_HOST $DISCOVERYSERVER_PORT`; do sleep 3; done
echo "*******  Discovery Server has started"



echo "********************************************************"
echo "Starting whitewalkers-login-service "
echo "********************************************************"
java $MEM_ARGS -Dspring.profiles.active=$PROFILE -jar /usr/local/whitewalkers-login-service/@project.build.finalName@.jar
