import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map1;
    static int[][] map2;
    static int x,y,val1, val2;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());

        boolean find = false;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map1 = new int[n][m];
        map2 = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < m; j++) {
                map1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < m; j++) {
                map2[i][j] = Integer.parseInt(st.nextToken());

                if (map1[i][j] != map2[i][j] && !find) {
                    x = i;
                    y = j;
                    val1 = map1[i][j];
                    val2 = map2[i][j];
                    find = true;
                }
            }
        }

        if (!find) {
            System.out.println("YES");
        } else {
            dfs(x, y);

            boolean isCorrect = true;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map1[i][j] != map2[i][j]) {
                        isCorrect = false;
                        break;
                    }
                }
            }
            if (isCorrect) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    public static void dfs(int x, int y) {
        visited[x][y] = true;
        map1[x][y] = val2;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                if (!visited[nx][ny] && map1[nx][ny] == val1) {
                    dfs(nx, ny);
                }
            }
        }
    }
}
