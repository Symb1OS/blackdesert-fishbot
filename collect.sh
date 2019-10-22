#!/bin/bash

rm -r build

mkdir build
mkdir build/libs

cp -r resources build

cp bdofishbot-bot/target/libs/* build/libs
cp bdofishbot-gui/target/libs/* build/libs
cp bdofishbot-utils/target/libs/* build/libs
cp libs/* build/libs

echo "1.9.0" > build/version

