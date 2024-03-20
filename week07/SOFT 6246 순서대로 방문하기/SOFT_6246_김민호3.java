import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SOFT_6246_김민호3 {
    static int n, m;
    static int[][] map;
    static int[][] objectPlace;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        visited = new boolean[n][n];
        objectPlace = new int[m][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 입력값 받기


        // 목표 위치 배열에 저장하기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine().trim());
            objectPlace[i][0] = Integer.parseInt(st.nextToken()) - 1;
            objectPlace[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }

        int visitNum = 0;
        // 처음 위치는 미리 방문 처리하기
        visited[objectPlace[0][0]][objectPlace[0][1]] = true;

        // 방문 순서와, 목표로 해야하는 위치 파라미터로 넣고 백트래킹 실행
        sol(visitNum, objectPlace[0][0], objectPlace[0][1]);
        System.out.println(count);
    }
    public static void sol(int visitNum, int objectX, int objectY) {
        // 방문 순서가 마지막까지 왔고, 마지막 목표 위치라면 카운트 올리기
        if (visitNum == m - 1) {
            if (objectX == objectPlace[m - 1][0] && objectY == objectPlace[m - 1][1]) {
                count++;
                return;
            }
        }

        // 방문 순서가 마지막이 아닐 때, 목표 위치에 오면, 방문 순서 1 올리기
        if (objectPlace[visitNum][0] == objectX && objectPlace[visitNum][1] == objectY) {
            sol(visitNum + 1, objectPlace[visitNum][0], objectPlace[visitNum][1]);
        }


        // 4방 탐색을 하면서 백트래킹 실행
        for (int i = 0; i < 4; i++) {
            int nx = objectX + dx[i];
            int ny = objectY + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] == 0) {
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    sol(visitNum, nx, ny);
                    visited[nx][ny] = false;
                }
            }
        }
    }
}