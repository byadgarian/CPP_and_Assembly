#!/bin/bash

#Program Name: Diamond Animation
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

echo Compile diamondPanel.java...
javac diamondPanel.java

echo Compile diamondUI.java...
javac diamondUI.java

echo Compile diamond.java...
javac diamond.java

echo Execute the program...
java diamond

echo End of execution.