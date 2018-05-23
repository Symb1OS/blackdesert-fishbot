echo off
set curdir=%1
set fn=%2

python %curdir%\parse.py %curdir% %fn%