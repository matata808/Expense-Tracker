#!/usr/bin/env sh

# Minimal Maven Wrapper for Unix-like shells.
# Downloads the Maven Wrapper JAR + Maven distribution as defined in .mvn/wrapper/maven-wrapper.properties.

# shellcheck disable=SC1007
BASEDIR=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
MAVEN_PROJECTBASEDIR="$BASEDIR"
WRAPPER_DIR="$BASEDIR/.mvn/wrapper"
WRAPPER_JAR="$WRAPPER_DIR/maven-wrapper.jar"
WRAPPER_PROPERTIES="$WRAPPER_DIR/maven-wrapper.properties"

if [ ! -f "$WRAPPER_PROPERTIES" ]; then
  echo "Missing $WRAPPER_PROPERTIES" >&2
  exit 1
fi

WRAPPER_URL=$(grep '^wrapperUrl=' "$WRAPPER_PROPERTIES" | sed 's/wrapperUrl=//')
if [ -z "$WRAPPER_URL" ]; then
  echo "wrapperUrl not set in $WRAPPER_PROPERTIES" >&2
  exit 1
fi

if [ ! -f "$WRAPPER_JAR" ]; then
  echo "Downloading Maven Wrapper JAR..."
  mkdir -p "$WRAPPER_DIR"
  if command -v curl >/dev/null 2>&1; then
    curl -fsSL "$WRAPPER_URL" -o "$WRAPPER_JAR"
  elif command -v wget >/dev/null 2>&1; then
    wget -q "$WRAPPER_URL" -O "$WRAPPER_JAR"
  else
    echo "Neither curl nor wget found to download Maven Wrapper" >&2
    exit 1
  fi
fi

JAVA_EXE="java"
if [ -n "$JAVA_HOME" ] && [ -x "$JAVA_HOME/bin/java" ]; then
  JAVA_EXE="$JAVA_HOME/bin/java"
fi

exec "$JAVA_EXE" -Dmaven.multiModuleProjectDirectory="$MAVEN_PROJECTBASEDIR" -classpath "$WRAPPER_JAR" org.apache.maven.wrapper.MavenWrapperMain "$@"


