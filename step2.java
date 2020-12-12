import java.util.Scanner;

public class step2 {

    public static void main(String[] args) {
        String[][] cube = {{"R", "R", "W"}, {"G", "C", "W"}, {"G", "B", "B"}};

        String temp = null;
        boolean quit = false;
        printCubeValue(cube);

        Scanner scan = new Scanner(System.in);
        while (!quit) {
            System.out.println();
            System.out.print("CUBE > ");
            String code = scan.nextLine();

            for (int i = 0; i < code.length(); i++) {   // 입력된값을 한글자씩 나누어 구분
                String codes = getCodes(code, i);
                if (codes.length() == 2) { // '가 포함된 경우 index+1
                    i++;
                }
                boolean isCorrectCode = printCodes(codes);
                if (!isCorrectCode) {
                    continue;
                }
                quit = moveCube(cube, codes);
                if (!quit) {
                    printCubeValue(cube); // 실행 후 큐브 배열 출력
                }
            }
        }
    }

    // 전체 code에서 한 번 연산할 codes 반환
    private static String getCodes(String code, int index) {
        boolean isMarkCode = index < code.length() - 1 && code.substring(index + 1, index + 2).equals("'"); // '여부 체크
        if (isMarkCode) {
            return code.substring(index, index + 2);
        } else {
            return code.substring(index, index + 1);
        }
    }

    private static boolean printCodes(String codes) {
        if (codes.equals(MoveCube.getCode(codes))) { // 코드값 비교후 정확한 코드라면 출력
            System.out.println("\n" + MoveCube.getCode(codes));
            return true;
        } else {
            System.out.println("정확한 명령어를 입력해 주세요."); // 잘못 입력되었을경우 메시지 출력
            return false;
        }
    }

    // cube 배열값 출력
    private static void printCubeValue(String[][] cube) {
        for (int i = 0; i < cube.length; i++) {
            for (int j = 0; j < cube[i].length; j++) {
                System.out.print(cube[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 코드 분석후 큐브내 이동
    private static boolean moveCube(String[][] cube, String codes) {
        if (codes.equals(MoveCube.U.nameCode)) { // U일경우
            String temp = cube[0][0];
            for (int j = 0; j < 2; j++) {
                cube[0][j] = cube[0][j + 1];
            }
            cube[0][2] = temp;
            return false;
        } else if (codes.equals(MoveCube.U_MARK.nameCode)) { // U'일경우
            String temp = cube[0][2];
            for (int j = 2; j > 0; j--) {
                cube[0][j] = cube[0][j - 1];
            }
            cube[0][0] = temp;
            return false;
        } else if (codes.equals(MoveCube.R.nameCode)) { // R일경우
            String temp = cube[0][2];
            for (int j = 0; j < 2; j++) {
                cube[j][2] = cube[j + 1][2];
            }
            cube[2][2] = temp;
            return false;
        } else if (codes.equals(MoveCube.R_MARK.nameCode)) { // R'일경우
            String temp = cube[2][2];
            for (int j = 2; j > 0; j--) {
                cube[j][2] = cube[j - 1][2];
            }
            cube[0][2] = temp;
            return false;
        } else if (codes.equals(MoveCube.L.nameCode)) { // L일경우
            String temp = cube[2][0];
            for (int j = 2; j > 0; j--) {
                cube[j][0] = cube[j - 1][0];
            }
            cube[0][0] = temp;
            return false;
        } else if (codes.equals(MoveCube.L_MARK.nameCode)) { // L'일경우
            String temp = cube[0][0];
            for (int j = 0; j < 2; j++) {
                cube[j][0] = cube[j + 1][0];
            }
            cube[2][0] = temp;
            return false;
        } else if (codes.equals(MoveCube.B.nameCode)) { // B일경우
            String temp = cube[2][2];
            for (int j = 2; j > 0; j--) {
                cube[2][j] = cube[2][j - 1];
            }
            cube[2][0] = temp;
            return false;
        } else if (codes.equals(MoveCube.B_MARK.nameCode)) { // B'일경우
            String temp = cube[2][0];
            for (int j = 0; j < 2; j++) {
                cube[2][j] = cube[2][j + 1];
            }
            cube[2][2] = temp;
            return false;
        } else if (codes.equals(MoveCube.Q.nameCode)) { // Q가 입력될경우 메시지 출력후 종
            System.out.println("Bye~");
            return true;
        } else {
            return false;
        }
    }

    private enum MoveCube {
        U("U"),
        U_MARK("U'"),
        R("R"),
        R_MARK("R'"),
        L("L"),
        L_MARK("L'"),
        B("B"),
        B_MARK("B'"),
        Q("Q");

        private final String nameCode;

        MoveCube(String name) {
            nameCode = name;
        }

        public static String getCode(String name) {
            for (MoveCube moveCube : values()) {
                if (name.equals(moveCube.nameCode)) {
                    return moveCube.nameCode;
                }
            }
            return null;
        }
    }
}