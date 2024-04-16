package AlgorithmStudy;

import java.io.*;
import java.util.*;
  
public class SWEA_4193_성민기 {
     
    /* 풀이시간 : 240409 20:11 ~ 22:30 + 11:50 ~ 00:38
     * 메인접근법 : 
     *     - 최단거리를 구하는 것이므로 BFS를 사용
     *     - 일반적인 BFS가 아닌 소용돌이 시간 체크를 위한 for문 삽입함 
     *         => for(int i=0; i<size; i++)
     *     - second 증가시키는 위치는 모든 방향이 진행이 끝났을 때 해줘야 함
     * 
     * 막힌 부분 :
     *     - DFS로 시도 => 해결하지 못해서 최단거리 구하는 BFS로 시도
     *     - BFS 시도 막힘 => 소용돌이 Case 구함    
     *     
     * 메모리 : 26384 kb / 실행시간 : 97 ms
     */
     
    static int[][] map;
    static boolean[][] visited;
    static int startR, startC, endR, endC, second, N;
      
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
          
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new boolean[N][N];
              
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            st = new StringTokenizer(br.readLine());
            startR = Integer.parseInt(st.nextToken());
            startC = Integer.parseInt(st.nextToken());
              
            st = new StringTokenizer(br.readLine());
            endR = Integer.parseInt(st.nextToken());
            endC = Integer.parseInt(st.nextToken());
            second = 0;
            int result = BFS(startR, startC);
             
            // 출발지와 도착지가 같은 경우도 있음 => 예외 상황
            // 여기서 1개 차이로 실패함
            if(startR == endR && startC == endC) {
                System.out.println("#" + t + " " + 0);
                continue;
            }
            System.out.println("#" + t + " " + result);
        }
    }
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
      
    public static int BFS(int r, int c) {
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int[] {r, c});
        visited[r][c] = true;
          
        while(!queue.isEmpty()) {
            int size = queue.size(); // 소용돌이 사라지는 시간 체크를 위함
            // while(size-- > 0) 으로도 가능 
            // 현재 큐에 있는 모든 위치에 대해서 처리한다
            for(int i=0; i<size; i++) { 
                int[] now = queue.poll();
                if(now[0]==endR && now[1]==endC) return second;
                  
                // 델타 진행
                for(int d=0; d<4; d++) {
                    int nr = now[0] + dr[d];
                    int nc = now[1] + dc[d];
                      
                    // 경계값
                    if(nr >= 0 && nr < N && nc >= 0 && nc < N
                            && !visited[nr][nc] && map[nr][nc]!=1) {
                        // 2일 경우에는 두 가지로 나뉨
                        if(map[nr][nc]==2) {
                            // (second+1)을 3의 배수로 나누었을 때 나머지가 0이면 통과
                            if((second+1) % 3 == 0) {
                                visited[nr][nc] = true;
                                queue.add(new int[] {nr, nc});
                            } else {
                                // 아니라면 방문처리
                                visited[now[0]][now[1]] = true;
                                queue.add(new int[] {now[0], now[1]});
                            }
                        } else {
                            visited[nr][nc] = true;
                            queue.add(new int[] {nr, nc});
                        }
                    }
                }
            }
            second++; // 여기서 ++해줘야
            // 현재 큐의 위치들을 모두 처리한 후 시간을 증가시켜야한다.
        }
        // 모두 해당하지 않은 경우 -1 출력
        // 여기서 이 처리를 해줘야 통과되었다.
        return -1;
    }
      
}