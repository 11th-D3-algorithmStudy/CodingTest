import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15486_이상휘 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 퇴사날에 1일이 들어갈 수 있으므로 N+1배열
        int[] dp = new int[N+1];
        int[] T = new int[N+1];
        int[] P = new int[N+1];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        for(int i = 0; i < N+1;i++) {
            if (max < dp[i]) {
                max = dp[i];
            }
            int idx = i + T[i];
            if (idx < N+1) {
                dp[idx] = Math.max(dp[idx], max + P[i]);
            }
        }
        System.out.println(max);
        System.out.println(Arrays.toString(dp));

    }
}
