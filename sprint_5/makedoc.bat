"%JAVA_HOME%\bin\java" -jar %PLANTUMLDIR%plantuml.jar -Tpng -o %SRCDOCDIR%images %SRCDOCDIR%diag0.puml

@echo ///////////////////////////////////////////////////////
@echo // COMPILATION documentation technique et utilisateur
@echo ///////////////////////////////////////////////////////

python %ASCIIDOCDIR%asciidoc.py -a toc -a toclevels=4 -o %DOCDIR%documentation_utilisateur_OPTI.html %SRCDOCDIR%documentation_utilisateur_OPTI.adoc
python %ASCIIDOCDIR%asciidoc.py -a toc -a toclevels=4 -o %DOCDIR%documentation_technique_OPTI.html %SRCDOCDIR%documentation_technique_OPTI.adoc

@echo ///////////////////////////////////////////////////////
@echo // COMPILATION de la Javadoc
@echo ///////////////////////////////////////////////////////

javadoc -encoding "utf8" -docencoding "utf8" -d doc/ -author %SRCDIR%controler/*.java %SRCDIR%model/*.java %SRCDIR%view/*.java