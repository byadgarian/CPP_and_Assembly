#!/bin/bash

#Program Name: Tom & Jerry
#Author: Brian Y
#Email: ***
#File Name: run.sh
#Execution: "sh run.sh" without the quotes

echo Remove old class files if present...
rm *.class

echo View a list of source files...
ls -l *.java

echo Compile computations.java...
javac computations.java

echo Compile tomNjerryPanel.java...
javac tomNjerryPanel.java

echo Compile tomNjerryUI.java...
javac tomNjerryUI.java

echo Compile tomNjerry.java...
javac tomNjerry.java

echo Execute the program...
java tomNjerry

echo End of execution.