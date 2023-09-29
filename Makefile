default: run

run: Airline.class
	java Airline

Airline.class: Airline.java
	javac Airline.java

test: TestAirline

TestAirline: Tester.class
	java -jar junit5.jar --class-path . --scan-classpath

Tester.class: Tester.java
	 javac -cp .:junit5.jar Tester.java -Xlint

clean:
	rm *.class
