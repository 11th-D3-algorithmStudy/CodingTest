import java.io.*;
import java.util.StringTokenizer;

public class BOJ_15486_김민호 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        int[][] arr = new int[n + 2][2];
        int[] dp = new int[n + 2];
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            arr[i][0] = t; // 기간
            arr[i][1] = p; // 금액
        }
        // 입력값 받기

        int max = -1;
        for (int i = 1; i <= n + 1; i++) {
            // max 값 재할당
            if(max < dp[i]) {
                max = dp[i];
            }

            // 상담하는데 걸리는 시간 구하기
            int next = i + arr[i][0];
            // 마지막날 넘지 않는 한에서 dp값 넣기
            if (next < n + 2) {
                dp[next] = Math.max(dp[next], max + arr[i][1]);
            }
        }
        System.out.println(dp[n+1]);
    }
}