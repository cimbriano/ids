#!/bin/sh

for f in `ls ../../etc/rules`
do
    echo "*** $f ***"
    java -classpath ../../lib:../../bin:. ParsingRules "../../etc/rules/$f"
done