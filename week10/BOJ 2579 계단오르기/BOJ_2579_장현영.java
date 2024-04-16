package week10;

import java.io.*;
import java.util.*;

public class BOJ_2579_장현영 {
	// 계단오르기
	// 1회마다 1 or 2
	// 3개연속 밟기 안됨
	// dp로 점화식 풀기
	// 풀이시간 40분
	
	public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());

    int[] arr = new int[n+1];

    for (int i = 1; i <= n; i++) {
        arr[i] = Integer.parseInt(br.readLine());
    }

    int[] dp = new int[n+1]; // dp로 계속 저장해가면서 점수 최댓값 업데이트
    dp[1] = arr[1];

    // dp[2] = a1+a2
    // dp[3] = a3+ (a1,a2 중 큰값)
    // 점화식 세우기
    // dp[4]: a4+ (시작점이 2 or 1에서 2칸뛴거 합 중 큰값)
    // dp[5]: a5+ (시작점이 3 or 2에서 2칸뛴거 합 중 큰값)
    // dp[n] = arr[n] + (dp[n-2], dp[n-3]+a[n-1])의 max
    for (int i = 2; i <= n; i++) {
        if(i==2){
            dp[2] = arr[1] + arr[2];
        }else if(i==3){
            dp[3] = Math.max(arr[1], arr[2]) + arr[3];
        }else{
            dp[i] = Math.max(dp[i-3] + arr[i-1], dp[i-2]) + arr[i];
        }
    }

    System.out.println(dp[n]);
	}
}
