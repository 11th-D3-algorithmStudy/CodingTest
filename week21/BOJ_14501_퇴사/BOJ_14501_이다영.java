import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+6];
        int cost= 0;
        int end = 0;
        for(int i =1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            end = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());
            dp[i+end] = dp[i+end]>dp[i]+cost?dp[i+end]:dp[i]+cost;
            dp[i+1]=Math.max(dp[i+1],dp[i]);
        }
        System.out.println(dp[N+1]);
    }
}
