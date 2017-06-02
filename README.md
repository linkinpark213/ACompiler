# ACompiler
-- A fake compiler, implementing a language with following features:

## Data types
Supports basic types including integers(int), real numbers(float), characters(char), booleans(bool)

## Simple Variables

## Expressions
### Arithmetic
+, ++, -, --, *
### Relation
<, <=, =, !=, >, >=
### Boolean
&&, ||, ! (Nobody is willing to type ∨, ∧, and ⌐, Okay?)

## Statements
### Assignment
Provides support for multi-dimensional arrays, like "a[1, 2] := 3". 
### Branch
Provides support for "if-then", "it-then-else" structure and "switch-case" structure
### Loop
"while-do" and "do-while" structures will be implemented.
### Call
It's not an OOP language, but the basic procedure-calling mechanism is necesary.
### Definition
As long as some procedures are called, they are defined.

With a text file as the input, a sequence of quadruples (which can be easily transferred to assembly language) will be generated. Invalid code in the text file will cause the compiler to report error type and error position. Also, an output file will be generated along with the analyzation result on the screen.

## Language Example
int a, b;
while a<20 && b>8 do {
  if a>10 || b<16 then {
    if a<15 then {
      a := 19;
      b := 15
    } else {
      a := 11;
      b := 9
    };
  } else {
    a := 1;
    b := 17
  };
}
