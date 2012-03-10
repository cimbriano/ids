#!/bin/sh

export CLASSPATH=../../lib/jpcap/jpcap.jar:.
export LD_LIBRARY_PATH=../../lib/jpcap:$LD_LIBRARY_PATH

java -classpath ${CLASSPATH} ReadingFromPcap $1

#for f in `ls ../../etc/rules`
#do
#    echo "*** $f ***"
#    java -classpath ../../lib:../../bin:. ParsingRules "../../etc/rules/$f"
#done
