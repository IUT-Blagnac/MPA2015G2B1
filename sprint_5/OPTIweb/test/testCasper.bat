@echo off

@echo ///////////////////////////////////////////////////////
@echo // TEST Casper
@echo ///////////////////////////////////////////////////////

call casperjs.bat test casperAccueil.js
call casperjs.bat test casperCredits.js
call casperjs.bat test casperProjets.js
call casperjs.bat test casperSujets.js
call casperjs.bat test casperEtudiants.js
call casperjs.bat test casperIntervenants.js