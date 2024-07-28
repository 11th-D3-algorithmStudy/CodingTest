import java.util.*;

class Solution {
    static int[][] map = new int[102][102]; 
    static boolean[][] visited = new boolean[102][102];
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {

        for (int[] rect : rectangle) {
            int x1 = rect[0] * 2;
            int y1 = rect[1] * 2;
            int x2 = rect[2] * 2;
            int y2 = rect[3] * 2;
            

            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    if (i == x1 || i == x2 || j == y1 || j == y2) {
                        map[i][j] = 1; 
                    } else {
                        map[i][j] = -1; 
                    }
                }
            }
        }


        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {characterX * 2, characterY * 2, 0});
        visited[characterX * 2][characterY * 2] = true;


        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];
            int dist = current[2];


            if (cx == itemX * 2 && cy == itemY * 2) {
                return dist / 2;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && nx < 102 && ny >= 0 && ny < 102 && map[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new int[] {nx, ny, dist + 1});
                }
            }
        }

        return -1;
    }
}
