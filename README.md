//Diego Chiesurin - 2111553

# Command to generate documentation (pwd is this directory)
javadoc -d doc -cp .:./JUnit/JUnit-4.13.jar:./JUnit/hamcrest-core-1.3-javadoc.jar:./JUnit/JUnit-4.13-javadoc.jar:./JUnit/hamcrest-core-1.3-javadoc.jar myAdapter/*.java myTest/*.java myExceptions/*.java
# Command to compile the entrire project (pwd is this directory)
javac -cp .:./JUnit/hamcrest-core-1.3.jar:./JUnit/junit-4.13.jar ./myAdapter/*.java ./myTest/*.java ./myExceptions/*.java
# Command to run the tests (pwd is this directory)
java -cp .:./JUnit/hamcrest-core-1.3.jar:./JUnit/junit-4.13.jar myTest.TestRunner