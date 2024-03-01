package _0225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_10760_김민호 {
    // 전체 순회를 하면서 해당 숫자에서 8방 탐색을 한 후
    // 조건에 맞게 숫자를 세면 될 것같다고 생각

    // 8방 탐색
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
    static int[][] map;
    static int count;
    static int candidate;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testNum = Integer.parseInt(br.readLine().trim());

        for (int i = 1; i < testNum + 1; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            candidate = 0;
            map = new int[n][m];

            // map에 대한 값 입력
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine().trim());

                for (int k = 0; k < m; k++) {
                    map[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            // map 전체 순회
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    // 후보지에 대한 8방 탐색 실행
                    findCandidate(j, k);

                    // 개수가 4개 이상이면
                    // 후보지 추가
                    if (count > 3) {
                        candidate++;
                    }
                    count = 0;
                }
            }
            System.out.println("#" + i + " " + candidate);
        }
    }
    // 8방 탐색 하면서
    // 조건에 맞으면 개수 증가
    public static void findCandidate(int x, int y) {
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[x][y] > map[nx][ny]) {
                count++;
            }
        }
    }
}