package _0225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_16268_김민호 {
    // 전체 순회를 하면서 해당 숫자에서 4방 탐색을 한 후
    // 조건에 맞게 숫자를 세면 될 것같다고 생각
    static int n;
    static int m;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int sum;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testNum = Integer.parseInt(br.readLine().trim());

        for (int i = 1; i < testNum + 1; i++) {
            st = new StringTokenizer(br.readLine().trim());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            max = Integer.MIN_VALUE;

            map = new int[n][m];

            // map 에 대한 값 입력
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int k = 0; k < m; k++) {
                    map[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            // map에 대해서 전체 순회
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    // 해당 값에 대해 4방 탐색 실행
                    findMax(j, k);

                    // 최대값 재할당
                    if (sum > max) {
                        max = sum;
                    }
                    sum = 0;
                }
            }
            System.out.println("#" + i + " " + max);
        }
    }

    // 4방 탐색 하면서
    // 조건에 맞으면 sum 합산
    public static void findMax(int x, int y) {
        sum += map[x][y];

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                sum += map[nx][ny];
            }
        }
    }
}