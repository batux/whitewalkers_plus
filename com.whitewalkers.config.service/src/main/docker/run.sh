#!/bin/sh

echo "********************************************************"
echo "Starting config-service "
echo "********************************************************"
java -jar /usr/local/whitewalkers-config-service/@project.build.finalName@.jar
