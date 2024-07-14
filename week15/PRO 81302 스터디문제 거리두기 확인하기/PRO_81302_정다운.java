import java.util.*;

class Solution {
    
    static class Node {
		int r, c, dist;
		
		public Node(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}
	
	static int[] res; // 결과 배열
	static Queue<Node> queue;
	static char[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static boolean flag;
	static boolean[][] visit;
    
	public static int[] solution(String[][] places) {
		int[] answer = new int[5];
		// P와 P 간 거리는 2 초과여야 함
		// 중간에 파티션(X)이 있는 경우는 2 미만이어도 오케잉 
		// -> bfs
        
		// P 발견하면 거리 2까지만 bfs 돌기
		// X는 통과할 수 없음
		
		for (int i=0; i<5; i++) {
			map = new char[5][5];
			for (int j=0; j<5; j++) {
				map[j] = places[i][j].toCharArray();
			}
			
			flag = false;
			label:
			for (int r=0; r<5; r++) {
				for (int c=0; c<5; c++) {
					if (map[r][c] == 'P') {
						queue = new LinkedList<>();
						visit = new boolean[5][5];
						queue.add(new Node(r,c,0));
						bfs();
					}
					if (flag) {
						break label;
					}
				}
			}
			if (flag) {
				answer[i] = 0;
			} else {
				answer[i] = 1;
			}
		}
		
		return answer;
	}
    
    static void bfs() {
		
		while (!queue.isEmpty()) {
			Node next = queue.poll();
			visit[next.r][next.c] = true;
			if (next.dist >= 2) {
				break;
			}
			for (int d=0; d<4; d++) {
				int nr = next.r+dr[d];
				int nc = next.c+dc[d];
				
				if (nr>=0 && nr<5 && nc>=0 && nc<5 && !visit[nr][nc]) {
					if (map[nr][nc] == 'P') {
						flag = true;
					} else if (map[nr][nc] == 'O') {
						queue.add(new Node(nr, nc, next.dist+1));
					}
				}
			}
		}
		
	}
}
