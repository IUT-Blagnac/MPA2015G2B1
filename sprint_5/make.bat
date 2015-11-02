@echo off
set ASCIIDOCDIR=.\tools\asciidoc-8.6.9\
set PLANTUMLDIR=.\tools\
set SPRINTDIR=%~dp0
set SRCDIR=./src/
set BINDIR=./bin
set SRCDOCDIR=./srcdoc/
set DOCDIR=./srcdoc/

set MAKETEST=1

echo o | DEL %BINDIR%/controler/*.*

echo o | DEL %BINDIR%/view/*.*

echo o | DEL %BINDIR%/model/*.*

call makejava.bat
call makedoc.bat
PAUSE