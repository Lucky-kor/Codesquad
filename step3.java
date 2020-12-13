import java.util.Scanner;

public class step3 {
    public static void main(String[] args) {

        String[][] cube = new String[6][9];
        String[][] checkCube = new String[6][9];
        String[] color = {"B", "W", "O", "G", "Y", "R"};
        int checkCount = 0;
        boolean quit = false;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 6; i++) {                               //큐브 초기값 입력
            for (int j = 0; j < 9; j++) {
                cube[i][j] = color[i];
                checkCube[i][j] = color[i];
            }
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
                checkCount++;
                if (!isCorrectCode) {
                    checkCount--;
                    continue;
                }
                quit = moveRuwixCube(cube, checkCube, codes, checkCount - 1, start);
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

    private static void moveMainRight(String[][] cube, int index) {      //돌리는 큐브의 9가지 숫자를 밀어내는 메소드
        String[] corner = new String[4];        //코너부분 따로 저장하기 위한 함수생성
        String[] edge = new String[4];          //코너를 제외한 바깥부분 따로 저장하기 위한 함수생성
        int[] cornerIndex = {0, 2, 8, 6};
        int[] edgeIndex = {1, 5, 7, 3};

        for (int i = 0; i < 4; i++) {
            corner[i] = cube[index][cornerIndex[i]];  //원본 큐브자료에서 계산할 부분의 큐브 코너부분 값을 복사
            edge[i] = cube[index][edgeIndex[i]];      //원본 큐브자료에서 계산할 부분의 큐브 코너를 제외한 바깥부분의 값을 복사
        }
        String backupCornerPoint = corner[3];
        String backupEdgePoint = edge[3];
        for (int j = 3; j > 0; j--) {                   //숫자 밀어내기
            corner[j] = corner[j - 1];
            edge[j] = edge[j - 1];
        }
        corner[0] = backupCornerPoint;
        edge[0] = backupEdgePoint;
        for (int k = 0; k < 4; k++) {                   //밀어낸 값을 원본 큐브자료에 저장하기
            cube[index][cornerIndex[k]] = corner[k];
            cube[index][edgeIndex[k]] = edge[k];
        }
    }

    private static void moveMainLeft(String[][] cube, int index) {      //돌리는 큐브의 9가지 숫자를 밀어내는 메소드
        String[] corner = new String[4];        //코너부분 따로 저장하기 위한 함수생성
        String[] edge = new String[4];          //코너를 제외한 바깥부분 따로 저장하기 위한 함수생성
        int[] cornerIndex = {0, 2, 8, 6};
        int[] edgeIndex = {1, 5, 7, 3};

        for (int i = 0; i < 4; i++) {
            corner[i] = cube[index][cornerIndex[i]];  //원본 큐브자료에서 계산할 부분의 큐브 코너부분 값을 복사
            edge[i] = cube[index][edgeIndex[i]];      //원본 큐브자료에서 계산할 부분의 큐브 코너를 제외한 바깥부분의 값을 복사
        }
        String backupCornerPoint = corner[0];
        String backupEdgePoint = edge[0];
        for (int j = 0; j < 3; j++) {                   //숫자 밀어내기
            corner[j] = corner[j + 1];
            edge[j] = edge[j + 1];
        }
        corner[3] = backupCornerPoint;
        edge[3] = backupEdgePoint;
        for (int k = 0; k < 4; k++) {                   //밀어낸 값을 원본 큐브자료에 저장하기
            cube[index][cornerIndex[k]] = corner[k];
            cube[index][edgeIndex[k]] = edge[k];
        }
    }

    private static void moveLineRight(String[][] cube, int index) {   //닿아있는 4부분의 큐브 숫자를 우측으로 밀어주는 함수
        int[][] touchedLine = {{4, 3, 2, 1}, {0, 2, 5, 4}, {0, 3, 5, 1}, {0, 4, 5, 2}, {5, 3, 0, 1}, {2, 3, 4, 1}};    //닿아있는 큐브의 번호
        int[][] usedLine = {{7, 8, 9, 8, 5, 2}, {0, 3, 6, 6, 7, 8}, {0, 1, 2, 6, 3, 0}, {2, 5, 8, 0, 1, 2}};   //닿아있는 큐브의 번호 안의 변경될 숫자의 위치
        String[] backupLineNumber = new String[3];

        backupLineNumber[0] = cube[touchedLine[index][3]][8];     //밀어낸후 옮길 첫자리 숫자 백업
        backupLineNumber[1] = cube[touchedLine[index][3]][5];
        backupLineNumber[2] = cube[touchedLine[index][3]][2];
        for (int i = 3; i > 0; i--) {
            for (int j = 0; j < 3; j++) {
                cube[touchedLine[index][i]][usedLine[i][j]] = cube[touchedLine[index][i - 1]][usedLine[i][j + 3]];
            }
        }
        cube[touchedLine[index][0]][6] = backupLineNumber[0];     //백업한 숫자 재입력
        cube[touchedLine[index][0]][7] = backupLineNumber[1];
        cube[touchedLine[index][0]][8] = backupLineNumber[2];
    }

    private static void moveLineLeft(String[][] cube, int index) {   //닿아있는 4부분의 큐브 숫자를 좌측으로 밀어주는 함수
        int[][] touchedLine = {{4, 3, 2, 1}, {0, 2, 5, 4}, {0, 3, 5, 1}, {0, 4, 5, 2}, {5, 3, 0, 1}, {2, 3, 4, 1}};    //닿아있는 큐브의 번호
        int[][] usedLine = {{6, 7, 8, 0, 3, 6}, {0, 3, 6, 2, 1, 0}, {0, 1, 2, 2, 5, 8}, {2, 5, 8, 8, 7, 6}};   //닿아있는 큐브의 번호 안의 변경될 숫자의 위치
        String[] backupLineNumber = new String[3];

        backupLineNumber[0] = cube[touchedLine[index][0]][8];     //밀어낸후 옮길 첫자리 숫자 백업
        backupLineNumber[1] = cube[touchedLine[index][0]][7];
        backupLineNumber[2] = cube[touchedLine[index][0]][6];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cube[touchedLine[index][i]][usedLine[i][j]] = cube[touchedLine[index][i + 1]][usedLine[i][j + 3]];
            }
        }
        cube[touchedLine[index][3]][2] = backupLineNumber[0];     //백업한 숫자 재입력
        cube[touchedLine[index][3]][5] = backupLineNumber[1];
        cube[touchedLine[index][3]][8] = backupLineNumber[2];
    }

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

    private static boolean moveRuwixCube(String[][] cube, String[][] checkCube, String codes, int checkCount, long start) {

        if (codes.equals(MoveRuwixCube.U.nameCode)) {             //상단 시계방향으로 회전
            moveMainRight(cube, 0);
            moveLineRight(cube, 0);
            return checkSuccessEnd(cube, checkCube, start, checkCount + 1);
        }
        if (codes.equals(MoveRuwixCube.U_MARK.nameCode)) {        //상단 반시계방향으로 회전
            moveMainLeft(cube, 0);
            moveLineLeft(cube, 0);
            return checkSuccessEnd(cube, checkCube, start, checkCount + 1);
        }
        if (codes.equals(MoveRuwixCube.U_TWO.nameCode)) {         //상단 시계방향으로 2분의1만큼 회전
            moveMainRight(cube, 0);
            moveLineRight(cube, 0);
            moveMainRight(cube, 0);
            moveLineRight(cube, 0);
            return checkSuccessEnd(cube, checkCube, start, checkCount + 1);
        }
        if (codes.equals(MoveRuwixCube.L.nameCode)) {             //왼쪽 시계방향으로 회전
            moveMainRight(cube, 1);
            moveLineRight(cube, 1);
            return checkSuccessEnd(cube, checkCube, start, checkCount + 1);
        }
        if (codes.equals(MoveRuwixCube.L_MARK.nameCode)) {        //왼쪽 반시계방향으로 회전
            moveMainLeft(cube, 1);
            moveLineLeft(cube, 1);
            return checkSuccessEnd(cube, checkCube, start, checkCount + 1);
        }
        if (codes.equals(MoveRuwixCube.L_TWO.nameCode)) {         //왼쪽 시계방향으로 2분의 1만큼 회전
            moveMainRight(cube, 1);
            moveLineRight(cube, 1);
            moveMainRight(cube, 1);
            moveLineRight(cube, 1);
            return checkSuccessEnd(cube, checkCube, start, checkCount + 1);
        }
        if (codes.equals(MoveRuwixCube.F.nameCode)) {             //전면 시계방향으로 회전
            moveMainRight(cube, 2);
            moveLineRight(cube, 2);
            return checkSuccessEnd(cube, checkCube, start, checkCount + 1);
        }
        if (codes.equals(MoveRuwixCube.F_MARK.nameCode)) {        //전면 반시계방향으로 회전
            moveMainLeft(cube, 2);
            moveLineLeft(cube, 2);
            return checkSuccessEnd(cube, checkCube, start, checkCount + 1);
        }
        if (codes.equals(MoveRuwixCube.F_TWO.nameCode)) {         //전면 시계방향으로 2분의 1만큼 회전
            moveMainRight(cube, 2);
            moveLineRight(cube, 2);
            moveMainRight(cube, 2);
            moveLineRight(cube, 2);
            return checkSuccessEnd(cube, checkCube, start, checkCount + 1);
        }
        if (codes.equals(MoveRuwixCube.R.nameCode)) {             //우측 시계방향으로 회전
            moveMainRight(cube, 3);
            moveLineRight(cube, 3);
            return checkSuccessEnd(cube, checkCube, start, checkCount + 1);
        }
        if (codes.equals(MoveRuwixCube.R_MARK.nameCode)) {        //우측 반시계방향으로 회전
            moveMainLeft(cube, 3);
            moveLineLeft(cube, 3);
            return checkSuccessEnd(cube, checkCube, start, checkCount + 1);
        }
        if (codes.equals(MoveRuwixCube.R_TWO.nameCode)) {         //우측 시계방향으로 2분의 1만큼 회전
            moveMainRight(cube, 3);
            moveLineRight(cube, 3);
            moveMainRight(cube, 3);
            moveLineRight(cube, 3);
            return checkSuccessEnd(cube, checkCube, start, checkCount + 1);
        }
        if (codes.equals(MoveRuwixCube.F.nameCode)) {             //뒷면 시계방향으로 회전
            moveMainRight(cube, 4);
            moveLineRight(cube, 4);
            return checkSuccessEnd(cube, checkCube, start, checkCount + 1);
        }
        if (codes.equals(MoveRuwixCube.F_MARK.nameCode)) {        //뒷면 반시계방향으로 회전
            moveMainLeft(cube, 4);
            moveLineLeft(cube, 4);
            return checkSuccessEnd(cube, checkCube, start, checkCount + 1);
        }
        if (codes.equals(MoveRuwixCube.F_TWO.nameCode)) {         //뒷면 시계방향으로 2분의 1만큼 회전
            moveMainRight(cube, 4);
            moveLineRight(cube, 4);
            moveMainRight(cube, 4);
            moveLineRight(cube, 4);
            return checkSuccessEnd(cube, checkCube, start, checkCount + 1);
        }
        if (codes.equals(MoveRuwixCube.D.nameCode)) {             //바닥 시계방향으로 회전
            moveMainRight(cube, 5);
            moveLineRight(cube, 5);
            return checkSuccessEnd(cube, checkCube, start, checkCount + 1);
        }
        if (codes.equals(MoveRuwixCube.D_MARK.nameCode)) {        //바닥 반시계방향으로 회전
            moveMainLeft(cube, 5);
            moveLineLeft(cube, 5);
            return checkSuccessEnd(cube, checkCube, start, checkCount + 1);
        }
        if (codes.equals(MoveRuwixCube.D_TWO.nameCode)) {         //바닥 시계방향으로 2분의 1만큼 회전
            moveMainRight(cube, 5);
            moveLineRight(cube, 5);
            moveMainRight(cube, 5);
            moveLineRight(cube, 5);
            return checkSuccessEnd(cube, checkCube, start, checkCount + 1);
        }
        if (codes.equals(MoveRuwixCube.SHUFFLE.nameCode)) {
            System.out.println("무작위로 큐브를 섞습니다.");
            shuffle(cube);
        }
        if (codes.equals(MoveRuwixCube.Q.nameCode)) {
            long end = System.currentTimeMillis();
            System.out.println("실행 시간 : " + (end - start) / 1000.0 + "초");
            System.out.println("조작갯수 : " + checkCount);
            System.out.println("이용해주셔서 감사합니다. 뚜뚜뚜.");
            return true;
        } else {
            return false;
        }
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
        SHUFFLE("S"),
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
}
