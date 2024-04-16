package 스터디;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_2382_정다운 {
	
	static class Micro {
		int cnt; // 미생물 수
		int dir; // 방향
		int maxCnt; // 이 좌표로 여러 미생물이 왔을 때 가장 큰 미생물의 수
		
		public Micro(int cnt, int dir, int maxCnt) {
			this.cnt = cnt;
			this.dir = dir;
			this.maxCnt = maxCnt;
		}
	}
	
	static int N, M, K; // map 크기, 격리 시간, 군집 개수
	static Micro[][] map; 
	static Micro[][] nextMap;
	static int res;
	
	static int[] dr = {-1, 1, 0, 0}; 
	static int[] dc = {0, 0, -1, 1}; // 상 하 좌 우
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new Micro[N][N];
			res = 0; 
			
			for (int i=0; i<K; i++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st2.nextToken()); 
				int c = Integer.parseInt(st2.nextToken());
				int cnt = Integer.parseInt(st2.nextToken()); // 미생물 수
				int dir = Integer.parseInt(st2.nextToken()); // 1234 상하좌우
				
				map[r][c] = new Micro(cnt, dir, cnt);
			}
			// 미생물이 없는 칸은 null
			
			// M번(시간) 반복
			for (int i=0; i<M; i++) {
				nextMap = new Micro[N][N];
				
				// map 돌면서 null이 아닌 칸에 있는 미생물 이동시키기
				for (int r=0; r<N; r++) {
					for (int c=0; c<N; c++) {
						if (map[r][c] != null) {
							// 이동할 좌표
							int nr = r+dr[map[r][c].dir-1];
							int nc = c+dc[map[r][c].dir-1];
							
							// 가장자리 인지?
							// 가장자리는 미생물 겹칠일이 없다~?
							// 방향 전환 && cnt/2
							if (nr == 0) { // 맨위
								// dir = 2 (하)
								nextMap[nr][nc] = new Micro(map[r][c].cnt/2, 2, map[r][c].cnt/2);
								continue;
							} else if (nr == N-1) { // 맨 아래
								// dir = 1 (상)
								nextMap[nr][nc] = new Micro(map[r][c].cnt/2, 1, map[r][c].cnt/2);
								continue;
							} else if (nc == 0) { // 왼쪽
								// dir = 4 (우)
								nextMap[nr][nc] = new Micro(map[r][c].cnt/2, 4, map[r][c].cnt/2);
								continue;
							} else if (nc == N-1) { // 오른쪽
								// dir = 3 (좌)
								nextMap[nr][nc] = new Micro(map[r][c].cnt/2, 3, map[r][c].cnt/2);
								continue;
							}
							
							// nextMap에 이미 다른 미생물 군집이 있다?
							if (nextMap[nr][nc] != null) {
								// maxCnt가 내 cnt보다 작으면 방향 내껄로 전환 && maxCnt 나로 업데이트
								if (nextMap[nr][nc].maxCnt < map[r][c].cnt) {
									nextMap[nr][nc].dir = map[r][c].dir;
									nextMap[nr][nc].maxCnt = map[r][c].cnt;
									nextMap[nr][nc].cnt += map[r][c].cnt;
								} else { // 아니면 그냥 cnt만 더하기
									nextMap[nr][nc].cnt += map[r][c].cnt;
								}
							} else { // 미생물 군집 없다 ~
								// 그대로 nextmap으로 넘겨주기
								nextMap[nr][nc] = map[r][c];
							}
							
						}
					}
				}
				// nextMap 원본 map에 복사
				for (int R=0; R<N; R++) {
					for (int C=0; C<N; C++) {
						map[R][C] = nextMap[R][C];
					}
				}
			}
			
			// 맵 돌면서 미생물 개수 구하기
			for (int r=0; r<N; r++) {
				for (int c=0; c<N; c++) {
					if (map[r][c] != null) {
						res += map[r][c].cnt;
					}
				}
			}
			
			sb.append("#"+tc+" "+res+"\n");
		
		}
		System.out.println(sb);
	}
}
