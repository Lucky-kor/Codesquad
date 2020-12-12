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
        printRuwixCubeValue(cube);
        //큐브 초기값 출력
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
            }
        }

    }

    private static void printRuwixCubeValue(String[][] cube) {
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
}
