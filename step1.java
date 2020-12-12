import java.util.Scanner;

public class step1 {
    public static void main(String[] args) {
        String str;
        int value;
        String direction;
        String leftWord;
        String rightWord;

        Scanner scan = new Scanner(System.in);
        str = scan.next();                      //문자열 저장
        value = scan.nextInt();                 //밀어낼 숫자 저장

        if(-101<value && value<101){
        }else{
            System.out.println("-100과 100까지의 정수값을 입력해 주십시오.");
        }
        direction = scan.next();                //방향 저장

        if(value<0){                            //방향이 음수인경우 양수로 변환후 방향변경
            value = Math.abs(value);
            if(direction.equals("R") || direction.equals("r")){
                direction = "L";
            }else{
                direction = "R";
            }
        }


    }
}