package AlgorithmStudy;

import java.io.*;
import java.util.*;

public class BOJ_15486_성민기 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] Day = new int[N+1];
		int[] cost = new int[N+1];
		int[] DP = new int[N+2];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			Day[i] = Integer.parseInt(st.nextToken());
			cost[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=N; i>=1; i--) {
			// 여기서 부호 >= 으로 하면 틀렸음
			// 이 조건 자체가 퇴사일 보장하는 느낌
			if(i+Day[i] > N+1) DP[i] = DP[i+1];
			else DP[i] = Math.max(DP[i+1], DP[i+Day[i]]+cost[i]);
		}
		
		System.out.println(DP[1]);
	}
}
