import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_16268_이윤주 {
	/*
	 * Q. 풍선팡2
	 * 	#문제요약
	 * 		N*M 격자판에 붙어있는 풍선을 터트리면 상하좌우 풍선이 추가로 터진다.
	 * 		풍선이 터질 때 꽃가루가 날리게 되며, 최대로 날릴 수 있는 꽃가루 수를 출력하라.
	 * 
	 * 	#풀이시간 : 10분
	 * 	#메모리/시간 : 26536kb / 143ms
	 * 	#메인 접근법
	 * 		1. 델타함수 4방 탐색
	 * 		2. 주변 꽃가루 개수 합해서 최댓값 구하기
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//테스트케이스
		int t = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			//구역의 크기 N, M
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int[][] balloon = new int[n][m];
			
			//높이정보 입력
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < m; j++) {
					balloon[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//4방 델타함수 - 상,하,좌,우
			int[] dx = {-1, 1, 0, 0};
			int[] dy = {0, 0, -1, 1};
			
			//꽃가루 최댓값
			int max = 0;
			
			//격자 안에 있는 모든 칸을 순회하면서 4방탐색
			for(int x = 0; x < n; x++) {
				for(int y = 0; y < m; y++) {
					int sum = balloon[x][y];
					
					//델타함수 4방 탐색
					for(int d = 0; d < 4; d++) {
						int nx = x + dx[d];
						int ny = y + dy[d];
						
						//배열 범위 내에서 확인
						if(nx >= 0 && nx < n && ny >=0 && ny < m) {
							sum += balloon[nx][ny];
						}
					}//4방 탐색 끝
					//최댓값 갱신
					max = Math.max(max, sum);
				}
			} //격자 순회 끝
		
			//출력
			System.out.println("#" + tc + " " + max);
			
		} //테스트케이스 끝

	}
}
