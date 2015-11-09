@echo off

@echo ///////////////////////////////////////////////////////
@echo // TEST Casper
@echo ///////////////////////////////////////////////////////

call casperjs test casperAccueil.js
call casperjs test casperCredits.js
call casperjs test casperProjets.js
call casperjs test casperSujets.js
call casperjs test casperEtudiants.js
call casperjs test casperIntervenants.js