@ECHO OFF
rem SET MAVEN_CMD=mvn clean install --quiet
rem SET MAVEN_CMD=mvn install -Daspectj -Dxsd-generator.skip -Dddl-generator.skip
rem SET MAVEN_CMD=mvn clean install -Daspectj -DskipTests -Dxsd-generator.skip -Dddl-generator.skip -Dgwt.compiler.skip
rem SET MAVEN_CMD=mvn clean install -Daspectj -DskipTests=true -PgwtDev
SET MAVEN_CMD=mvn clean install -Daspectj -DskipTests=true 

rem call %MAVEN_CMD% | tee build.log

call %MAVEN_CMD%
