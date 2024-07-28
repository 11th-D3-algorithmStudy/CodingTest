import java.util.*;

class PRO_169199_김민호 {
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int rx, ry, gx, gy;
    public int solution(String[] board) {
        visited = new boolean[board.length][board[0].length()];

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length(); j++) {
                if(board[i].charAt(j) == 'R') {
                    rx = i;
                    ry = j;
                }

                if(board[i].charAt(j) == 'G') {
                    gx = i;
                    gy = j;
                }
            }
        }

        Queue<Info> q = new LinkedList<>();

        q.offer(new Info(rx, ry, 0));

        while(!q.isEmpty()) {
            Info info = q.poll();

            if (info.getX() == gx && info.getY() == gy) {
                return info.getCnt();
            }

            for (int i = 0; i < 4; i++) {
                int nx = info.getX();
                int ny = info.getY();

                while (true) {
                    nx += dx[i];
                    ny += dy[i];

                    if (nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length() || board[nx].charAt(ny) == 'D') {
                        nx -= dx[i];
                        ny -= dy[i];
                        break;
                    }
                }

                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.offer(new Info(nx, ny, info.getCnt() + 1));
                }
            }
        }

        return -1;


    }
    public static class Info {
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