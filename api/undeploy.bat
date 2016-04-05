@ECHO OFF
SETLOCAL
call :loadProfile %1
call %GLASSFISH_DIR%\bin\asadmin --port %ADMIN_PORT% undeploy HrSystem
if "%ERRORLEVEL%" == "1" goto :undeploy_failed
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

:undeploy_failed
echo Failed to undeploy
exit /B 1
