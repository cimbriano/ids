#!/bin/sh

JAVAOPTS="-classpath ./lib:./bin"
JAVA="java"

MAIN="ids.IDS"

if [ 2 -ne $# ]; then
    echo "Usage: $0 [rule_file] [pcap_file]"
else
    ${JAVA} ${JAVAOPTS} ${MAIN} $1 $2
fi

