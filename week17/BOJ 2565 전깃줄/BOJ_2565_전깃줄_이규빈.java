package week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2565_전깃줄_이규빈 {
    /*
        # 문제 해석 (핵심!)
        1. "전봇대 A, B" = A를 인덱스, B를 값으로 하는 배열
        2. "줄이 교차하지 않는다." = 위 배열이 오름차순으로 정렬되어 있다. (빈 공간 제외)
        3. "없애야 하는 줄의 최소 개수" = 위 배열을 오름차순으로 만들기 위해 지워야 할 원소의 최소 개수

        # 풀이방법
        1. "BOJ_11722_최장감소부분수열" 문제와 유사하게, 최장"증가"부분수열의 길이를 구한다.
        2. 전깃줄 개수 N에서 위 길이를 뺀 값이 정답이 된다. 왜냐하면 최장증가부분수열에 포함되지 않는 원소가 없애야 할 전깃줄이기 때문이다.
    */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[501];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            arr[idx] = value;
        }

        // ----- 입력 끝 -----

        // dp 배열 초기화
        int[] dp = new int[501];
        for (int i = 1; i < 501; i++) {
            dp[i] = 1;
        }

        // arr를 순회하며, dp 배열 값 구하기
        for (int i = 1; i < 501; i++) {
            for (int j = 1; j < i; j++) {
                if (arr[i] != 0 && arr[j] != 0 && arr[j] < arr[i]) { // 줄 연결 되어있는 경우(= 0 아닌 경우)만 판단
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // dp 배열 중 최댓값 찾기
        int max = 0;
        for (int i = 1; i < 501; i++) {
            max = Math.max(max, dp[i]);
        }

        // 결과
        System.out.println(N - max);
    }
}
