import java.io.*;
import java.util.*;

public class Main {
	
	/* 메인접근법 :
	 *       - 걸리는 날과 수익을 따로 입력 받도록 함
	 *       - DP 배열에서 N+2까지 해줘야 인덱스 에러가 안났음
	 *           -> N+1인 날에 퇴사하기 때문인 것 같음
	 *       - DP[i] => i번째 날부터 퇴사날까지 벌 수 있는 최대 수입
	 *         (점화식 정의를 뒤에서부터 생각한 것)
	 *       - for문에서 T부터 0까지 i--로 해주었더니 값이 나옴
	 *       
	 * 막힌 부분 : 정보를 찾아봄
	 * (https://youtu.be/EwkHdDeygsI?si=Db5_vkH2QGXlQ4xw)
	 * 메모리 : 14216 KB, 시간 : 124 ms
	 */
	
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
		
		for(int i=N; i > 0; i--) {
			// 일한 것이 퇴사 예정날보다 벗어나면
			if(i+Day[i] > N+1) DP[i] = DP[i+1];
			// 일할 수 있는 경우
			else DP[i] = Math.max(DP[i+1], DP[i+Day[i]]+cost[i]);
		}
		System.out.println(DP[1]);
//		System.out.println(Arrays.toString(DP));
	}
}
