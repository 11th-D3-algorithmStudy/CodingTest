package week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1339_단어수학_이규빈 {
    /*
        # 풀이방법
        1. 알파벳 배열을 만들고, 자릿수에 따라 가중치를 부여한다. (e.g. ABC라면 각 가중치는 A 100, B 10, C 1)
        2. 가중치가 가장 높은 알파벳에 9, 그 다음에 8, ... 과 같이 숫자를 배정한다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        // 알파벳 배열 ('A'는 0, 'Z'는 25)
        int[] alphabet = new int[26];

        // 가중치 저장
        for (int i = 0; i < N; i++) {
            String str = words[i];
            int digit = str.length() - 1;
            for (int j = 0; j < str.length(); j++) {
                char c = str.charAt(j);
                alphabet[c - 'A'] += Math.pow(10, digit--);
            }
        }

        // 가중치 기준 정렬
        Arrays.sort(alphabet);

        // 결과 계산
        int res = 0;
        int num = 9;
        for (int i = 25; i >= 0; i--) {
            if (alphabet[i] == 0)  break;
            res += alphabet[i] * num--;
        }
        System.out.println(res);
    }
}
