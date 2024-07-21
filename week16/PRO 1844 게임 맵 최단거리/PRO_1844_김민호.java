import java.util.*;

class PRO_1844_김민호 {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][] visited;
    public int solution(int[][] maps) {
        Queue<Pos> q = new LinkedList<>();
        visited = new boolean[maps.length][maps[0].length];

        visited[0][0] = true;
        q.offer(new Pos(0,0,1));

        while(!q.isEmpty()) {
            Pos pos = q.poll();

            if (pos.getX() == maps.length - 1 && pos.getY() == maps[0].length - 1) {
                return pos.getCnt();
            }

            for (int i = 0; i < 4; i++) {
                int nx = pos.getX() + dx[i];
                int ny = pos.getY() + dy[i];

                if (nx < 0 || nx >= maps.length || ny < 0 || ny >= maps[0].length) continue;
                if (maps[nx][ny] != 0 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.offer(new Pos(nx, ny, pos.getCnt() + 1));
                }
            }
        }
        return -1;
    }

    static class Pos {
        int x;
        int y;
        int cnt;

        public Pos(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getCnt() {
            return cnt;
        }
    }
}