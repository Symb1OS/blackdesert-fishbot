@echo off 

set /p ai= Install Arduino IDE? (y/n)
if %ai%==y (echo Installing Arduino IDE.. & soft\arduino-1.8.5-windows.exe)

pause