import java.io.*;
import java.util.*;

class Solution {

    static class Node {
        int R, C, cnt;

        public Node(int R, int C, int cnt) {
            this.R = R;
            this.C = C;
            this.cnt = cnt;
        }
    }

    static int R, C;
    static char[][] map;
    static boolean[][] visit;
    static Node robot, target;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public int solution(String[] board) {
        R = board.length;
        C = board[0].length();

        map = new char[R][C];
        visit = new boolean[R][C];

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                char input = board[r].charAt(c);
                if (input == 'R') {
                    robot = new Node(r, c, 0);
                } else if (input == 'G') {
                    target = new Node(r, c, 0);
                }
                map[r][c] = input;
            }
        }

        return bfs();
    }

    static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(robot);
        visit[robot.R][robot.C] = true;

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            if (curr.R == target.R && curr.C == target.C) {
                return curr.cnt;
            }

            for (int d = 0; d < 4; d++) {
                int nr = curr.R;
                int nc = curr.C;

                // D 만나거나 map 바깥으로 나가지 않을때까지 같은 방향으로 이동
                while (true) {
                    int nextR = nr + dr[d];
                    int nextC = nc + dc[d];
                    if (nextR < 0 || nextR >= R || nextC < 0 || nextC >= C || map[nextR][nextC] == 'D') {
                        break;
                    }
                    nr = nextR;
                    nc = nextC;
                }

                if (!visit[nr][nc]) {
                    visit[nr][nc] = true;
                    queue.add(new Node(nr, nc, curr.cnt + 1));
                }
            }
        }

        return -1; // G에 도달할 수 없는 경우
    }
}
