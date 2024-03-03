import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7964_이윤주 {
	/*
	 * Q.부먹왕국의 차원 관문
	 * 
	 * #문제요약
	 *  거리D까지 연결되는 차원 관문이 모든 도시랑 연결할 수 있도록 하는 
	 *  최소 개수를 구하기
	 *  입력 : T, 도시 수 N, 이동 제한 거리 D
	 *  설치된 차원관문 정보 1:있음 0:없음
	 *  i도시 <-> i+1 도시 사이 거리는 1
	 *  출력 : 추가로 건설해야하는 차원관문의 최소 개수
	 *  
	 * #풀이시간 : 25분
	 * #메모리/시간 : 81016kb/248ms
	 * #메인접근법
	 *  -완전탐색
	 * 	1. 0의 개수를 세면서 이동 제한 거리가 되면 관문 추가
	 *  2. 이동 제한 거리 이내면 0개수 초기화 
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//테스트케이스
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			
			int gate = 0;
			int distance = 0;
			
			//지도 정보 받으면서 확인
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				int map = Integer.parseInt(st.nextToken());
				if(map == 0) { //관문이 없으면
					distance++;
					if(distance == D) {
						gate++;
						distance = 0;
					}
					
				} else { //관문이 있으면
					distance = 0;
				}
			}
			
			System.out.printf("#%d %d\n", tc, gate);
		} //테케끝
	}
}
