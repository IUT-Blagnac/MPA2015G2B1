@echo off 
set BINDIR=./bin
cd %BINDIR%/..
call makeOptiWeb.bat
java -classpath bin controler.MakeOPTIweb

