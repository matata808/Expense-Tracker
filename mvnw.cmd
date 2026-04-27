@ECHO OFF
SETLOCAL

REM Minimal Maven Wrapper for Windows.
REM Downloads the Maven Wrapper JAR + Maven distribution as defined in .mvn\wrapper\maven-wrapper.properties.

SET BASEDIR=%~dp0
REM Remove trailing backslash for consistency
IF "%BASEDIR:~-1%"=="\" SET BASEDIR=%BASEDIR:~0,-1%
SET MAVEN_PROJECTBASEDIR=%BASEDIR%
SET WRAPPER_DIR=%BASEDIR%\.mvn\wrapper
SET WRAPPER_JAR=%WRAPPER_DIR%\maven-wrapper.jar
SET WRAPPER_PROPERTIES=%WRAPPER_DIR%\maven-wrapper.properties

IF NOT EXIST "%WRAPPER_PROPERTIES%" (
  ECHO Missing %WRAPPER_PROPERTIES%
  EXIT /B 1
)

FOR /F "usebackq tokens=1,* delims==" %%A IN ("%WRAPPER_PROPERTIES%") DO (
  IF "%%A"=="wrapperUrl" SET WRAPPER_URL=%%B
)

IF "%WRAPPER_URL%"=="" (
  ECHO wrapperUrl not set in %WRAPPER_PROPERTIES%
  EXIT /B 1
)

IF NOT EXIST "%WRAPPER_JAR%" (
  ECHO Downloading Maven Wrapper JAR...
  powershell -NoProfile -ExecutionPolicy Bypass -Command "New-Item -ItemType Directory -Force -Path '%WRAPPER_DIR%' | Out-Null; (New-Object System.Net.WebClient).DownloadFile('%WRAPPER_URL%', '%WRAPPER_JAR%')"
  IF ERRORLEVEL 1 (
    ECHO Failed to download Maven Wrapper JAR from %WRAPPER_URL%
    EXIT /B 1
  )
)

IF NOT "%JAVA_HOME%"=="" (
  SET JAVA_EXE=%JAVA_HOME%\bin\java.exe
) ELSE (
  SET JAVA_EXE=java
)

"%JAVA_EXE%" -Dmaven.multiModuleProjectDirectory="%MAVEN_PROJECTBASEDIR%" -classpath "%WRAPPER_JAR%" org.apache.maven.wrapper.MavenWrapperMain %*

ENDLOCAL



