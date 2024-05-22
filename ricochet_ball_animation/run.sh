#!/bin/bash

#Program Name: Ricochet Ball
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

echo Compile ricochetBallPanel.java...
javac ricochetBallPanel.java

echo Compile ricochetBallUI.java...
javac ricochetBallUI.java

echo Compile ricochetBall.java...
javac ricochetBall.java

echo Execute the program...
java ricochetBall

echo End of execution.