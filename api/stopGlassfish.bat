@ECHO OFF
SETLOCAL
call :loadProfile %1



call %GLASSFISH_DIR%\bin\asadmin --port %ADMIN_PORT% stop-domain --domaindir %DOMAIN_DIR% HrSystem
if "%ERRORLEVEL%" == "1" goto :stop_domain_failed
rd /S /Q %DOMAIN_DIR%\HrSystem\osgi-cache 2> nul

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

:stop_domain_failed
echo Failed to stop the domain
exit /B 1
