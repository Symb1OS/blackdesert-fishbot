@echo off

cd /d %~dp0
java -Dprofile=${server} --module-path libs -m bdofishbot.gui/ru.namibios.bdofishbot.gui.Launcher