import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2638_김민호 {
    static int n, m;
    static int[][] map;
    static int[][] externalAir;
    static boolean[][] visited;
    static int count;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    count++;
                }
            }
        } // 입력값 받으면서 치즈 개수 세기

        int time = 0;

        // 치즈가 0이 될 때까지 반복
        while (count > 0) {
            // 외부의 공기 영역 구해주기
            // 맨 가장자리는 치즈가 놓이지 않는다고 문제에 조건 명시되었으므로
            // (0,0) 기준으로 외부 공기 영역 구해준다.
            externalAir = new int[n][m];
            dfs(0,0);

            visited = new boolean[n][m];

            // 전체 지도에서 치즈가 있는 곳에서 check 시작
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if(map[i][j] == 1) {
                        check(i, j);
                    }
                }
            }
            // check가 1회 끝나면 1초 증가
            time++;
        }
        System.out.println(time);
    }

    public static void check(int x, int y) {
        // 외부공기에 닿는 부분이 2 이상인 치즈를 찾으면
        // 해당 부분을 지도에서 0으로 만들고 치즈 개수 1감소
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] || externalAir[nx][ny] == 0) continue;
            cnt++;
        }
        if (cnt >= 2) {
            map[x][y] = 0;
            visited[x][y] = true;
            count--;
        }
    }

    public static void dfs(int x, int y) {
        // (0,0) 기준으로 치즈가 경계인 부분 이외부분을 1로 바꿔서
        // 외부 공기 영역 정하기
        externalAir[x][y] = 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

            if (map[nx][ny] == 0 && externalAir[nx][ny] == 0) {
                externalAir[nx][ny] = 1;
                dfs(nx, ny);
            }
        }
    }
}
