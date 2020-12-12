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
                if (codes.equals(MoveCube.getCode(codes))) {
                    System.out.println();
                    System.out.println(MoveCube.getCode(codes));
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
