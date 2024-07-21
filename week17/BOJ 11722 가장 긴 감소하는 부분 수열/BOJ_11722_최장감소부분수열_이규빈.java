package week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11722_최장감소부분수열_이규빈 {
    /*
        # 풀이방법
        1. DP : 수열의 각 요소에 대해, "해당 요소를 기준으로 할때의 감소수열 길이"를 dp 배열에 저장한다.
        2. dp 배열의 각 요소는 1로 초기화한다.
        3. 점화식 (핵심!) : 각 요소 arr[i]에 대해 이전의 모든 요소 arr[j]와 비교한다.
               이때 arr[j] > arr[i]인 경우, dp[i]를 dp[j]+1로 갱신한다.
               왜냐하면 arr[j]에 이어서 부분수열로 덧붙여질수 있기 때문이다.
        4. 최종적으로 dp 배열의 최댓값을 찾는다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // ----- 입력 끝 -----

        // dp 배열 초기화
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
        }

        // arr 순회하며, dp 배열의 값 구하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // 결과값 구하기
        int res = 0;
        for (int i = 0; i < N; i++) {
            res = Math.max(res, dp[i]);
        }
        System.out.println(res);
    }
}
