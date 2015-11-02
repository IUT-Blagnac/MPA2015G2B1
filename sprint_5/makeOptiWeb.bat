@echo off
set ASCIIDOCDIR=.\tools\asciidoc-8.6.9\
set PLANTUMLDIR=.\tools\
set SPRINTDIR=%~dp0
set SRCDIR=./src/
set BINDIR=./bin
set SRCDOCDIR=./OPTIweb/srcdoc/
set SRCDOCDIR2=./srcdoc/
set DOCDIR=./OPTIweb/srcdoc/
set DOCDIR2=./srcdoc/
set TESTDIR=./OPTIweb/test/

set MAKETEST=1

call makejavaOptiWeb.bat
call makedocOptiWeb.bat
call %TESTDIR%testCasper.bat