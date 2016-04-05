@ECHO OFF
SETLOCAL
call :loadProfile %1
call %GLASSFISH_DIR%\bin\asadmin --port %ADMIN_PORT% start-domain --debug=%GLASSFISH_DEBUG_ENABLED% --domaindir %DOMAIN_DIR% HrSystem
if "%ERRORLEVEL%" == "1" goto :start_domain_failed
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

:start_domain_failed
echo Failed to start the domain
exit /B 1
