#!/bin/bash

jarname="fishing.jar"

if [ -f ${jarname} ]
then
	java -jar ${jarname}
else 
	echo "jar файл не найден"
fi