
#!/bin/bash
set -e

cd "${0%/*}"

DRIVER_NAME="filemaker" \
DRIVER_GROUP="com.filemaker" \
DRIVER_ARTIFACT="jdbc" \
DRIVER_VERSION="19.0.0" \
DRIVER_CLASS="com.filemaker.jdbc.Driver" \
DRIVER_BUILD_TIME_CLASSES="" \
DRIVER_OUTPUT_DIR="`cd ../.. && pwd`/$DRIVER_NAME" \
  ../wrap-driver.sh
