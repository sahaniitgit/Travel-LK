@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  computervisionapi startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and COMPUTERVISIONAPI_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\computervisionapi.jar;%APP_HOME%\lib\azure-cognitiveservices-computervision-1.0.6-beta.jar;%APP_HOME%\lib\azure-client-runtime-1.7.3.jar;%APP_HOME%\lib\client-runtime-1.7.3.jar;%APP_HOME%\lib\guava-24.1.1-jre.jar;%APP_HOME%\lib\converter-jackson-2.7.2.jar;%APP_HOME%\lib\adapter-rxjava-2.7.2.jar;%APP_HOME%\lib\retrofit-2.7.2.jar;%APP_HOME%\lib\logging-interceptor-3.12.2.jar;%APP_HOME%\lib\okhttp-urlconnection-3.12.2.jar;%APP_HOME%\lib\okhttp-3.14.7.jar;%APP_HOME%\lib\jackson-datatype-joda-2.10.1.jar;%APP_HOME%\lib\commons-lang3-3.4.jar;%APP_HOME%\lib\rxjava-1.3.8.jar;%APP_HOME%\lib\slf4j-api-1.7.22.jar;%APP_HOME%\lib\azure-annotations-1.10.0.jar;%APP_HOME%\lib\jsr305-1.3.9.jar;%APP_HOME%\lib\checker-compat-qual-2.0.0.jar;%APP_HOME%\lib\error_prone_annotations-2.1.3.jar;%APP_HOME%\lib\j2objc-annotations-1.1.jar;%APP_HOME%\lib\animal-sniffer-annotations-1.14.jar;%APP_HOME%\lib\jackson-databind-2.10.1.jar;%APP_HOME%\lib\jackson-annotations-2.10.1.jar;%APP_HOME%\lib\jackson-core-2.10.1.jar;%APP_HOME%\lib\joda-time-2.9.9.jar;%APP_HOME%\lib\okio-1.17.2.jar


@rem Execute computervisionapi
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %COMPUTERVISIONAPI_OPTS%  -classpath "%CLASSPATH%" ImageAnalysisQuickstart %*

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable COMPUTERVISIONAPI_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%COMPUTERVISIONAPI_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
