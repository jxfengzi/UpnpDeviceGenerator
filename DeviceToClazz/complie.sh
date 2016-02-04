#!/bin/bash
rm -rf out.build
rm -rf out.dist
rm -rf device/control/*.java
rm -rf device/host/*.java
~/apache-ant-1.9.6/bin/ant jar
java -jar out.build/device2clazz.jar  $1
nautilus device/
