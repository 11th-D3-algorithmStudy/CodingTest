import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_16918_이상휘 {

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 행
        int R = Integer.parseInt(st.nextToken());
        // 열
        int C = Integer.parseInt(st.nextToken());
        // N초 후
        int N = Integer.parseInt(st.nextToken());

        // 배열 맵 (1초, 입력값)
        char[][] map = new char[R][C];
        // 모든 폭탄 맵 (2초,4초)
        char[][] map2 = new char[R][C];
        // 설치 된 폭탄 터진 맵 (새로 시작되는 1초)
        char[][] map3 = new char[R][C];
        // 초기 입력 배열 터짐 (3초)
        char[][] map4 = new char[R][C];


        // 초기값 설장
        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C ; c++) {
                map[r][c] = '.';
                map2[r][c] = 'O';
                map3[r][c] = 'O';
                map4[r][c] = 'O';
            }
        }

        for(int r = 0; r < R; r++) {
            String t = br.readLine();
            for(int c = 0; c < C ; c++) {
                char tmp = t.charAt(c);
                // 입력값 << map에 담음
                map[r][c] = tmp;
                if (tmp == 'O') {
                    // 처음 값이 터졌을때 (입력값 = "O"인 위치를 델타로 터트림) map4
                    map4[r][c] = '.';
                    for(int k =0; k<4; k++) {
                        int nr = r+dr[k];
                        int nc = c+dc[k];
                        if(nr <0 || nc <0 || nr >= R || nc>= C) {
                            continue;
                        }map4[nr][nc] = '.';
                    }
                }
            }
        }
        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C ; c++) {
                char tmp = map4[r][c];
                // 처음 입력된 것들이 터진 상태에서, 델타를 돌려서 터트림( 모듈러 연산 후 1초)
                if (tmp == 'O') {
                    map3[r][c] = '.';
                    for(int k =0; k<4; k++) {
                        int nr = r+dr[k];
                        int nc = c+dc[k];
                        if(nr <0 || nc <0 || nr >= R || nc>= C) {
                            continue;
                        }map3[nr][nc] = '.';
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        if (N == 1) {
            for(int i = 0; i <R ; i++) {
                for(int j = 0; j <C ; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
        } else {
            if(N % 2 == 0) {
                for(int i = 0; i <R ; i++) {
                    for(int j = 0; j <C ; j++) {
                        sb.append(map2[i][j]);
                    }
                    sb.append("\n");
                }
            }
            else if (N % 4 == 3){
                for(int i = 0; i <R ; i++) {
                    for(int j = 0; j <C ; j++) {
                        sb.append(map4[i][j]);
                    }
                    sb.append("\n");
                }
            } else if (N % 4 ==1) {
                for(int i = 0; i <R ; i++) {
                    for(int j = 0; j <C ; j++) {
                        sb.append(map3[i][j]);
                    }
                    sb.append("\n");
                }
            }
        }
        System.out.println(sb);

    }
}
