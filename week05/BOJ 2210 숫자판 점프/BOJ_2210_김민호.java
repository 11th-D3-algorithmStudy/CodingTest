package zz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_2210_김민호 {
    static String[][] map;
    static Set<String> set;
    static String s = "";
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력값 받아서 map 넣기
        map = new String[5][5];
        set = new HashSet<>();

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < 5; j++) {
                map[i][j] = st.nextToken();
            }
        }

        // 전체 순회 하면서 find함수 실행
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                s = "";
                find(i, j);
            }
        }
        System.out.println(set.size());
    }
    // map을 전체 순회하면서 4방 탐색
    // 길이가 6이 되면 set에 add

    public static void find(int x, int y) {
        s += map[x][y];

        if (s.length() == 6) {
            set.add(s);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5) {
                find(nx, ny);

                if (s == null) {
                    continue;
                }

                // s의 길이가 6이여서 리턴되면 마지막값 삭제
                s = s.substring(0, s.length() - 1);
            }
        }
    }
}