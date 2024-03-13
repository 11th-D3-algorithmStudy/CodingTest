import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953_김민호 {
    // 파이프 모양마다 타입이 정해져 있고,
    // 기준점에서 시간 안에 퍼져 나가면서 갈 수 있는 곳을 찾아야하므로
    // BFS로 찾아야 한다고 생각함
    static int[][] map;
    static boolean[][] visited;
    static int n, m, r, c, l;
    // 상, 하, 좌, 우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testNum = Integer.parseInt(br.readLine().trim());

        for (int i = 1; i < testNum + 1; i++) {
            st = new StringTokenizer(br.readLine().trim());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());

            map = new int[n][m];
            visited = new boolean[n][m];

            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int k = 0; k < m; k++) {
                    map[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            // 입력값 받기
            bfs(r, c);

            int count = 0;
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (visited[j][k]) {
                        count++;
                    }
                }
            }
            System.out.println("#" + i + " " + count);
        }
    }

    public static void bfs(int x, int y) {
        // 해당 위치와 타입과 몇 번째 칸인지 알 수있는 클래스 생성해서
        // 해당 객체 큐에 넣기
        Queue<preType> q = new LinkedList<>();
        int count = 0;

        q.add(new preType(x, y, map[r][c], count));
        visited[r][c] = true;

        // while 문으로 서로 이동할 수 있는 타입이면 큐에 넣고 빼면서
        // 답 도출하기
        while (!q.isEmpty()) {
            preType type = q.poll();

            int typeNum = type.getType();
            int typeCount = type.getCount();
            x = type.getX();
            y = type.getY();

            // 카운트를 0 부터 시작하므로
            // 카운트가 시간리미트 - 1이 하나라도 나오는 순간
            // 리턴하기
            if (typeCount == l - 1) {
                return;
            }

            // 4방 탐색하기
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 상 하 좌 우 0 1 2 3
                // 경계값 외의 조건으로 돌면서
                // 방문을 안하고
                // i(상 하 좌 우)의 값마다 해당 조건을 분기시켜서
                // 큐에 넣고, 방문 처리
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                   if (!visited[nx][ny]) {
                        if (i == 0) {
                            if ((typeNum == 1 || typeNum == 2 || typeNum == 4 || typeNum == 7)
                                    && (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5 || map[nx][ny] == 6)) {
                                q.add(new preType(nx, ny, map[nx][ny], typeCount + 1));
                                visited[nx][ny] = true;
                            }
                        } else if (i == 1) {
                            if ((typeNum == 1 || typeNum == 2 || typeNum == 5 || typeNum == 6)
                                    && (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 7)) {
                                q.add(new preType(nx, ny, map[nx][ny], typeCount + 1));
                                visited[nx][ny] = true;
                            }
                        } else if (i == 2) {
                            if ((typeNum == 1 || typeNum == 3 || typeNum == 6 || typeNum == 7)
                                    && (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5)) {
                                q.add(new preType(nx, ny, map[nx][ny], typeCount + 1));
                                visited[nx][ny] = true;
                            }
                        } else {
                            if ((typeNum == 1 || typeNum == 3 || typeNum == 4 || typeNum == 5)
                                    && (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 6 || map[nx][ny] == 7)) {
                                q.add(new preType(nx, ny, map[nx][ny], typeCount + 1));
                                visited[nx][ny] = true;
                            }
                        }
                   }
                }
            }
        }
    }
    static class preType {
        int x;
        int y;
        int type;
        int count;
        public preType(int x, int y, int type, int count) {
            this.x = x;
            this.y = y;
            this.type = type;
            this.count = count;
        }
        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
        public int getType() {
            return type;
        }

        public int getCount() {
            return count;
        }
    }
}