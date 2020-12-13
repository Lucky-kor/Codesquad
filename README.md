# String pushing #  
  
After receiving the text and number direction, the text is moved in the input direction as much as the input number.  


## Input and output examples ##  
Odd lines are input, even lines are output.  

```
> apple 3 L 
leapp

> banana 6 R
banana

> carrot -1 r
arrotc

> cat -4 R
atc
```
  
## Requirements ##  
  
It receives a string, number, and direction to push.  
One word, one integer number (-100 <= N <100), L or R from the user. L or R can be entered in both upper and lower case letters.  
If the given word is L, it pushes the given number of numbers to the left, if it is R, it pushes it to the right.  
Words that are pushed out are filled to the opposite side.  
  
  
## Code detail ##  
  
### 1. Command input part ###  
  
 * It was coding by extracting the input command as a separate method. 
 * If the input does not match the input form, an error message is output and the input is waited for again.   
```java
private static StringAndValueAndDirection inputValue() {
        StringAndValueAndDirection data = new StringAndValueAndDirection();
        Scanner scan = new Scanner(System.in);

        boolean isCorrectInput = false;
 
        while (!isCorrectInput) {
            try {
                data.str = scan.next(); ]
                data.value = scan.nextInt(); ]
                if (Math.abs(data.value) > 100) {
                    System.out.println("-100과 100까지의 정수값을 입력해 주십시오.");
                    break;
                }
                data.direction = Direction.getWord(scan.next());       
                isCorrectInput = true;
            } catch (Exception e) {
                System.out.println("제대로 된 값을 입력해 주세요.");
            }
        }
        return data;
    }
```

### 2.Processing ###  
 * If the number is less than 0, I changed the direction and treated it as an absolute value.  
```java
if (mustReverseValue(inputValue.value)) { 
            inputValue.value = Math.abs(inputValue.value);
            inputValue.direction = inputValue.direction.reverseWord();
        }
private static boolean mustReverseValue(int value) {
        return value < 0;
    }
```
 * Divide the number by the length of the string to get the remainder, and move only that value.
```java
while (inputValue.value > inputValue.str.length()) { 
            inputValue.value = inputValue.value - inputValue.str.length();
        }
```
 * used Stringbuilder to divide and move the string.  
```java
StringBuilder sb = new StringBuilder();
        if (inputValue.direction.equals(Direction.RIGHT)) {
            sb.append(inputValue.str.substring(inputValue.str.length() - inputValue.value));
            sb.append(inputValue.str.substring(0, inputValue.str.length() - inputValue.value));
        } else {
            sb.append(inputValue.str.substring(inputValue.value));
            sb.append(inputValue.str.substring(0, inputValue.value));
        }
```
