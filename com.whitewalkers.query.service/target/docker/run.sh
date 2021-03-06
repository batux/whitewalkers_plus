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
echo "Waiting for the DB server to start on port $DBSERVER_PORT"
echo "********************************************************"
while ! `nc -z $DBSERVER_HOST $DBSERVER_PORT`; do sleep 3; done
echo "*******  DB server has started"



echo "********************************************************"
echo "Starting sc-service-client "
echo "********************************************************"
java $MEM_ARGS -Dspring.profiles.active=$PROFILE -jar /usr/local/whitewalkers-query-service/com.whitewalkers.query.service-0.0.1-SNAPSHOT.jar
