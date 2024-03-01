import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_10760_이윤주 {
	/*
	 * Q. 우주선착륙2
	 * 	#문제요약
	 * 		착륙지점 주변 8개 구역 중 착륙지점보다 높이가 낮은 구역이 4개 이상인 지점 찾기
	 * 
	 * 	#풀이시간 : 20분
	 * 	#메모리/시간 : 24360kb / 145ms
	 * 	#메인 접근법
	 * 		1. 델타함수 8방 탐색
	 * 		2. 높이 낮은 구역 카운팅 -> 4개 되면 후보지 개수+1 다음지역으로
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
			
			int[][] area = new int[n][m];
			
			//높이정보 입력
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < m; j++) {
					area[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//8방 델타함수 - 상,우상,우,우하,하,좌하,좌,좌상
			int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
			int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
			
			//예비후보지 개수 저장하는 변수
			int num = 0;
			
			//구역 안에 있는 모든 칸을 순회하면서 8방탐색
			for(int x = 0; x < n; x++) {
				for(int y = 0; y < m; y++) {
					int lower = 0;
					
					//델타함수 8방 탐색
					for(int d = 0; d < 8; d++) {
						int nx = x + dx[d];
						int ny = y + dy[d];
						
						//배열 범위 내에서 확인
						if(nx >= 0 && nx < n && ny >=0 && ny < m) {
							if(area[nx][ny] < area[x][y]) {
								lower++;
							}
						}
						if(lower == 4) { //주변에 낮은 지점 4개되면 카운트하고 다음으로 넘어감
							num++;
							break;
						}
					}//8방 탐색 끝
				}
			} //구역 순회 끝
		
			//출력
			System.out.println("#" + tc + " " + num);
			
		} //테스트케이스 끝

	}
}
