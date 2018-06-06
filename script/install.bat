@echo off 

set /p pyi= Install Python? (y/n) 
if %pyi%==y (echo Installing Python... & soft\python-3.5.4.exe)

set /p ai= Install Arduino IDE? (y/n) 
if %ai%==y (echo Installing Arduino IDE.. & soft\arduino-1.8.5-windows.exe)

set /p pi= Install pip dependency? (y/n) 
if %pi%==y (echo Install dependency & pip install http://download.pytorch.org/whl/cpu/torch-0.4.0-cp35-cp35m-win_amd64.whl & pip install torchvision)

pause