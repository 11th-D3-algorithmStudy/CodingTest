package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_7490_정다운 {
	
	static int N;
	static String res;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		// dfs로 끝까지 탐색하고 0이 되는 경우에 리턴
		// ASCII 순서?....... 공백 -> + -> - 순서인듯
		// 백트래킹 3번 들어가기
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=0; tc<T; tc++) {
			N = Integer.parseInt(br.readLine());
			res = "1";
			
			dfs(1, 1, res, 1, '+');
			
			// 테스트 케이스 종료 후 공백 추가
			sb.append("\n");
		}
		System.out.println(sb);
	}

	// n : 지금 계산하는 수 / tot : 수식의 결과 / res : 지금까지 작성된 수식 / before : 수식 직전에 있는 수 / op : 직전 연산자
	static void dfs(int n, int tot, String res, int before, char op) {
		// N까지 다봤다
		if (n == N) {
			// 수식 결과 0이면 출력~
			if (tot == 0) {
				sb.append(res+"\n");
			}
			return;
		}
		
		// ASCII 순서대로 백트래킹 : 공백 + - 
		
		// 공백
		int nBefore; // 넘겨줄 직전 수
		if (n == 1) {
			nBefore = 12;
		} else {
			nBefore = Integer.parseInt(before+""+(n+1));
		}
		int newtot; // 넘겨줄 수식 결과
		if (op == '+') {
			newtot = tot-before+nBefore; 			
		} else {
			newtot = tot+before-nBefore;
		}
		String newres = res+" "+(n+1); 
		dfs(n+1, newtot, newres, nBefore, op);
		
		// +
		newtot = tot+(n+1);
		newres = res+"+"+(n+1);
		dfs(n+1, newtot, newres, n+1, '+');
		
		// -
		newtot = tot-(n+1);
		newres = res+"-"+(n+1);
		dfs(n+1, newtot, newres, n+1, '-');
		
	}
}
