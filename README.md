#Math Expression Parser

####Grammar 

```
number     = {"0"|"1"|"2"|"3"|"4"|"5"|"6"|"7"|"8"|"9"}
factor     = number | "(" expression ")"
component  = factor [{("*" | "/") factor}]
expression = component [{("+" | "-") component}]
```

- Numbers are one or more of the digits 0-9.
- Factors are a number or an expression within parentheses.
- Components are a factor optionally followed by one or more factors, each of which is preceded by a "*" or a "/".
- Expressions are a component optionally followed by one or more components, each of which is preceded by a "+" or a "-".


####Sources
https://lukaszwrobel.pl/blog/math-parser-part-2-grammar/

https://levelup.gitconnected.com/create-your-own-expression-parser-d1f622077796