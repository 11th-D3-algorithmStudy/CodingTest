import java.io.*;
import java.util.*;

public class SOFT_6246_이윤주 {
    //0:빈칸, 1 : 벽
    //출발점 : 첫번째방문지점, 도착점 : 마지막 방문지점
    //항상 상하좌우 인접칸으로만 이동
    //n*n 격자 내에서 m개의 지점 순서대로 방문
    //한번 지났던 지점은 다시 방문 x
    //이동가능한 서로 다른 가지수를 구하라
    //가능한 방법이 없다면 0출력

    static int N, M;
    static int[][] grid;
    static boolean[][] visited;
    static int[][] target; //m개의 지점을 저장
    static boolean possible;
    static int count; //지점 방문 개수 저장
    static int result; //이동가능한 가지수 저장
    static int[] dr = {-1, 1, 0, 0}; //델타배열 상하좌우
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][N];
        visited = new boolean[N][N];
        target = new int[M][2];
        possible = false;
        count = 1;
        result = 0;
        
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            target[i][0] = r - 1; //0 : 방문지점 r
            target[i][1] = c - 1; //1 : 방문지점 c
            //grid[r - 1][c - 1] = 2; //격자에 지점 표시
        }

        visited[target[0][0]][target[0][1]] = true;
        findWay(target[0][0], target[0][1], 0);

        System.out.println(result);
    }
    //r, c : 현재위치, cnt : 방문개수
    public static void findWay(int r, int c, int cnt){
        //기저조건
        if(cnt == M-1 && r == target[M-1][0] && c == target[M-1][1]){
            //마지막 지점에 도착
            result++;
            return;
        }

        //현재 위치가 방문지점일 때
        if(r == target[cnt][0] && c == target[cnt][1]){
            cnt++; //다음 지점 찾기
        }

        //현재 위치에서 할 것
        for(int d = 0; d < 4; d++){
            int nr = r + dr[d];
            int nc = c + dc[d];
            
            if(nr < 0 || nr >= N || nc < 0 || nc >= N)
                continue;

            if(!visited[nr][nc] && grid[nr][nc] == 0){
                visited[nr][nc] = true;
                findWay(nr, nc, cnt);
                visited[nr][nc] = false;
            }
        }
        
    }
}
