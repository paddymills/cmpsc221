#!/bin/sh

# compile
javac $1/*.java

# run
java $1.$1

# cleanup
rm $1/*.class
