"%JAVA_HOME%\bin\java" -jar %PLANTUMLDIR%plantuml.jar -Tpng -o %SRCDOCDIR%images %SRCDOCDIR%diag0.puml

@echo ///////////////////////////////////////////////////////
@echo // COMPILATION documentation technique et utilisateur
@echo ///////////////////////////////////////////////////////

python %ASCIIDOCDIR%asciidoc.py -a toc -a toclevels=4 -o %DOCDIR%documentation_utilisateur_MakeOptiWeb.html %SRCDOCDIR%documentation_utilisateur.adoc
python %ASCIIDOCDIR%asciidoc.py -a toc -a toclevels=4 -o %DOCDIR%documentation_technique_MakeOptiWeb.html %SRCDOCDIR%documentation_technique.adoc

pause

@echo ///////////////////////////////////////////////////////
@echo // COMPILATION de la Javadoc
@echo ///////////////////////////////////////////////////////

javadoc -classpath ./tools/junit.jar -encoding "utf8" -docencoding "utf8" -d doc/ -author %SRCDIR%controler/*.java %SRCDIR%model/*.java

pause