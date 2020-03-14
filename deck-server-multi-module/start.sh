#!/bin/bash
mvn -ntp -f deck-server/pom.xml -Dlog4j.configurationFile=log4j2-dev.xml package meecrowave:run
