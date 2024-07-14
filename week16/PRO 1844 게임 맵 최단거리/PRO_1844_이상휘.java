import java.util.*;
public class PRO1844 {

    class Solution {

        // N = row
        public static int N;
        // M = col
        public static int M;

        // 초기값 설정
        public static int answer = -1;

        public static int dr[] = {-1, 1, 0, 0};
        public static int dc[] = {0, 0, -1, 1};
        public static boolean visited[][];

        public int solution(int[][] maps) {
            N = maps.length;
            M = maps[0].length;
            visited = new boolean[N][M];

            return bfs(0, 0, maps);
        }

        // bfs
        public int bfs(int x, int y, int[][] maps){
            Queue<int[]> que = new LinkedList<>();

            que.add(new int[]{x, y, 1});
            visited[0][0] = true;

            while (!que.isEmpty()) {
                int temp[] = que.poll();
                x = temp[0];
                y = temp[1];
                int count = temp[2];

                // 도착
                if (x == N-1 && y == M-1) {
                    answer = count;
                    break;
                }

                for (int k = 0; k<4; k++) {
                    int nx = x + dr[k];
                    int ny = y + dc[k];

                    if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                    if(maps[nx][ny] == 0) continue;
                    if(!visited[nx][ny] && maps[nx][ny] == 1) {
                        visited[nx][ny] = true;
                        que.add(new int[]{nx, ny, count+1});
                    }
                }
            }

            return answer;
        }
    }
}
