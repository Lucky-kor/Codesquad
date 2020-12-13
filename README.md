# Implementing a flat cube #
  
There is a 3x3 quadratic arrangement.  
As below  
  
*R* *R* *W*  
*G* *C* *W*  
*G* *B* *B*  
  
The following actions are executed by receiving user input.  
  
> U Push the top row to the left RRW -> RWR  
> U' Push the top row to one space to the right RRW -> WRR  
> R Push the rightmost row up one space WWB -> WBW  
> R'push the rightmost row down one space WWB -> BWW  
> L Push the leftmost row down one space RGG -> GRG (Note that in the case of L, the direction is opposite to R.)  
> L'Push the leftmost line up one space RGG -> GGR  
> Push the bottom line of B one space to the right GBB -> BGB (Note that the direction of B is also opposite to U.)  
> B'Slide the bottom row one space to the left GBB -> BBG  
> Prints Q Bye~ and ends the program  
  
## Requirements ##  
  
When you first start, the initial array entered is output.  
A prompt called CUBE> is displayed.  
If several strings are received at once, they are divided one by one and executed sequentially.  
Try to implement it in units of functions that are not too large.  
Avoid the use of global variables.  
Make good use of objects and arrays.  
  
## Code detail ##  

### 1. Command input part ###  

 * It displays a simple prompt (the simple letter CUBE> displayed before the keyboard input is received).  
 * When multiple characters are input at once, they are processed in order and each process is displayed on the screen.  
 * When entering an invalid command, output an error message and wait for re-input.  
  
```java
private static boolean printCodes(String codes) {
        if (codes.equals(MoveCube.getCode(codes))) { // 코드값 비교후 정확한 코드라면 출력
            System.out.println("\n" + MoveCube.getCode(codes));
            return true;
        } else {
            System.out.println("정확한 명령어를 입력해 주세요."); // 잘못 입력되었을경우 메시지 출력
            return false;
        }
    }
```
  
### 2.Processing ###  

 * When dividing the following command, it checks for "'".  
  
```java
private static String getCodes(String code, int index) {
        boolean isMarkCode = index < code.length() - 1 && code.substring(index + 1, index + 2).equals("'"); // '여부 체크
        if (isMarkCode) {
            return code.substring(index, index + 2);
        } else {
            return code.substring(index, index + 1);
        }
    }
```
  
 * After arranging the commands into enumeration constants, the moveCube method was created to process the divided commands. 
 * We have created a separate output method for conciseness every time cube output after command input.
```java
private static void printCubeValue(String[][] cube) {
        for (int i = 0; i < cube.length; i++) {
            for (int j = 0; j < cube[i].length; j++) {
                System.out.print(cube[i][j] + " ");
            }
            System.out.println();
        }
    }
``` 
