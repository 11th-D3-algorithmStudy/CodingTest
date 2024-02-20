import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2567_김민호 {
    // 색종이를 길이만큼 1로 넣어서 1의 개수를 세어줌
    // 예시의 답은 96인데 88이 나오길래
    // 내부의 구멍난 곳, 종이가 곂치는 곳 등등
    // 무슨 값이 빠졌는지 확인하다가
    // 꼭지점 값이 빠졌다는 것을 확인하고, 꼭지점 일 때의 값은
    // 중복으로 더해지도록 코드를 고쳤다.

    // 주변의 0을 탐색하기 위한 delta
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][] visited;
    static int perimeter;
    static final int LIMIT = 102;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 전체 도화지를 만들기
        // 100에 2를 더해줘서 경계조건을 배제함
        map = new int[LIMIT][LIMIT];
        visited = new boolean[LIMIT][LIMIT];
        int paperNum = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < paperNum; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            // 종이의 개수만큼 종이를 입력받고, 종이의 크기만큼 1을 저장
            for (int j = LIMIT - y - 10 - 1; j < LIMIT - y - 1; j++) {
                for (int k = x + 1; k < x + 10 + 1; k++) {
                    map[j][k] = 1;
                }
            }
        }
        // 전체 도화지의 크기를 순회하면서
        // 종이가 있는 위치 중에, 방문하지 않는 곳에
        // sumPerimeter 함수 사용
        for (int j = 0; j < map.length; j++) {
            for (int k = 0; k < map[0].length; k++) {
                if (map[j][k] == 1 && !visited[j][k]) {
                    sumPerimeter(j, k);
                }
            }
        }
        System.out.println(perimeter);
    }

    // 종이의 영역 중에 상하좌우에 0이 있으면
    // perimeter 값 더하기
    // 상하좌우 중에 0이 한 개면 모서리의 길이를 더하고
    // 꼭지점의 경우 상하좌우에 0이 두개이므로 중복으로 더해짐
    public static void sumPerimeter(int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (map[nx][ny] == 0) {
                perimeter++;
            }
        }
    }
}