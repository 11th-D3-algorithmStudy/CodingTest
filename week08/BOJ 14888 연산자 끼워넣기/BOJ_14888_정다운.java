package week08;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14888_정다운 {
	
	static int N;
	static int[] arr;
	static int[] opArr = new int[4]; // 연산자 개수 배열 
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		// 모든 계산식 결과를 저장하고
		// 마지막에 최대/최소값 찾기
		// 연산자를 골랐다가 안골랐다가 할때
		// 셋째줄에 나오는 연산자 개수 --?
		// dfs 나오면 ++?
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int stLen = st.countTokens();
		for (int i=0; i<stLen; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		StringTokenizer opToken = new StringTokenizer(br.readLine());
		for (int i=0; i<4; i++) {
			opArr[i] = Integer.parseInt(opToken.nextToken());
		}
		
		dfs(arr[0], 0);
		
		System.out.println(max);
		System.out.println(min);
		
	}

	// res : 연산할 값(넘겨줄 값) / o : 연산자 고른 개수
	static void dfs(int res, int o) {
		// 연산자 N-1개 고르기
		// 계산이 끝까지 끝나면 max, min값 업데이트 후 리턴
		if (o == N-1) {
			max = Math.max(max, res);
			min = Math.min(min, res);
			
			return;
		}
		
		// 현재 넘겨줄 값은 res & arr[o+1]
		int next = 0;
		
		// 연산자 고르기
		for (int i=0; i<4; i++) {
			if (opArr[i] != 0) { // 사용할 연산자가 남아있을떄
				switch (i) {
					// +
					case 0: {
						next = res + arr[o+1];
						break;
					}
					// -
					case 1: {
						next = res - arr[o+1];
						break;
					}
					// *
					case 2: {
						next = res * arr[o+1];
						break;
					}
					// %
					case 3: {
						next = res / arr[o+1];
						break;
					}
				}
				// 이번 연산자 골랐다 ~
				opArr[i]--;
				dfs(next, o+1);
				// 백트래킹
				opArr[i]++;
			}
		}
		
	}
	
	
}
