import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SWEA_4193_김민호 {
    static int n;
    static int[][] map;
    static boolean[][] whirlpool;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[] init = new int[2];
    static int[] object = new int[2];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testNum = Integer.parseInt(br.readLine().trim());

        for (int i = 1; i < testNum + 1; i++) {
            n = Integer.parseInt(br.readLine().trim());

            map = new int[n][n];
            whirlpool = new boolean[n][n];
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int k = 0; k < n; k++) {
                    map[j][k] = Integer.parseInt(st.nextToken());
                    if (map[j][k] == 2) {
                        whirlpool[j][k] = true;
                    }
                }
            } // 입력값 받기, 소용돌이 지도 따로 만들어 주기

            // 시작 위치 저장
            st = new StringTokenizer(br.readLine().trim());
            init[0] = Integer.parseInt(st.nextToken());
            init[1] = Integer.parseInt(st.nextToken());

            // 목표 위치 저장
            st = new StringTokenizer(br.readLine().trim());
            object[0] = Integer.parseInt(st.nextToken());
            object[1] = Integer.parseInt(st.nextToken());

            System.out.println("#" + i + " " + bfs(init[0], init[1]));

        }
    }

    public static int bfs(int x, int y) {
        // 최소 시간 찾기 위해 bfs 사용
        visited = new boolean[n][n];
        Deque<int[]> q = new ArrayDeque<>();
        visited[x][y] = true;
        q.offer(new int[] {x, y, 0});

        while(!q.isEmpty()) {
            int[] arr = q.poll();

            // 시간 조건에 따라 소용돌이 지도에서 소용돌이 만들었다가 지우기 반복
            for(int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(whirlpool[i][j] == true && (arr[2] + 1) % 3 == 0) {
                        map[i][j] = 0;
                    } else if (whirlpool[i][j] == true && (arr[2] + 1) % 3 != 0 ) {
                        map[i][j] = 2;
                    }
                }
            }

            // 해당 위치에 도착한다면 소요시간 반환
            if (arr[0] == object[0] && arr[1] == object[1]) {
                return arr[2];
            }

            for (int i = 0; i < 4; i++) {
                int nx = arr[0] + dx[i];
                int ny = arr[1] + dy[i];

                // 맵을 벗어나면 컨티뉴
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                // 소용돌이 위치에 아직 방문한 적이 없다면, 소용돌이 전의 위치에서 1초 대기
                if (!visited[nx][ny] && map[nx][ny] == 2) {
                    q.offer(new int[] {arr[0],arr[1],arr[2]+1});

                    // 소용돌이 위치에 소용돌이가 있고, 방문한 적이 있다면
                    // 현재 소용돌이 위치에 있을 수 있으므로, 해당 위치 그대로 있으면서
                    // 다음 위치에 소용돌이가 있다면 소용돌이 전의 위치에서 1초 대기
                    // 소용돌이가 연속으로 있는 위치에서가 0 보다 먼저 도착할 수 있으므로
                    // offerfirst로 넣어준다.
                } else if (visited[arr[0]][arr[1]] && map[arr[0]][arr[1]] == 2 && map[nx][ny] == 2) {
                    q.offerFirst(new int[] {arr[0],arr[1],arr[2]+1});

                    // 소용돌이가 없고, 다음 위치에 방문한 적이 없다면 한 칸 옮기고 1초 추가
                }  else if (!visited[nx][ny] && map[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    q.offer(new int[] {nx,ny,arr[2]+1});
                }
            }
        }
        // 갈 수 없다면 -1 반환
        return -1;
    }
}