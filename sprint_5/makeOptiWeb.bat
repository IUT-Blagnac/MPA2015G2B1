@echo off
set ASCIIDOCDIR=.\tools\asciidoc-8.6.9\
set PLANTUMLDIR=.\tools\
set SPRINTDIR=%~dp0
set SRCDIR=./src/
set BINDIR=./bin
set SRCDOCDIR=./srcdoc/
set DOCDIR=./doc/
set TESTDIR=./test/

set MAKETEST=1

echo o | DEL %BINDIR%/controler/*.*

echo o | DEL %BINDIR%/model/*.*

call makejavaOptiWeb.bat
call makedocOptiWeb.bat

@echo ///////////////////////////////////////////////////////
@echo // EXECUTION de MakeOPTIweb
@echo ///////////////////////////////////////////////////////

java -classpath bin "-Dfile.encoding=UTF-8" controler.MakeOPTIweb

@echo ///////////////////////////////////////////////////////
@echo // EXECUTION des tests casper
@echo ///////////////////////////////////////////////////////

call %TESTDIR%testCasper.bat

pause