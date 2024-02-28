package _0225;

import java.util.Scanner;

public class SWEA_1926_김민호 {
    // 1부터 입력 받은 값까지 전체 순회를 하면서
    // 각각의 숫자를 입력 받은 것의 글자 수를 센 후
    // 글자 수 만큼 또 순회를 하면서
    // 3,6,9가 있으면 '-'를 3,6,9 개수만큼 출력
    // 없으면 해당 숫자 출력
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 판단 필요한 숫자 받기
        int num = sc.nextInt();

        // 1부터 입력받은 수까지 전체 순회
        for (int i = 1; i < num + 1; i++) {
            judge369(i);
        }
        // 숫자 출력
        System.out.println(sb.toString().trim());
    }

    public static void judge369(int n) {
        // 해당 숫자의 길이 구하기
        String num = n + "";
        int nLen = num.length();
        int numOf369 = 0;

        // 숫자의 길이만큼 순회
        for (int i = 0; i < nLen; i++) {
            char c = num.charAt(i);

            // 3, 6, 9 가 있으면 개수만큼 "-" 추가
            if (c == '3' || c == '6' || c == '9') {
                numOf369++;
                sb.append("-");
            }
        }
        // 3, 6, 9 없으면 해당 숫자 추가
        if (numOf369 == 0) {
            sb.append(n);
        }
        sb.append(" ");
        // String 클래스의 repeat 함수는 자바 11 버전부터 지원
//        if (numOf369 == 0) {
//            sb.append(n).append(" ");
//        } else {
//            sb.append("-".repeat(numOf369)).append(" ");
//        }
    }
}