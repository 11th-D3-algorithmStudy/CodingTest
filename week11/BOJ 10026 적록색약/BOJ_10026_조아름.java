package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_10026_조아름 {
	static int N;
	static int count;
	static boolean[][] visited;
	static char[][] arr;
	static int[] dr = {-1, 1, 0,0}; // 상하좌우
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		arr = new char[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			arr[i] = s.toCharArray();
		}
		
		count = 0; // main 함수에서 count 재정의
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if (!visited[i][j]) { // *** 방문하지 않았을 때만 bfs 작동
                    bfs(i, j);
                }
			}
		}
		
		System.out.print(count + " ");
		count = 0;
		
		// G를 R로 변경하는 색약 처리 로직 추가
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 'G') {
                    arr[i][j] = 'R';
                }
            }
        }
		
		// visited 배열 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                visited[i][j] = false;
            }
        }
		
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(i, j);
                }
            }
        }
		
		System.out.print(count);
		
	}

	private static void bfs(int i, int j) {
		visited[i][j] = true;
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {i,j});
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int x = now[0];
			int y = now[1];
			
			for(int k=0;k<4;k++) {
				int dx = x+dr[k];
				int dy = y+dc[k];
				
				if(dx>=0 && dx<N && dy>=0 && dy<N) {
					if(arr[x][y]==arr[dx][dy] && !visited[dx][dy]) {
						visited[dx][dy] = true;
						q.add(new int[] {dx,dy}); // ** 다시 추가하기 잊지 말기
					}
				}
			}
		}
		count++;
	}
	
}

/*
 * 적록색약 - 빨간색과 초록색의 차이를 거의 느끼지 못한다. R(빨강),G(초록),B(파랑)
 * 
 * 
 * RRRBB GGBBB BBBRR BBRRR RRRRR
 * 
 * 적록색약이 아닌 사람 / 적록색약인 사람 -> G를 R로 변경한다. BFS -> 같은 알파벳이면 끝까지 파고 든 다음, count++
 */
