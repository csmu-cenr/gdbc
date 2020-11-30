#1/usr/bin/env bash
echo "mvn install:install-file -DgroupId=com.filemaker -DartifactId=jdbc -Dversion=19.0.0 -Dpackaging=jar -Dfile=wrapper/src/main/java/filemaker/jdbc/driver/fmjdbc.jar -DgeneratePom=true"
mvn install:install-file -DgroupId=com.filemaker -DartifactId=jdbc -Dversion=19.0.0 -Dpackaging=jar -Dfile=wrapper/src/main/java/filemaker/jdbc/driver/fmjdbc.jar -DgeneratePom=true