#!/bin/bash
mvn -ntp clean compile install -Dtest=skip -DfailIfNoTests=false
