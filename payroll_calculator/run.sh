#!/bin/bash

#Program Name: Payroll System
#Author: Brian Y
#Email: ***
#File Name: run.sh
#Execution: "sh run.sh" without the quotes

echo Remove old *.class files if present...

echo View list of source files...
ls -l *.java

echo Compile arithmeticoperations.java...
javac arithmeticoperations.java

echo Compile payrollFrame.java...
javac payrollFrame.java

echo Compile testPayroll.java...
javac testPayroll.java

echo Execute the Payroll program...
java testPayroll

echo End of execution. Have a nice day!