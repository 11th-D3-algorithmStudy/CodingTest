package week10;

import java.io.*;
import java.util.*;

public class BOJ_2638_치즈_이규빈 {
    static int N, M, num, time;
    static int[][] map;
    static int[][] air;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        num = 0;
        time = 0;
        
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                
                if(map[r][c] == 1)  num++;
            }
        }
        
        while (num != 0)  bfs();

        System.out.println(time);
    }

    static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0}); // 가장자리에는 치즈가 없으므로, (0,0)부터 시작
        
        air = new int[N][M]; // [수정] 매번 bfs 할때마다 초기화 해줘야함!!
        air[0][0] = -1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];

                if (!inMap(nr,nc)) continue;
                
                if (map[nr][nc] == 1) {
                    air[nr][nc]++;
                } 
                else if (map[nr][nc] == 0 && air[nr][nc] == 0) {
                	air[nr][nc] = -1;
                    q.offer(new int[]{nr, nc});
                }
            }
        }
        
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (air[r][c] >= 2) {
                	num--;
                    map[r][c] = 0;
                }
            }
        }
        
        time++;
    }

    private static boolean inMap(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
