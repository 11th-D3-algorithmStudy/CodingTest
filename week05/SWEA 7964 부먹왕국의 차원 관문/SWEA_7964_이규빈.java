package week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7964_이규빈 {
	/*
	 *	Q.부먹왕국의 차원관문
	 *	# 문제 요약 : 모든 도시가 거리 D 이하가 되게 만들기 위해 필요한 차원관문의 최소 개수를 구하라.
	 *	# 풀이시간 : 25분
	 *	# 메모리 / 실행시간 : 74,504kb / 239ms
	 *	# 메인 접근법 : 지도 정보를 순회하며 이동제한 거리에 걸릴 때마다 개수 +1
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 도시 수
			int D = Integer.parseInt(st.nextToken()); // 이동제한 거리
			
			int res = 0; // 추가로 건설해야 하는 차원관문 개수
			int distance = 0; // 거리 계산
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				distance++;
				
				int gate = Integer.parseInt(st.nextToken());
				if (gate == 0 && distance == D) { // 이동제한 거리에 걸린 경우, 차원관문 건설
					res++; 
					distance = 0; // 거리 초기화
				} else if (gate == 1) {
					distance = 0; // 거리 초기화
				}
			}
			
			System.out.println("#" + t + " " + res);
		} // 테스트케이스 끝
	}
}