import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1965_이규빈 {
    /*
        #풀이방법
        1. DP : 수열의 각 요소에 대해, "최장증가 부분수열"의 길이를 dp 배열에 저장한다. (초기값은 1)
        2. 점화식 (핵심!) : 각 요소 arr[i]에 대해 "이전의 모든 요소" arr[j]와 비교한다.
               이때 arr[j] < arr[i]인 경우, dp[i]를 dp[j]+1로 갱신한다.
               왜냐하면 arr[j]에 이어서 부분수열로 덧붙여질수 있기 때문이다.
        3. 최종적으로 dp 배열의 최댓값을 찾는다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // ----- 입력 끝 -----

        // dp 배열 초기화
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        // dp 배열의 값(= 최장증가 부분수열의 길이) 구하기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // 결과값 구하기 (88 ms)
        Arrays.sort(dp);
        System.out.println(dp[n - 1]);

//        // 스트림 API : 스트림 생성 -> 연산 (최대값 찾기) -> OptionalInt를 int로 변환 (216 ms)
//        int answer = Arrays.stream(dp).max().getAsInt();
//        System.out.println(answer);
    }
}
