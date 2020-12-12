import java.util.Scanner;

public class step2 {

    public static String[][] cube = {{"R", "R", "W"}, {"G", "C", "W"}, {"G", "B", "B"}};

    public static void main(String[] args) {
        String code;
        String codes;
        String cheak;
        String temp = null;
        boolean quit = false;
        for (int i = 0; i < cube.length; i++) {             //큐브 초기배열 출력
            for (int j = 0; j < cube[i].length; j++) {
                System.out.print(cube[i][j] + " ");
            }
            System.out.println();
        }
        Scanner scan = new Scanner(System.in);
        while(!quit) {
            System.out.println();
            System.out.print("CUBE > ");
            code = scan.nextLine();

            for (int k = 0; k < code.length(); k++) {       //입력된값을 한글자씩 나누어 구분
                if (k < code.length() - 1) {
                    cheak = code.substring(k + 1, k + 2);   //구하는값의 뒷자리의 글자를 cheak에 저장
                } else {                                    //맨 마지막은 비교할 필요가 없어 체크값 강제입력
                    cheak = "";
                }
                if (cheak.equals("'")) {                    //cheak의 값이 '일경우 '를 포함해 뒤의값의값까지 함께 저장
                    codes = code.substring(k, k + 2);
                    k++;                                    //뒷자리 패싱
                } else {                                    //이외의 경우 한자리만 저장
                    codes = code.substring(k, k + 1);
                }
                if (codes.equals(MoveCube.getCode(codes))) {//코드값 비교후 코드 출력
                    System.out.println();
                    System.out.println(MoveCube.getCode(codes));
                }
                //코드 분석후 큐브내 이동함
                if (codes.equals(MoveCube.getCode("U"))) { //U일경우
                    temp = cube[0][0];
                    for (int i = 0; i < 2; i++) {
                        cube[0][i] = cube[0][i + 1];
                    }
                    cube[0][2] = temp;
                    for (int i = 0; i < cube.length; i++) {     //명렁어 실행후 큐브 재출력
                        for (int j = 0; j < cube[i].length; j++) {
                            System.out.print(cube[i][j] + " ");
                        }
                        System.out.println();
                    }
                }
                else if (codes.equals(MoveCube.getCode("U'"))) { //U'일경우
                    temp = cube[0][2];
                    for (int i = 2; i > 0; i--) {
                        cube[0][i] = cube[0][i - 1];
                    }
                    cube[0][0] = temp;
                    for (int i = 0; i < cube.length; i++) {            //명렁어 실행후 큐브 재출력
                        for (int j = 0; j < cube[i].length; j++) {
                            System.out.print(cube[i][j] + " ");
                        }
                        System.out.println();
                    }
                }
                else if (codes.equals(MoveCube.getCode("R"))) { //R일경우
                    temp = cube[0][2];
                    for (int i = 0; i < 2; i++) {
                        cube[i][2] = cube[i + 1][2];
                    }
                    cube[2][2] = temp;
                    for (int i = 0; i < cube.length; i++) {           //명렁어 실행후 큐브 재출력
                        for (int j = 0; j < cube[i].length; j++) {
                            System.out.print(cube[i][j] + " ");
                        }
                        System.out.println();
                    }
                }
                else if (codes.equals(MoveCube.getCode("R'"))) { //R'일경우
                    temp = cube[2][2];
                    for (int i = 2; i > 0; i--) {
                        cube[i][2] = cube[i - 1][2];
                    }
                    cube[0][2] = temp;
                    for (int i = 0; i < cube.length; i++) {           //명렁어 실행후 큐브 재출력
                        for (int j = 0; j < cube[i].length; j++) {
                            System.out.print(cube[i][j] + " ");
                        }
                        System.out.println();
                    }
                }
                else if (codes.equals(MoveCube.getCode("L"))) { //L일경우
                    temp = cube[2][0];
                    for (int i = 2; i > 0; i--) {
                        cube[i][0] = cube[i - 1][0];
                    }
                    cube[0][0] = temp;
                    for (int i = 0; i < cube.length; i++) {          //명렁어 실행후 큐브 재출력
                        for (int j = 0; j < cube[i].length; j++) {
                            System.out.print(cube[i][j] + " ");
                        }
                        System.out.println();
                    }
                }
                else if (codes.equals(MoveCube.getCode("L'"))) { //L'일경우
                    temp = cube[0][0];
                    for (int i = 0; i < 2; i++) {
                        cube[i][0] = cube[i + 1][0];
                    }
                    cube[2][0] = temp;
                    for (int i = 0; i < cube.length; i++) {           //명렁어 실행후 큐브 재출력
                        for (int j = 0; j < cube[i].length; j++) {
                            System.out.print(cube[i][j] + " ");
                        }
                        System.out.println();
                    }
                }
                else if (codes.equals(MoveCube.getCode("B"))) { //B일경우
                    temp = cube[2][2];
                    for (int i = 2; i > 0; i--) {
                        cube[2][i] = cube[2][i - 1];
                    }
                    cube[2][0] = temp;
                    for (int i = 0; i < cube.length; i++) {           //명렁어 실행후 큐브 재출력
                        for (int j = 0; j < cube[i].length; j++) {
                            System.out.print(cube[i][j] + " ");
                        }
                        System.out.println();
                    }
                }
                else if (codes.equals(MoveCube.getCode("B'"))) { //B'일경우
                    temp = cube[2][0];
                    for (int i = 0; i < 2; i++) {
                        cube[2][i] = cube[2][i + 1];
                    }
                    cube[2][2] = temp;
                    for (int i = 0; i < cube.length; i++) {            //명렁어 실행후 큐브 재출력
                        for (int j = 0; j < cube[i].length; j++) {
                            System.out.print(cube[i][j] + " ");
                        }
                        System.out.println();
                    }
                }
                else if (codes.equals(MoveCube.getCode("Q"))) {//Q가 입력될경우 메시지 출력후 종
                    System.out.println("Bye~");
                    quit = true;
                }else {
                    System.out.println("정확한 명령어를 입력해 주세요.");    //잘못 입력되었을경우 메시지 출력
                }
            }
        }
    }
    private enum  MoveCube {
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

        MoveCube(String name){
            nameCode = name;
        }
        public static String getCode(String name){
            for(MoveCube moveCube : values()){
                if(name.equals(moveCube.nameCode)){
                    return moveCube.nameCode;
                }
            }return null;
        }
    }
//    public static int
}
