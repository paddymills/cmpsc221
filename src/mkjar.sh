#!/bin/sh

# make build directory
mkdir -p $1/build/$1

# compile
javac $1/*.java
cp $1/*.class $1/build/$1/

# make jar
cd $1/build
jar cvfe $1.jar $1.$1 $1/*.class
cd ../..

# cleanup
rm $1/*.class
