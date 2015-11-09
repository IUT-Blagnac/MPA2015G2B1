@echo off 
set BINDIR=./bin
cd %BINDIR%/..
call makeOptiWeb.bat
java -Dfile.encoding=UTF8 -classpath bin controler.MakeOPTIweb

