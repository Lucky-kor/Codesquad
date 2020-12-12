import java.util.Scanner;

public class step3 {
    public static void main(String[] args) {

        String[][] cube = new String[6][9];
        String[] color = { "B" , "W" , "O" , "G" , "Y" , "R" };

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
