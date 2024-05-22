#!/bin/bash

#Program Name: Earth
#Student Name: Brian Y
#Student Email: ***
#File Name: run.sh
#Execution: "sh run.sh" without the quotes

echo Remove old class files if present...
rm *.class

echo View a list of source files...
ls -l *.java

echo Compile computations.java...
javac computations.java

echo Compile earthPanel.java...
javac earthPanel.java

echo Compile earthUI.java...
javac earthUI.java

echo Compile earth.java...
javac earth.java

echo Execute the program...
java earth

echo End of execution.