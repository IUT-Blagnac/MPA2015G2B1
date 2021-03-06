@echo ///////////////////////////////////////////////////////
@echo // COMPILATION des executables et des Test
@echo ///////////////////////////////////////////////////////

if "%MAKETEST%"=="1" (
"%JAVA_HOME%\bin\javac" -Xlint:unchecked -encoding utf-8 -cp ./src;./tools/junit.jar -d %BINDIR% .\src\controler\\*.java .\src\model\\*.java
)

pause

@echo ///////////////////////////////////////////////////////
@echo // EXECUTION des tests
@echo ///////////////////////////////////////////////////////

if "%MAKETEST%"=="1" (
 rem cd %BINDIR%
 "%JAVA_HOME%\bin\java" -Dfile.encoding=UTF8 -cp bin;./tools/junit.jar;./model model.TestCSVLibrairie
 "%JAVA_HOME%\bin\java" -Dfile.encoding=UTF8 -cp bin;./tools/junit.jar;./model model.TestEtudiants
 "%JAVA_HOME%\bin\java" -Dfile.encoding=UTF8 -cp bin;./tools/junit.jar;./model model.TestEncadrer
 "%JAVA_HOME%\bin\java" -Dfile.encoding=UTF8 -cp bin;./tools/junit.jar;./model model.TestGroupe
 "%JAVA_HOME%\bin\java" -Dfile.encoding=UTF8 -cp bin;./tools/junit.jar;./model model.TestIntervenants
 "%JAVA_HOME%\bin\java" -Dfile.encoding=UTF8 -cp bin;./tools/junit.jar;./model model.TestProjets
 "%JAVA_HOME%\bin\java" -Dfile.encoding=UTF8 -cp bin;./tools/junit.jar;./model model.TestSujets
 rem cd %SPRINTDIR%
 )