import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2115_김민호 {
    static int n, m, c;
    static int[][] map;
    static int[] arr;
    static int max;
    static int maxX, maxY;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine().trim());

        for (int i = 1; i < t + 1; i++) {
            st = new StringTokenizer(br.readLine().trim());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            map = new int[n][n];

            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int k = 0; k < n; k++) {
                    map[j][k] = Integer.parseInt(st.nextToken());
                }
            } // 입력값 받기

            max = maxX = maxY = 0;

            // 전체 맵을 기준으로 조건에 맞는 개수에 대한 max 값 찾기
            select();

            // 찾은 max 값 임시저장하고
            // max가 나온 부분은 전체 맵에서 0으로 재할당
            int max1 = max;
            for (int j = maxX; j < maxX + 1; j++) {
                for (int k = maxY; k < maxY + m; k++) {
                    map[j][k] = 0;
                }
            }

            // 전체 맵에서 0인 부분을 제외하고
            // 위를 반복해서 max 값 찾기
            max = 0;
            select();

            // 두 개의 max값 합해서 최종 답 도출
            System.out.println("#" + i + " " + (max + max1));
        }
    }

    public static void select() {
        // 슬라이딩 윈도우를 사용해서 조건에서 제시하는 개수를 뽑기
        arr = new int[m];

        for (int i = 0; i < n; i++) {
            int l = 0;
            int r = m;
            int num = 0;
            while (r < n + 1) {
                num = 0;
                for (int j = l; j < r; j++) {
                    arr[num++] = map[i][j];
                }
                out : for (int k = 1; k <= m; k++) {
                    // 첫 번째 뽑기에서 max 값을 찾은 후
                    // 0으로 바꾼 부분은 제외하기 위한 반복문
                    visited = new boolean[m];
                    for (int q = 0; q < m; q++) {
                        if (arr[q] == 0) {
                            break out;
                        }
                    }
                    // 뽑은 개수에 대해서 벌꿀 채취할 수 있는 경우의 수 구하기
                    comb(0, 0, k, i, l);
                }
                l++;
                r++;
            }
        }
    }

    public static void comb(int start, int count, int limit, int x, int y) {
        if (count == limit) {
            // 조합으로 가능한 경우의 수 구한 후,
            // 최대값을 뽑기 위한 메서드
            int n = calMax();
            if (n > max) {
                max = n;
                maxX = x;
                maxY = y;
            }
            return;
        }

        for (int i = start; i < m; i++) {
            if (!visited[i]) {
                visited[i] = true;
                comb(i + 1, count + 1, limit, x, y);
                visited[i] = false;
            }
        }

    }

    public static int calMax() {
        // 조합으로 뽑은 것들 중에서
        // c 초과라면 해당 벌꿀은 채취할 수 없으므로 0 반환
        // c 이하라면 값을 계산해서 해당 값 반환
        int[] list = new int[m];
        int sum = 0;
        int calNum = 0;
        for (int i = 0; i < m; i++) {
            if (visited[i]) {
                list[i] = arr[i];
                sum += arr[i];
            }
        }

        if (sum > c) {
            return 0;
        } else {
            for (int i = 0; i < m; i++) {
                if (list[i] != 0) {
                    calNum += (int) Math.pow(list[i], 2);
                }
            }
        }
        return calNum;
    }
}
