import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16918_김민호 {
    static int r, c, n;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static char[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = s.charAt(j);
            }
        }
        // 입력값 받기

        // n이 2의 배수일 때는 모든 맵에 폭탄 설치
        if (n % 2 == 0) {
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    map[i][j] = 'O';
                }
            }
            show();
        } else {
            // n이 2의 배수가 아닐 때는
            // 폭탄을 놓고, 터지는 것이 반복되므로
            // n까지 폭탄을 터뜨리고, 나머지는 폭탄으로 채운다.
           for (int i = 3; i <= n; i++) {
               if (i % 2 == 1) {
                    visited = new boolean[r][c];
                    for (int j = 0; j < r; j++) {
                        for (int k = 0; k < c; k++) {
                            if (map[j][k] == 'O' && !visited[j][k]) {
                                sol(j, k);
                            }
                        }
                    }
                   for (int j = 0; j < r; j++) {
                       for (int k = 0; k < c; k++) {
                           if (!visited[j][k]) {
                               map[j][k] = 'O';
                           }
                       }
                   }
               }
           }
           show();
        }
    }
    public static void show() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
    public static void sol(int x, int y) {
        visited[x][y] = true;
        map[x][y] = '.';

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
            if (!visited[nx][ny] && map[nx][ny] == '.') {
                visited[nx][ny] = true;
            }
        }
    }
}
