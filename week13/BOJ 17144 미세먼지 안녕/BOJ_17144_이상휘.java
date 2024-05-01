import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BOJ_17144_이상휘 {

    // 1. 미세먼지의 확산
    // 2. 공기청정기의 순환
    // 3. 공기청정기의 좌표로 들어가는 미세먼지는 소멸.

    static int[][] map;
    static int[][] diffusionMap;
    static int R;
    static int C;
    static int[] dr = new int[]{-1,1,0,0};
    static int[] dc = new int[]{0,0,-1,1};

    static int upR;
    static int upC;
    static int downR;
    static int downC;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 방의 크기 행(r)
        R = Integer.parseInt(st.nextToken());
        // 방의 크기 열(c)
        C = Integer.parseInt(st.nextToken());
        // 수행횟수T
        int T = Integer.parseInt(st.nextToken());
        // 공기청정기의 위치 = -1

        map = new int[R][C];
        diffusionMap = new int[R][C];

        boolean up = false;

        // 맵에 값 담기.
        for (int i = 0; i < R; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st2.nextToken());
                diffusionMap[i][j] = map[i][j];
                if (map[i][j] == -1) {
                    if (up) {
                        downR = i;
                        downC = j;
                        continue;
                    }
                    upR = i;
                    upC = j;
                    up = true;
                }
            }
        }

        for (int i = 0; i < T; i++) {
            diffusion();
            circulation();
        }
        int ans = 2;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                ans += map[i][j];
            }
        }
        System.out.println(ans);
    }
    // 확산 -> 완전 탐색 후, 각 지점에서 계산
    // 공기 청정기로 빨려 들어가는 경우만 생각,
    static void diffusion() {
        // 확산 case
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] == -1 || map[r][c] == 0) {
                    continue;
                }
                int tmp = map[r][c] / 5;
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nr = r + dr[k];
                    int nc = c + dc[k];
                    if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                        continue;
                    }
                    if (map[nr][nc] == -1) {
                        continue;
                    }
                    diffusionMap[nr][nc] += tmp;
                    cnt++;
                }
                diffusionMap[r][c] -= (tmp * cnt);
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = diffusionMap[i][j];
            }
        }
    }

    static void circulation() {
        // check point
        // 1. 상부지점 ( 반시계순환)
        // 1-1. 공기청정기를 만나는 지점 (upR, 0)
        // 1-2. 상부 방향 변경지점
        // 1-2-1. 위쪽으로 이동 (upR, C)
        // 1-2-2. 왼쪽으로 이동 (0, C)
        // 1-2-3. 아래로 이동 (0,0)
        // 1-2-4. 사라지는 먼지 위치 (upR-1,0)
        // 2. 하부지점 ( 시계순환)
        // 2-1. 공기청정기를 만나는 지점 (downR, 0)
        // 2-2. 하부 반환 변경지점
        // 2-2-1. 아래로 이동 (downR, C)
        // 2-2-2. 왼쪽으로 이동 (R, C)
        // 2-2-3. 위로 이동 (R,0)
        // 2-2-4. 사라지는 먼지 위치 (downR+1,0)

        // 오른쪽으로 이동 (열의 인덱스만 신경쓰면 됨)
        for (int c = 2; c < C; c++) {
            diffusionMap[upR][c] = map[upR][c-1];
            diffusionMap[downR][c] = map[downR][c-1];
        }
        // 왼쪽으로 이동 (열의 인덱스만 신경쓰면 됨)
        for (int c = C-2; c >= 0; c--) {
            diffusionMap[0][c] = map[0][c+1];
            diffusionMap[R-1][c] = map[R-1][c+1];
        }
        // c = C인 지접
        for (int r = upR-1; r >= 0 ; r--) {
            diffusionMap[r][C-1] = map[r+1][C-1];
        }
        for (int r = downR+1; r <= R-1 ; r++) {
            diffusionMap[r][C-1] = map[r-1][C-1];
        }
        // c = 0인 지점
        for (int r = 1; r < upR; r++) {
            diffusionMap[r][0] = map[r-1][0];
        }
        for (int r = R-2; r > downR; r--) {
            diffusionMap[r][0] = map[r+1][0];
        }
        diffusionMap[upR][1] = 0;
        diffusionMap[downR][1] = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = diffusionMap[i][j];
            }
        }
    }
}
