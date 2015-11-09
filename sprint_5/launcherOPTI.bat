@echo off 
set BINDIR=./bin
cd %BINDIR%/..
java -Dfile.encoding=UTF8 -classpath bin controler.Controleur
