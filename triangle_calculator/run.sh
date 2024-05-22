#********** STUDENT INFORMATION **********************************************************************
#  Student Name: Brian Y
#  Student E-mail: ***

#!/bin/bash

echo Remove old *.class files if present...
rm *.class

echo View list of source files...
ls -l *.java

echo Compile arithmeticoperations.java...
javac arithmeticoperations.java

echo Compile triangleFrame.java...
javac triangleFrame.java

echo Compile testTriangle.java...
javac testTriangle.java

echo Execute the Triangle Calculator program...
java testTriangle

echo End of execution. Have a nice day!