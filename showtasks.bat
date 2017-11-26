call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openWebBrowser
echo.
echo Runcrud.bat script error.
goto fail

:openWebBrowser
start http://localhost:8080/crud/v1/task/getTasks
goto end

:fail
echo.
echo There were errors.

:end
echo Thank you.