import java.util.Scanner;

public class step3 {
    public static void main(String[] args) {

        String[][] cube = new String[6][9];
        String[] color = {"B", "W", "O", "G", "Y", "R"};
        boolean quit = false;

        for (int i = 0; i < 6; i++) {                               //큐브 초기값 입력
            for (int j = 0; j < 9; j++) {
                cube[i][j] = color[i];
            }
        }
        for(int j=0;j<9;j++){
            cube[0][j]=Integer.toString(j);
        }
        printRuwixCubeValue(cube);                          //큐브 초기값 출력

        Scanner scan = new Scanner(System.in);
        while (!quit) {
            System.out.println();
            System.out.print("CUBE > ");
            String code = scan.nextLine();

            for (int i = 0; i < code.length(); i++) {       //입력된값을 한글자씩 나누어 구분
                String codes = getCodes(code, i);
                if (codes.length() == 2) { // '가 포함된 경우 index+1
                    i++;
                }
                boolean isCorrectCode = printCodes(codes);
                if (!isCorrectCode) {
                    continue;
                }
                quit = moveRuwixCube(cube,codes);
                if (!quit) {
                    printRuwixCubeValue(cube);                          //큐브 출력
                }
            }
        }

    }

    private static void printRuwixCubeValue(String[][] cube) {  //큐브 출력 메소드
        for (int i = 0; i < 9; i += 3) {                        //첫 큐브는 앞 한칸 띄어 출력함
            System.out.print("       ");
            for (int j = 0; j < 3; j++) {
                System.out.print(cube[0][j + i] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < 9; i += 3) {                        //2~4번째 큐브 출력
            for (int j = 1; j < 5; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print(cube[j][k + i] + " ");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        for (int i = 0; i < 9; i += 3) {                        //마지막 큐브는 앞 한칸 띄어 출력함
            System.out.print("       ");
            for (int j = 0; j < 3; j++) {
                System.out.print(cube[5][j + i] + " ");
            }
            System.out.println();
        }
    }

    private static String getCodes(String code, int index) {
        boolean isMarkCode = index < code.length() - 1 && (code.charAt(index + 1) == '\'' || code.charAt(index + 1) == '2'); // '여부 체크
        if (isMarkCode) {
            return code.substring(index, index + 2);
        } else {
            return code.substring(index, index + 1);
        }
    }

    private static boolean printCodes(String codes) {
        if (codes.equals(MoveRuwixCube.getCode(codes))) { // 코드값 비교후 정확한 코드라면 출력
            System.out.println("\n" + MoveRuwixCube.getCode(codes));
            return true;
        } else {
            System.out.println(codes + "는 잘못된 코드입니다. 정확한 명령어를 입력해 주세요."); // 잘못 입력되었을경우 메시지 출력
            return false;
        }
    }

    private static void moveMainRight(String[][] cube, int index){
        String[] corner = new String[4];
        String[] edge = new String[4];
        int[] cornerIndex = {0,2,8,6};
        int[] edgeIndex = {1,5,7,3};

        for(int i=0;i<4;i++){
            corner[i]=cube[index][cornerIndex[i]];
            edge[i]=cube[index][edgeIndex[i]];
        }
        String backupCornerPoint = corner[3];
        String backupEdgePoint = edge[3];
        for(int j=3;j>0;j--){
            corner[j]=corner[j-1];
            System.out.println("conner "+j+" = "+corner[j]);
            edge[j]=edge[j-1];
        }
        corner[0]=backupCornerPoint;
        edge[0]=backupEdgePoint;
        for(int k=0;k<4;k++){
            cube[index][cornerIndex[k]]=corner[k];
            cube[index][edgeIndex[k]]=edge[k];
        }
    }
    private static void moveLineRight(String[][] cube,int index){   //닿아있는 4부분의 큐브 숫자를 밀어주는 함수
        int[][] touchedLine = {{4,3,2,1},{0,2,5,4},{0,3,5,1},{0,4,5,2},{5,3,0,1},{2,3,4,1}};    //닿아있는 큐브의 번호
        int[][] usedLine = {{7,8,9,8,5,2},{0,3,6,6,7,8},{0,1,2,6,3,0},{2,5,8,0,1,2}};   //닿아있는 큐브의 번호 안의 변경될 숫자의 위치
        String[] backupLineNumber = new String[3];

            backupLineNumber[0]=cube[touchedLine[index][3]][8];     //밀어낸후 옮길 첫자리 숫자 백업
            backupLineNumber[1]=cube[touchedLine[index][3]][5];
            backupLineNumber[2]=cube[touchedLine[index][3]][2];
            for(int i=3;i>0;i--) {
                for(int j=0;j<3;j++) {
                    cube[touchedLine[index][i]][usedLine[i][j]] = cube[touchedLine[index][i - 1]][usedLine[i][j+3]];
                }
            }
            cube[touchedLine[index][0]][6]=backupLineNumber[0];     //백업한 숫자 재입력
            cube[touchedLine[index][0]][7]=backupLineNumber[1];
            cube[touchedLine[index][0]][8]=backupLineNumber[2];
    }

    private static boolean moveRuwixCube(String[][] cube, String codes) {

        if(codes.equals(MoveRuwixCube.U.nameCode)){
            moveMainRight(cube,0);
            moveLineRight(cube,0);
            return false;
        }
        return false;
    }

    private enum MoveRuwixCube {                                    //명령어 정렬
        F("F"),
        F_MARK("F'"),
        F_TWO("F2"),
        R("R"),
        R_MARK("R'"),
        R_TWO("R2"),
        U("U"),
        U_MARK("U'"),
        U_TWO("U2"),
        B("B"),
        B_MARK("B'"),
        B_TWO("B2"),
        L("L"),
        L_MARK("L'"),
        L_TWO("L2"),
        D("D"),
        D_MARK("D'"),
        D_TWO("D2"),
        Q("Q");

        private final String nameCode;

        MoveRuwixCube(String name) {
            nameCode = name;
        }

        public static String getCode(String name) {
            for (MoveRuwixCube moveRuwixCube : values()) {
                if (name.equals(moveRuwixCube.nameCode)) {
                    return moveRuwixCube.nameCode;
                }
            }
            return null;
        }
    }
//    private enum TouchedLine{
//        ONE("1",5,4,3,2),
//        TWO("2",1,3,6,5),
//        THREE("3",1,4,6,2),
//        FOUR("4",1,5,6,3),
//        FIVE("5",6,4,1,2),
//        SIX("6",3,4,5,2);
//
//        public final String lineNameCode;
//        public final int[] tLine = new int[4];
//
//        TouchedLine(String name,int line0, int line1, int line2, int line3){
//            lineNameCode = name;
//            tLine[0]=line0;
//            tLine[1]=line1;
//            tLine[2]=line2;
//            tLine[3]=line3;
//        }
//        public static String getCode(String name){
//            for(TouchedLine touchedLine : values()){
//                if(name.equals(TouchedLine.valueOf()))
//            }
//        }
//    }
}
