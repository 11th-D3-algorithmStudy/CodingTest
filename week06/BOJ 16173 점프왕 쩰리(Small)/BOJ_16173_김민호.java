import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16173_김민호 {
    // 한 가지의 경우로 쭉 나가면서 도착지점까지 갈 수 있을지를
    // 알아보면 답을 도출 할 수 있어 dfs로 풀기로 생각함
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean find = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine().trim());
        map = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 입력값 받기

        // 0, 0 좌표에서 dfs 시작
        dfs(0, 0, n);

        // 도착지점에 도착하지 못하면 Hing 출력
        if (!find) {
            System.out.println("Hing");
        }
    }
    public static void dfs(int x, int y, int n) {
        // 해당 좌표에 오면 방문 처리
        visited[x][y] = true;

        // 4방 탐색 중
        // 해당 맵의 값만큼은 이동을 해야하므로
        // map[x][y] 만큼을 곱해준다
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i] * map[x][y];
            int ny = y + dy[i] * map[x][y];

            // 경계값과 미방문 확인 후
            // 방문 처리 및 도착 목표지점에 도착하면 find 는 true
            if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny]) {
                visited[nx][ny] = true;

                if (map[nx][ny] == -1) {
                    find = true;
                    // 트루 일 시 HaruHaru 출력
                    System.out.println("HaruHaru");
                }
                dfs(nx, ny, n);
            }
        }
    }
}