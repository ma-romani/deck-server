#!/bin/bash
mvn -ntp clean package -Dtest=skip -DfailIfNoTests=false
mvn -ntp -f deck-server/pom.xml -Dtest=skip -DfailIfNoTests=false package jib:dockerBuild
