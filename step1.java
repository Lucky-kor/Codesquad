import java.util.Scanner;

public class step1 {
    public static void main(String[] args) {
        String str = null;
        int value = 0;
        String direction = null;
        String leftWord = null;
        String rightWord = null;

        Scanner scan = new Scanner(System.in);
        boolean isCorrectInput = false;
        // 제대로 입력될 때 까지 무한루프
        while (!isCorrectInput) {
            try {
                str = scan.next();                      //문자열 저장
                value = scan.nextInt();                 //밀어낼 숫자 저장
                if (Math.abs(value) > 100) { // 절대값
                    System.out.println("-100과 100까지의 정수값을 입력해 주십시오.");
                    break;
                }
                direction = scan.next();                //방향 저장
                isCorrectInput = true;
            } catch (Exception e) {
                System.out.println("제대로 된 값을 입력해 주세요.");
            }
        }

        if(value<0) {                                   //음수일경우 값 변경후 방향전환
            value = Math.abs(value);
            if (direction.toLowerCase().equals("r")) {
                direction = "L";
            } else {
                direction = "R";
            }
        }

        while (value > str.length()) {                   //문자열 자르기 위한 값 구하기
            value -= str.length();
        }

        if (direction.toLowerCase().equals("r")) {      //구한값만큼 방향 확인후 이동 대소문자 구분 명령어사용으로 정
            leftWord = str.substring(str.length() - value);
            rightWord = str.substring(0, str.length() - value);
        } else {
            leftWord = str.substring(value);
            rightWord = str.substring(0, value);
        }

        System.out.println(leftWord + rightWord);
    }
}

