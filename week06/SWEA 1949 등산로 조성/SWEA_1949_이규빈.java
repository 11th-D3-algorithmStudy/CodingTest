package week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1949_이규빈 {
	/*
	* Q. 등산로 조성
	* # 풀이시간 : 1시간 정도 풀어보다가, 못풀거 같아서 해설 참고
	* # 메모리 / 실행시간 : 18648kb / 106ms
	* # 문제 해석
	*  - "가장 긴" 등산로 : DFS -> Info 클래스 필드로 len 넣기.
	*  - 가장 높은 곳에서 시작 : map 정보 저장할 때 최고점 찾자.
	*  - 사방 중 낮은 지형으로 진행 : 사방탐색
	*  - 딱 한 곳만 깎기 : 다음 지점 진행여부 판단시 조건 분기 -> 다음 지점 높이가 같거나 높더라도 K 한번 더 고려
	*     또한 Info 클래스 필드로 usedK 넣기.
	* # 주의
	*  - "가장 긴" 등산로를 만들어야 하므로, K만큼 전부 깎지 말아야 한다.
	*    왜냐하면 다음 지점을 K만큼 깎을 경우, 다다음 지점이 더 높아질 수 있기 때문이다.
	*    따라서 다음 지점이 "현재 지점 -1"이 될 정도만 깎아야 한다.
	*  - 높이가 1보다 작아질 수도 있으므로, 음수가 되는 경우를 배제할 필요는 없다.
	* # 막힌부분 해결 - 갓 GPT...
	*  계속 몇몇 테스트케이스 정답과 1,2 정도 차이나는 결과가 발생
	*  -> GPT 도움받아 130라인에서 backtrack되어 돌아온 경우 공사 전 높이로 복구해야 함을 꺠달아 추가함.
	*/
	
	static int N, K;
	static int[][] map;
	static boolean[][] visited;
	static int res;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static class Info { // 현재 지점에서의 정보
		int r, c;
		int height;
		int usedK; // 공사 진행여부
		int len; // 현재까지의 등산로 길이
		
		public Info(int r, int c, int height, int usedK, int len) {
			this.r = r;
			this.c = c;
			this.height = height;
			this.usedK = usedK;
			this.len = len;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			visited = new boolean[N][N];
			
			int highest = 0; // 가장 높은 봉우리의 높이
			res = 0;
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					highest = Math.max(highest, map[r][c]); // 최대 높이 찾기
				}
			}
			
			// ----------- INPUT END -------------
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					// 최고점일 때만 DFS 진행
					if (map[r][c] == highest) { 
						visited[r][c] = true;
						dfs(r, c, map[r][c], 0, 1);
						visited[r][c] = false;
					}
				}
			}
			
			System.out.println("#" + t + " " + res);
		}
	} // End of main
	
	/*
	 *  매 지점마다 "공사 진행여부", "현재 등산로 길이" 정보를 가지고 있어야 하므로,
	 *  매개변수로 usedK, len도 넘겨줘야 한다.
	 */
	private static void dfs(int r, int c, int height, int usedK, int len) {
		res = Math.max(res, len); // 메서드가 시작될때마다 최대값 갱신
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 진행 조건 == 경계조건 만족 && 방문X
			if (inMap(nr, nc) && !visited[nr][nc]) {
				// 1. 다음 지점의 높이가 더 낮은 경우 - 진행 가능
				if (map[r][c] > map[nr][nc]) {
					visited[nr][nc] = true;
					dfs(nr, nc, map[nr][nc], usedK, len + 1); // len++ (X)
					visited[nr][nc] = false; // Backtracking
				} 
				// 2. 다음 지점의 높이가 같거나 더 높은 경우 - 공사할지 판단해야 함
				// 진행 조건 == 현재까지 공사X && 공사하면 현재 지점보다 높이 낮아짐
				else if (usedK == 0 && map[r][c] > map[nr][nc] - K ) {
					visited[nr][nc] = true;
					
					/* [주의] 공사 후 높이
					 *   map[nr][nc] - K (X)
					 *   map[r][c] - 1 (O)
					 *   
					 *   최대한 긴 등산로를 만들어야 하므로, 높이를 K만큼 다 깎으면 안된다.
					 *   대신 "현재 지점보다 1만큼만 작게" 만들어, 다다음에 갈 지점이 최대한 많게 해야한다.
					 */
					int tmp = map[nr][nc]; // 원래 높이를 임시로 저장
					map[nr][nc] = map[r][c] - 1;
					
					dfs(nr, nc, map[nr][nc], 1, len + 1); // 공사했으므로 usedK == 1

					map[nr][nc] = tmp; // [막혔던 부분] Backtrack된 경우 다시 원래 높이로 복구해야 함
					
					visited[nr][nc] = false; // Backtracking
				}
			}
		}
	} // End of DFS

	private static boolean inMap(int nr, int nc) {
		return 0 <= nr && nr < N && 0 <= nc && nc < N;
	}

}
