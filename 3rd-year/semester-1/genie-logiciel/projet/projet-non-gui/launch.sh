#!usr/bin/env bash
# compile and run this java project
javac *.java
java -classpath ".:sqlite-jdbc-3.32.3.2.jar" Main
# to see the database, you can use sqlite3 in a terminal:
# $ sudo apt install sqlite3
# $ sqlite3 Librairie.db
# sqlite> .tables
# sqlite> SELECT * FROM Abonnes;
# sqlite> .quit
