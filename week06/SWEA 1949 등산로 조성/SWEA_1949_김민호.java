import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1949_김민호 {
    // 등산로를 한 줄로 이어가면서 찾아야하므로
    // DFS 로 풀어야 겠다고 생각함
    // 공사를 해야하므로 공사를 할 때와 안 할 때를 나눠서
    // 풀어야겠다고 생각함
    static boolean[][] visited;
    static int n, k;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int count;
    static int maxCount;
    static boolean isUseDig;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testNum = Integer.parseInt(br.readLine().trim());

        for (int i = 1; i < testNum + 1; i++) {
            st = new StringTokenizer(br.readLine().trim());

            int max = Integer.MIN_VALUE;
            maxCount = Integer.MIN_VALUE;

            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            map = new int[n][n];

            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int l = 0; l < n; l++) {
                    map[j][l] = Integer.parseInt(st.nextToken());
                    if (map[j][l] > max) {
                        max = map[j][l];
                    }
                }
            }
            // 입력값 받기

            for (int j = 0; j < n; j++) {
                for (int l = 0; l < n; l++) {
                    if (map[j][l] == max) {
                        visited = new boolean[n][n];
                        count = 0;
                        dfs(j, l);

                    }
                }
            }
            System.out.println("#" + i + " " + maxCount);
        }
    }

    public static void dfs(int x, int y) {
        // 한 칸 들어갈 때마다 count 한 개씩 올려주기
        visited[x][y] = true;
        count++;

        // 카운트가 작을 때마다 새로 갱신
        if (count > maxCount) {
            maxCount = count;
        }


        // 4방 탐색 실행
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 경계값 기준으로 공사를 할 때 안 할때를 나눔
            if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                // 공사를 안 했으면 최대 공사 깊이 까지 반복문 돌리면서
                // j가 1 이상이면 공사를 했다는 boolean 체크를 함
                // 후에 해당 j의 반복이 끝나면 공사 했다는 체크 다시 false로 고치기
                if (!isUseDig) {
                    for (int j = 0; j < k + 1; j++) {
                        map[nx][ny] = map[nx][ny] - j;

                        if (j != 0) {
                            isUseDig = true;
                        }

                        if (!visited[nx][ny] && map[nx][ny] < map[x][y]) {
                            dfs(nx, ny);
                            count--;
                            visited[nx][ny] = false;
                        }
                        map[nx][ny] = map[nx][ny] + j;
                        isUseDig = false;
                    }
                    // 공사를 했다면 후에는 추가 공사를 할 수 없으므로
                    // 아래와 같이 map 그대로의 크기로 등산로 찾기
                } else {
                    if (!visited[nx][ny] && map[nx][ny] < map[x][y]) {
                        dfs(nx, ny);
                        count--;
                        visited[nx][ny] = false;
                    }
                }
            }
        }
    }
}