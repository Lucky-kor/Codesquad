import java.util.Scanner;

public class step1 {
    public static void main(String[] args) {
        StringAndValueAndDirection inputValue = inputValue(); // 값 입력 받

        if (mustReverseValue(inputValue.value)) { // 음수일경우 값 변경후 방향전환
            inputValue.value = Math.abs(inputValue.value);
            inputValue.direction = inputValue.direction.reverseWord();
        }

        while (inputValue.value > inputValue.str.length()) { // 문자열 자르기 위한 값 구하기
            inputValue.value = inputValue.value - inputValue.str.length();
        }

        StringBuilder sb = new StringBuilder();
        if (inputValue.direction.equals(Direction.RIGHT)) {
            sb.append(inputValue.str.substring(inputValue.str.length() - inputValue.value));
            sb.append(inputValue.str.substring(0, inputValue.str.length() - inputValue.value));
        } else {
            sb.append(inputValue.str.substring(inputValue.value));
            sb.append(inputValue.str.substring(0, inputValue.value));
        }
        System.out.println(sb.toString());
    }

    private static StringAndValueAndDirection inputValue() {
        StringAndValueAndDirection data = new StringAndValueAndDirection();
        Scanner scan = new Scanner(System.in);

        boolean isCorrectInput = false;
        // 제대로 입력될 때 까지 무한루프
        while (!isCorrectInput) {
            try {
                data.str = scan.next();                      //문자열 저장
                data.value = scan.nextInt();                 //밀어낼 숫자 저장
                if (Math.abs(data.value) > 100) { // 절대값
                    System.out.println("-100과 100까지의 정수값을 입력해 주십시오.");
                    break;
                }
                data.direction = Direction.getWord(scan.next());                //방향 저장
                isCorrectInput = true;
            } catch (Exception e) {
                System.out.println("제대로 된 값을 입력해 주세요.");
            }
        }
        return data;
    }

    private static boolean mustReverseValue(int value) {
        return value < 0;
    }

    private enum Direction {
        LEFT("L"),
        RIGHT("R");

        private String simpleDirection;

        Direction(String simpleDirection) {
            this.simpleDirection = simpleDirection;
        }

        public static Direction getWord(String simpleDirection) {
            for (Direction word: values()) {
                if (word.simpleDirection.equals(simpleDirection.toUpperCase())) {
                    return word;
                }
            }
            return null;
        }

        public Direction reverseWord() {
            switch (this) {
                case LEFT:
                    return RIGHT;
                case RIGHT:
                    return LEFT;
                default:
                    return null;
            }
        }
    }

    public static class StringAndValueAndDirection {
        String str;
        int value;
        Direction direction;

        public StringAndValueAndDirection() {
        }
    }
}