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
 "%JAVA_HOME%\bin\java" -cp bin;./tools/junit.jar;./model model.MakeOPTIwebTest
 )

pause