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
}
