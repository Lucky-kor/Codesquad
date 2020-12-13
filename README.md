# Implement the Rubik's Cube #  

Refer to the reference. [link][] to implement the Rubik's Cube.  
The cube has 6 colors: W, B, G, Y, O, and R.  
Input: Each operation method is entered in one line.  
Print: Prints the six sides of the cube open.  
When Q is input, the program is terminated and the number of commands operated is displayed.  
  
[link]: https://cube3x3.com/큐브를-맞추는-방/#notation
  
### Initial state of the cube ###  
  
		B B B  
		B B B  
		B B B  
	W W W	O O O	G G G	Y Y Y  
	W W W	O O O	G G G	Y Y Y  
	W W W	O O O	G G G	Y Y Y  
		R R R  
		R R R  
		R R R  
  
### Program execution example ###  
  
(초기 상태 출력)  
  
CUBE> FRR'U2R  
  
F  
(큐브상태)  
  
R  
(큐브상태)  
  
...  
  
R  
(큐브상태)  
  
CUBE> Q  
경과시간: 00:31 //추가 구현 항목  
조작갯수: 6  
이용해주셔서 감사합니다. 뚜뚜뚜.  
  
## Requirements ##  
  
Commit as often as possible and write commit messages so that the meaning of the implementation is clearly communicated.  
Implement functions or methods to do one thing at a time and not more than 20 lines if possible.  
Try to make the indentation of the function or method as small as possible (up to 3 steps only).  
Output of elapsed time at the end of the program.  
Cube's random shuffle function.  
When all faces are correct, the program automatically closes with a congratulatory message.  
  
  
## Code detail ##  
  
 * It displays a simple prompt (the simple letter CUBE> displayed before the keyboard input is received).  
 * When multiple characters are input at once, they are processed in order and each process is displayed on the screen.  
 * Among the input strings, "'" and "2" are separated and included to divide the string.  
  
```java
for (int i = 0; i < code.length(); i++) { 
                String codes = getCodes(code, i);
                if (codes.length() == 2) { 
                    i++;
                }
                boolean isCorrectCode = printCodes(codes);
                checkCount++;
                if (!isCorrectCode) {
                    checkCount--;
                    continue;
                }
                quit = moveRuwixCube(cube, checkCube, codes, checkCount - 1, start);
                if (!quit) {
                    printRuwixCubeValue(cube);
                }
            }
  
private static String getCodes(String code, int index) {
        boolean isMarkCode = index < code.length() - 1 && (code.charAt(index + 1) == '\'' || code.charAt(index + 1) == '2');
        if (isMarkCode) {
            return code.substring(index, index + 2);
        } else {
            return code.substring(index, index + 1);
        }
    }
```
  
 * If an incorrect command is entered, an error message is displayed.   
```java
private static boolean printCodes(String codes) {
        if (codes.equals(MoveRuwixCube.getCode(codes))) {
            System.out.println("\n" + MoveRuwixCube.getCode(codes));
            return true;
        } else {
            System.out.println(codes + "는 잘못된 코드입니다. 정확한 명령어를 입력해 주세요.");
            return false;
        }
    }
```
  
### 2.Processing ###  
  
 * Since there are many times to output the cube every time a command is entered, a separate output method was written.  
 * When printing the cube, I put a space to make it easier to read.   
```java
private static void printRuwixCubeValue(String[][] cube) { 
        for (int i = 0; i < 9; i += 3) { 
            System.out.print("       ");
            for (int j = 0; j < 3; j++) {
                System.out.print(cube[0][j + i] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < 9; i += 3) {   
            for (int j = 1; j < 5; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print(cube[j][k + i] + " ");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        for (int i = 0; i < 9; i += 3) {
            System.out.print("       ");
            for (int j = 0; j < 3; j++) {
                System.out.print(cube[5][j + i] + " ");
            }
            System.out.println();
        }
    }
```
  
 * The commands are divided into separate enumeration functions.   
 * To make things simple and convenient when executing tasks that run cubes, we have created a method divided into 4 types.   
 * I have created a method that moves to the right and left by dividing two into the corner part and the outer part excluding the corner.  
```java
private static void moveMainRight(String[][] cube, int index) {     
        String[] corner = new String[4];      
        String[] edge = new String[4];        
        int[] cornerIndex = {0, 2, 8, 6};
        int[] edgeIndex = {1, 5, 7, 3};

        for (int i = 0; i < 4; i++) {
            corner[i] = cube[index][cornerIndex[i]]; 
            edge[i] = cube[index][edgeIndex[i]];    
        }
        String backupCornerPoint = corner[3];
        String backupEdgePoint = edge[3];
        for (int j = 3; j > 0; j--) {      
            corner[j] = corner[j - 1];
            edge[j] = edge[j - 1];
        }
        corner[0] = backupCornerPoint;
        edge[0] = backupEdgePoint;
        for (int k = 0; k < 4; k++) {        
            cube[index][cornerIndex[k]] = corner[k];
            cube[index][edgeIndex[k]] = edge[k];
        }
    }

    private static void moveMainLeft(String[][] cube, int index) {  
        String[] corner = new String[4]; 
        String[] edge = new String[4];       
        int[] cornerIndex = {0, 2, 8, 6};
        int[] edgeIndex = {1, 5, 7, 3};

        for (int i = 0; i < 4; i++) {
            corner[i] = cube[index][cornerIndex[i]]; 
            edge[i] = cube[index][edgeIndex[i]];  
        }
        String backupCornerPoint = corner[0];
        String backupEdgePoint = edge[0];
        for (int j = 0; j < 3; j++) {   
            corner[j] = corner[j + 1];
            edge[j] = edge[j + 1];
        }
        corner[3] = backupCornerPoint;
        edge[3] = backupEdgePoint;
        for (int k = 0; k < 4; k++) {          
            cube[index][cornerIndex[k]] = corner[k];
            cube[index][edgeIndex[k]] = edge[k];
        }
    }

    private static void moveLineRight(String[][] cube, int index) {  
        int[][] touchedLine = {{4, 3, 2, 1}, {0, 2, 5, 4}, {0, 3, 5, 1}, {0, 4, 5, 2}, {5, 3, 0, 1}, {2, 3, 4, 1}};  
        int[][] usedLine = {{7, 8, 9, 8, 5, 2}, {0, 3, 6, 6, 7, 8}, {0, 1, 2, 6, 3, 0}, {2, 5, 8, 0, 1, 2}};  
        String[] backupLineNumber = new String[3];

        backupLineNumber[0] = cube[touchedLine[index][3]][8];  
        backupLineNumber[1] = cube[touchedLine[index][3]][5];
        backupLineNumber[2] = cube[touchedLine[index][3]][2];
        for (int i = 3; i > 0; i--) {
            for (int j = 0; j < 3; j++) {
                cube[touchedLine[index][i]][usedLine[i][j]] = cube[touchedLine[index][i - 1]][usedLine[i][j + 3]];
            }
        }
        cube[touchedLine[index][0]][6] = backupLineNumber[0];    
        cube[touchedLine[index][0]][7] = backupLineNumber[1];
        cube[touchedLine[index][0]][8] = backupLineNumber[2];
    }

    private static void moveLineLeft(String[][] cube, int index) { 
        int[][] touchedLine = {{4, 3, 2, 1}, {0, 2, 5, 4}, {0, 3, 5, 1}, {0, 4, 5, 2}, {5, 3, 0, 1}, {2, 3, 4, 1}}; 
        int[][] usedLine = {{6, 7, 8, 0, 3, 6}, {0, 3, 6, 2, 1, 0}, {0, 1, 2, 2, 5, 8}, {2, 5, 8, 8, 7, 6}}; 
        String[] backupLineNumber = new String[3];

        backupLineNumber[0] = cube[touchedLine[index][0]][8];
        backupLineNumber[1] = cube[touchedLine[index][0]][7];
        backupLineNumber[2] = cube[touchedLine[index][0]][6];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cube[touchedLine[index][i]][usedLine[i][j]] = cube[touchedLine[index][i + 1]][usedLine[i][j + 3]];
            }
        }
        cube[touchedLine[index][3]][2] = backupLineNumber[0];
        cube[touchedLine[index][3]][5] = backupLineNumber[1];
        cube[touchedLine[index][3]][8] = backupLineNumber[2];
    }
```
  
### 3. Additional features ###  

 * I made a function that outputs the elapsed time at program termination using System.currentTimeMillis.  
 * A new command, "S", has been added to randomly shuffle cubes.  
```java
private static void shuffle(String[][] cube) {
        int[] cubeIndex = {0, 1, 2, 3, 4, 5, 0, 2, 4, 5};
        int cost = (int) (Math.random() * 100);
        for (int k = 0; k < cost; k++) {
            int j = (int) (Math.random() * 10);
            double i = Math.random();
            if (i > 0 && i < 0.25) {
                moveLineRight(cube, cubeIndex[j]);
            } else if (i > 0.25 && i < 0.5) {
                moveLineLeft(cube, cubeIndex[j]);
            } else if (i > 0.5 && i < 0.75) {
                moveMainRight(cube, cubeIndex[j]);
            } else {
                moveMainLeft(cube, cubeIndex[j]);
            }
        }
    }
```
 * After entering the command, if the first input value and the current cube value are the same when moving the cube, a congratulatory message is output and the program ends.   
```java
private static boolean checkSuccessEnd(String[][] cube, String[][] checkCube, long start, int checkCount) { //큐브 모든면이 일치하면 성공메시지 출력후 종
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                if (!cube[i][j].equals(checkCube[i][j])) {
                    return false;
                } else {
                    continue;
                }
            }
        }
        printRuwixCubeValue(cube);
        long end = System.currentTimeMillis();
        System.out.println("실행 시간 : " + (end - start) / 1000.0 + "초");
        System.out.println("조작갯수 : " + checkCount);
        System.out.println("큐브의 모든 면 맞았습니다. 축하합니다. 짝짝");
        return true;
    }
```
