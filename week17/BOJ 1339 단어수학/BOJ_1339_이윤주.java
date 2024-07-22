import java.util.*;

//참고 : https://velog.io/@originxh/%EB%B0%B1%EC%A4%80-1339-%EB%8B%A8%EC%96%B4-%EC%88%98%ED%95%99.Java
public class Main {
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        arr = new int[26]; //알파벳 배열
        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            String str = sc.next();

            int size = str.length();
            int base = (int)Math.pow(10, size-1); //자리수

            for (int j = 0; j < size; j++) {
                arr[str.charAt(j) - 'A'] += base; //해당 알파벳의 자리수 누적합
                base /= 10;
            }
        }
        Arrays.sort(arr);
        int sum = 0;
        for (int i = 25; i >= 17; i--) { //단어에 쓰인 알파벳 개수 최대 10개
            sum += arr[i] * (i-16); //9부터 자리수 누적합 곱해서 더해줌
        }
        System.out.println(sum);

    }

}