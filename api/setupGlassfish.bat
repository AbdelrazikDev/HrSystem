@ECHO OFF
SETLOCAL
call :loadProfile %1
set SCRIPT=%~dp0setupDomain.tmp
call :prepareScript %SCRIPT%
call :executeScript %SCRIPT%
call :createProfileScript
ENDLOCAL
exit /B 0

:loadProfile
call %~dp0loadProfile.bat %1
if "%ERRORLEVEL%" == "1" goto :no_profile
goto :EOF

:createProfileScript
set PROFILE_SCRIPT=%~dp0profile.bat
echo @ECHO OFF > %PROFILE_SCRIPT%
echo SET PROFILE=%PROFILE% >> %PROFILE_SCRIPT%
goto :EOF

:prepareScript
set SCRIPT=%1
rem escape : and \ characters
echo Preparing the script for execution
del /F %SCRIPT%
echo stop-domain --domaindir %DOMAIN_DIR% HrSystem >> %SCRIPT%
echo delete-domain --domaindir %DOMAIN_DIR% HrSystem >> %SCRIPT%
echo create-domain --domaindir %DOMAIN_DIR% --portbase %PORT_BASE% --nopassword=true --user admin --usemasterpassword=false --savelogin=false HrSystem >> %SCRIPT%
echo start-domain --domaindir %DOMAIN_DIR% HrSystem >> %SCRIPT%
echo create-system-properties --target domain WMS_JDBC_URL=%WMS_JDBC_URL% >> %SCRIPT%
echo create-system-properties --target domain WMS_JDBC_USER=%WMS_JDBC_USER% >> %SCRIPT%
echo create-system-properties --target domain WMS_JDBC_PASS=%WMS_JDBC_PASS% >> %SCRIPT%
echo create-system-properties --target domain WMS_ENVIRONMENT=%WMS_ENVIRONMENT% >> %SCRIPT%
echo create-system-properties --target domain WMS_LOGBACK_XML=%WMS_LOGBACK_XML% >> %SCRIPT%
echo create-system-properties --target domain WMS_CONFIG_DIR=%WMS_CONFIG_DIR% >> %SCRIPT%
echo create-system-properties --target domain activemq.message.storage=%ACTIVEMQ_STORAGE_DIR% >> %SCRIPT%
echo create-system-properties --target domain activemq.connector.address=%ACTIVEMQ_REMOTE_CONNECTOR% >> %SCRIPT%
echo create-system-properties --target domain ACTIVEMQ_BROKER_CONFIG=%ACTIVEMQ_BROKER_CONFIG% >> %SCRIPT%
echo create-system-properties --target domain MaxPermSize=%MAX_PERM_SIZE% >> %SCRIPT%
echo create-system-properties --target domain InitPermSize=%INIT_PERM_SIZE% >> %SCRIPT%
echo create-system-properties --target domain MaxHeapSize=Xmx%MAX_HEAP_SIZE% >> %SCRIPT%
echo create-system-properties --target domain MAIL_SERVER=%MAIL_SERVER% >> %SCRIPT%
echo create-system-properties --target domain MAIL_USER=%MAIL_USER% >> %SCRIPT%
echo create-system-properties --target domain MAIL_PASS=%MAIL_PASS% >> %SCRIPT%
echo create-system-properties --target domain SMTP_PORT=%SMTP_PORT% >> %SCRIPT%
echo create-system-properties --target domain POP3_PORT=%POP3_PORT% >> %SCRIPT%
echo create-system-properties --target domain ACTIVEMQ_REMOTE_CONNECTOR_HOST=%ACTIVEMQ_REMOTE_CONNECTOR_HOST% >> %SCRIPT%
echo create-system-properties --target domain JMX_PORT=%JMX_PORT% >> %SCRIPT%

rem classic env variables
echo create-system-properties --target domain CLASSIC_SYSTEM_ROOT=%CLASSIC_SYSTEM_ROOT% >> %SCRIPT%
echo create-system-properties --target domain CLASSIC_CONFIG_PATH=%CLASSIC_CONFIG_PATH% >> %SCRIPT%
echo create-system-properties --target domain CLASSIC_CONFIG_ENV=%CLASSIC_CONFIG_ENV% >> %SCRIPT%
echo create-system-properties --target domain CLASSIC_PROJECT_PATH=%CLASSIC_PROJECT_PATH% >> %SCRIPT%
echo create-system-properties --target domain CLASSIC_RUNTIME_DATA=%CLASSIC_RUNTIME_DATA% >> %SCRIPT%

echo deploy --name jca-socketsrar %JCA_SOCKETS_RAR% >> %SCRIPT%
echo deploy --name activemqrar %ACTIVEMQ_RAR% >> %SCRIPT%
type %~dp0target\scripts\setupDomainStandalone.script >> %SCRIPT%
echo restart-domain --debug %GLASSFISH_DEBUG_ENABLED% --domaindir %DOMAIN_DIR% HrSystem >> %SCRIPT%
goto :EOF

:executeScript
if not exist %DOMAIN_DIR% mkdir %DOMAIN_DIR%
call %GLASSFISH_DIR%\bin\asadmin --port %ADMIN_PORT% multimode --file %1
if "%ERRORLEVEL%" == "1" goto :setup_domain_failed
echo Glassfish Domain is up and running!
goto :EOF

:no_profile
echo Profile file does not exist!
exit /B 1

:setup_domain_failed
echo Failed to setup the domain
exit /B 1
