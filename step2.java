import java.util.Scanner;

public class step2 {

    public static String[][] cube = {{"R", "R", "W"}, {"G", "C", "W"}, {"G", "B", "B"}};

    public static void main(String[] args) {
        String code;
        String codes;
        String cheak;
        String temp = null;
        boolean quit = false;
        for (int i = 0; i < cube.length; i++) {         //큐브 초기배열 출력
            for (int j = 0; j < cube[i].length; j++) {
                System.out.print(cube[i][j] + " ");
            }
            System.out.println();
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
