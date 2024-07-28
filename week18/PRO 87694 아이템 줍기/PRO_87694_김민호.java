import java.util.*;

class PRO_87694_김민호 {
    static int[][] map;
    static boolean[][] visited;
    static int min = 99999;
    static int max = -99999;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {

        map = new int[102][102];
        visited = new boolean[102][102];

        for (int i = 0; i < rectangle.length; i++) {
            for (int j = rectangle[i][0] * 2; j <= rectangle[i][2] * 2; j++) {
                for (int k = rectangle[i][1] * 2; k <= rectangle[i][3] * 2; k++) {
                    if (!visited[j][k] &&
                            (j == rectangle[i][0] * 2 || j == rectangle[i][2] * 2 ||
                                    k == rectangle[i][1] * 2 || k == rectangle[i][3] * 2)) {
                        map[j][k] = 1;
                        visited[j][k] = false;
                        continue;
                    } else {
                        map[j][k] = 0;
                    }
                    visited[j][k] = true;
                }
            }
        }

        Queue<Info> q = new LinkedList<>();
        q.offer(new Info(characterX * 2, characterY * 2, 0));

        map[characterX * 2][characterY * 2] = 0;

        while(!q.isEmpty()) {
            Info info = q.poll();

            if (info.getX() == itemX * 2 && info.getY() == itemY * 2) {
                return info.getCnt() / 2;
            }

            for (int i = 0; i < 4; i++) {
                int nx = info.getX() + dx[i];
                int ny = info.getY() + dy[i];

                if (nx < 0 || ny < 0 || nx >= map[0].length || ny >= map.length || map[nx][ny] == 0) continue;

                q.offer(new Info(nx, ny, info.getCnt() + 1));
                map[nx][ny] = 0;
            }
        }
        return -1;
    }

    static class Info {
        int x;
        int y;
        int cnt;

        public Info(int x, int y, int cnt) {
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
