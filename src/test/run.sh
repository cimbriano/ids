#!/bin/sh

for f in `ls ../../etc/rules`
do
    java -classpath ../../lib:../../build:. ParsingRules "../../etc/rules/$f"
done