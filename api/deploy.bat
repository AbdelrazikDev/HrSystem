@ECHO OFF
SETLOCAL
call :loadProfile %1
set AS_ADMIN_READTIMEOUT=3600000
call %GLASSFISH_DIR%\bin\asadmin --port %ADMIN_PORT% deploy --enabled=true --name=HrSystem %APPLICATION%
if "%ERRORLEVEL%" == "1" goto :deploy_failed
ENDLOCAL
exit /B 0

:loadProfile
call %~dp0profile.bat
echo Using profile %PROFILE%
call %~dp0loadProfile.bat %PROFILE%
if "%ERRORLEVEL%" == "1" goto :no_profile
goto :EOF

:no_profile
echo Profile file does not exist!
exit /B 1

:deploy_failed
echo Failed to deploy
exit /B 1
