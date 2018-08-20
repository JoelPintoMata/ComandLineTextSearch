# Commandline text search

## Tech Stack
* Java 8
* Maven

## Usage
### To generate the application JAR, run:
```bash
$ mvn package
```

### To compile, run:
```bash
$ mvn package
```

### To run the latest version:
```bash
$ mvn compile && mvn package && java -jar ./target/*.jar .
```
, for example:
```bash
$ mvn compile && mvn package && java -jar ./target/*.jar ./<read_from_here>
```

### To run the tests:
```bash
$ mvn compile && mvn test
```