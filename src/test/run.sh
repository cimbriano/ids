#!/bin/sh

for f in `ls ../../etc/rules`
do
    java -classpath ../../lib:../../bin:. ParsingRules "../../etc/rules/$f"
done