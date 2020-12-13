### String pushing  
  
It receives a string, number, and direction to push.  
One word, one integer number (-100 <= N <100), L or R from the user. L or R can be entered in both upper and lower case letters.  
If the given word is L, it pushes the given number of numbers to the left, if it is R, it pushes it to the right.  
Words that are pushed out are filled to the opposite side.  
  
  
It was coding by extracting the input command as a separate method. 
If the input does not match the input form, an error message is output and the input is waited for again.   
I coded a method that divides the number to be pushed out by the length of the string and gets the remaining number and stores it.  
By dividing the number by direction as much as the moving direction, the string was cut, moved, and saved.  
In order to use the main area as concisely as possible, the method was written outside as much as possible.