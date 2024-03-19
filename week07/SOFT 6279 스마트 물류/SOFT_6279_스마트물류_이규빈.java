package week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SOFT_6279_스마트물류_이규빈 {
	/*
	* # 풀이시간 : 30분
	* # 메모리 / 실행시간 : 10.34MB / 76ms
	* # 접근법
	*  - 문자열을 순회하다가 로봇을 만나면, 로봇칸을 기준으로 왼쪽으로 K칸부터 오른쪽으로 K칸까지 차례로 순회한다.
	*  - 집을 수 있는 부품이 있으면 결과값을 +1하고 방문처리 한뒤, 다음 로봇으로 넘어간다.
	*/
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		String line = br.readLine();
		boolean[] picked = new boolean[N];
		
		int res = 0;
		
		// line을 순회하며 로봇칸 찾기
		next :
		for (int i = 0; i < N; i++) {
			if (line.charAt(i) == 'P') {
				// 로봇칸을 기준으로 왼쪽으로 K칸부터 오른쪽으로 K칸까지 순회
				for (int j = -K; j <= K; j++) {
					// 경계조건
					if (i + j < 0 || i + j >= N)  continue; 
					
					// 집을 수 있는 조건 = 부품칸 and 아직 집어지지 않았음
					if (line.charAt(i + j) == 'H' && !picked[i + j]) {
						picked[i + j] = true;
						res++;
						continue next; // 부품 집었으면 다음 로봇 찾아서 떠난다.
					}
				}
			}
		} // line 순회 끝
		
		System.out.println(res);
	}
}
