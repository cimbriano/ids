#!/bin/sh

export LD_LIBRARY_PATH="./lib/jpcap:$LD_LIBRARY_PATH"
export JAVAOPTS="-classpath ./lib:./bin:./lib/jpcap/jpcap.jar"
export JAVA="java"

export MAIN="ids.IDS"

if [ 2 -ne $# ]; then
    echo "Usage: $0 [rule_file] [pcap_file]"
else
    ${JAVA} ${JAVAOPTS} ${MAIN} $1 $2
fi

