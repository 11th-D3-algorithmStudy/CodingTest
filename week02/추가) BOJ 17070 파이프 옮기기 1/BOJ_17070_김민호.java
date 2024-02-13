import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17070_김민호 {

    // 각 상황 별로 파이프의 모양을 선택해서 목적지까지 도착하는 것의 개수를 세는 것이라서
    // BFS로 풀어야 겠다고 생각함
    // 가로, 세로, 대각선의 각각의 경우를 나눠서 문제를 풀었는데,
    // 시간초과가 나와서 생각을 계속하다가, 도저히 모르겠어서 질문게시판에 가서
    // 시간초과가 난 사람의 글을 보니까
    // 목적지가 벽일 때의 경우가 있어서, 목적지가 1일 때는 바로 0을 출력하게 하니까 통과가 됨

    // 나도 모르게 목적지는 당연히 0일거라고 단정지었는데, 항상 테스트케이스 특히 경계 조건 조심하자
    static int[][] map;
    // 가로, 세로, 대각선 일 때, 상황 별로 나눔
    static int[][] dx = {{0, 1}, {1, 1}};
    static int[][] dy = {{1, 0}, {1, 1}};
    static int[][] dc = {{0, 1}, {1, 0}, {1, 1}};
    static int count;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        n = Integer.parseInt(br.readLine().trim());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 목적지가 1일 때와 아닐 때 처리
        if (map[n - 1][n - 1] == 1) {
            System.out.println(0);
        } else {
            bfs(0, 1);
            System.out.println(count);
        }
    }

    public static void bfs(int x, int y) {
        Queue<XY> q = new LinkedList<>();
        q.offer(new XY(x, y, 2));

        while (!q.isEmpty()) {
            XY xy = q.poll();

            x = xy.getX();
            y = xy.getY();
            int shape = xy.getShape();

            // x, y가 목적지에 도달하면 개수 증가
            if (x == n - 1 && y == n - 1) {
                count++;
                continue;
            }

            // shape
            // 2 : 가로, 3 : 세로, 4 : 대각선
            if (shape == 2) {
                for (int i = 0; i < 2; i++) {
                    int nx = x + dx[i][0];
                    int ny = y + dx[i][1];

                    // i = 0 일 때는 가로, i = 1 일 때는 대각선
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] != 1) {
                        if (i == 0) {
                            q.offer(new XY(nx, ny, 2));
                        } else if (i == 1 && map[nx-1][ny] != 1 && map[nx][ny-1] != 1) {
                            q.offer(new XY(nx, ny, 4));
                        }
                    }
                }
                // i = 0 일 때는 세로, i = 1 일 때는 대각선
            } else if (shape == 3) {
                for (int i = 0; i < 2; i++) {
                    int nx = x + dy[i][0];
                    int ny = y + dy[i][1];

                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] != 1) {
                        if (i == 0) {
                            q.offer(new XY(nx, ny, 3));
                        } else if (i == 1 && map[nx-1][ny] != 1 && map[nx][ny-1] != 1) {
                            q.offer(new XY(nx, ny, 4));
                        }
                    }
                }
                // i = 0 일 때는 가로, i = 1 일 때는 세로, i = 2 일대는 대각선
            } else if (shape == 4) {
                for (int i = 0; i < 3; i++) {
                    int nx = x + dc[i][0];
                    int ny = y + dc[i][1];

                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] != 1) {
                        if (i == 0) {
                            q.offer(new XY(nx, ny, 2));
                        } else if (i == 1) {
                            q.offer(new XY(nx, ny, 3));
                        } else if (i == 2 && map[nx-1][ny] != 1 && map[nx][ny-1] != 1) {
                            q.offer(new XY(nx, ny, 4));
                        }
                    }
                }
            }
        }
    }

    // x, y 좌표와 모양 정보를 가지는 클래스
    static class XY {
        int x;
        int y;
        int shape;

        public XY (int x, int y, int shape) {
            this.x = x;
            this.y = y;
            this.shape = shape;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getShape() {
            return shape;
        }
    }
}
