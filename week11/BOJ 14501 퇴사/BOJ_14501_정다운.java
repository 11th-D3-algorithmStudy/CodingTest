package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14501_정다운 {
	
	static class Sangdam {
		int T, P; // 완료하는데 걸리는 기간, 상담료

		Sangdam(int t, int p) {
			T = t;
			P = p;
		}	
	}
	
	static int N;
	static Sangdam[] arr;
	static int max = 0; // 최대 이익
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new Sangdam[N];
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int t = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			
			arr[i] = new Sangdam(t, p);
		}
		
		getMax(0, 0);
		
		System.out.println(max);
	}

	
	// d : 몇번째 날인지(arr idx) / sum : 지금까지의 수익 
	static void getMax(int d, int sum) {
		// d == N-1 -> arr[d].T == 1 일때는 상담하고 sum값 업데이트 하고 리턴
		// d == N -> 바로 리턴?
		// d > N -> 불가능~
		
		if (d >= N-1) {
			if (d == N-1 && arr[d].T == 1) {
				sum += arr[d].P;
			}
			max = Math.max(max, sum);
			return;
		}
		
		// 오늘 상담 할거다~
		int nextSum = sum+arr[d].P;
		int nextD = d+arr[d].T;
		if (nextD <= N) {
			getMax(nextD, nextSum);
		}
		// 안할거야~
		getMax(d+1, sum);

	}
}
