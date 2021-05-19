#!/bin/bash
# le driver sqlite-jdbc-3.32.3.2.jar est disponible dans le répertoire drivers/.
javac *.java
java -classpath ".:sqlite-jdbc-3.32.3.2.jar" Main
