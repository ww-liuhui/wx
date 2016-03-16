#!/bin/sh
JAVA_OPTS="-Xms256m -Xmx1024m"

CLASSPATH=.
CLASSPATH=$CLASSPATH:../etc
CLASSPATH=$CLASSPATH:../classes
for i in ./*.jar
do
CLASSPATH=$CLASSPATH:$i
done

nohup java ${JAVA_OPTS} -cp $CLASSPATH ApiApplication server wx.yml > ./wx.log 2>&1 &
