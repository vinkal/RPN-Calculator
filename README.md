# Reverse Polish notation calulator

Command-line based RPN calculator

## Intro

The calculator waits for user input and expects to receive strings containing whitespace
separated lists of numbers and operators.
 
Numbers are pushed on to the stack.  Operators operate on numbers that are on the stack.
 
Available operators are `+`, `-`, `*`, `/`

- The `+`, `-`, `*`, `/` operators perform addition, subtraction, multiplication and division respectively on the top two items from the stack. 
 
## Compile, Test, Run and Packaging

- Compile: `mvn compile`

- Test: `mvn test`

- Run: `mvn exec:java`

- Packaging: `mvn package`, compiled jar in *target/* folder