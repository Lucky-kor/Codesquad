import java.util.Scanner;

public class step3 {
    public static void main(String[] args) {

        String[][] cube = new String[6][9];
        String[] color = { "B" , "W" , "O" , "G" , "Y" , "R" };
        String cheak = null;
        String code = null;
        String codes = null;
        boolean quit = false;

        for (int i=0; i<6; i++) {                               //큐브 초기값 입력
            for (int j=0; j<9; j++){
                cube[i][j]=color[i];
            }
        }
        //큐브 초기값 출력
        for (int i = 0; i < 9; i += 3) {                        //첫 큐브는 앞 한칸 띄어 출력함
            System.out.print("       ");
            for (int j = 0; j < 3; j++) {
                System.out.print(cube[0][j+i] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < 9; i += 3) {                        //2~4번째 큐브 출력
            for (int j = 1; j < 5; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print(cube[j][k+i] + " ");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        for (int i = 0; i < 9; i += 3) {                        //마지막 큐브는 앞 한칸 띄어 출력함
            System.out.print("       ");
            for (int j = 0; j < 3; j++) {
                System.out.print(cube[5][j+i] + " ");
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
                if (cheak.equals("'") || cheak.equals("2")) {                    //cheak의 값이 '일경우 '를 포함해 뒤의값의값까지 함께 저장
                    codes = code.substring(k, k + 2);
                    k++;                                    //뒷자리 패싱
                } else {                                    //이외의 경우 한자리만 저장
                    codes = code.substring(k, k + 1);
                }
                if (codes.equals(MoveCube.getCode(codes))) {//코드값 비교후 코드 출력
                    System.out.println();
                    System.out.println(MoveCube.getCode(codes));
                }
            }
        }

    }
    private enum  MoveCube {                                    //명령어 정렬
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
}
