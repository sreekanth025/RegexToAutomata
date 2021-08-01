# RegexToAutomata

### Prerequisites to run the program
- Java
- Maven

### To build and run the program
```
mvn clean install
mvn exec:java -Dexec.mainClass=com.sreekanth.compilerdesign.RegexToAutomata
```

### Flow of the process
- After starting the application, user will be prompted to enter the regular expression.
- Regular expression should only contain the following
  - Lower case english alphabets
  - '+'
  - '*'
  - '(' or ')'
- User can also choose random regular expression by entering "random".
- The program will display the formatted expression (with concatenation operator) and compute its postfix.
- Then the application will display corresponding NFA in a new window and also in the console as well.


Note: 
NFA to DFA conversion (models/automata/DFA.java) is still under development.