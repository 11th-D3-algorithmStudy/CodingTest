package AlgorithmStudy;

import java.io.*;
import java.util.*;

public class BOJ_13903_성민기 {

    static int[][] map;
    static boolean[][] visited;
    static int[] dr;
    static int[] dc;
    static int R, C;
    static int count;

    public static void main(String[] args) throws IOException{
        
    	/* 풀이시간 : 240304 16:59 ~ 3만년걸림
    	 * 메인풀이법 :
    	 *       - BFS 사용(BFS가 최단거리 찾는 것을 보장한다고함)
    	 *       - 이번에도 cnt++가 아닌 cnt+1을 하였음
    	 *       - 가능한 모든 시작점을 큐에 넣은 BFS를 한번만 돌림
    	 *       - 마지막 행의 값에 도달하면 그 떄의 깊이(cnt)를 return
    	 * 막힌 부분 :
    	 *       1. 시간초과 : main메서드에서 첫번째 행의 값이 1일 때
    	 *                  BFS를 계속 호출했었는데 이 부분이 시간초과의 원인
    	 *          해결 -> BFS 메소드 안에서 큐에다가 시작 위치값을 모두 넣어서 진행
    	 *                 이러면 BFS를 한번만 돌려도 된다.
    	 *       2. 틀렸습니다 : 방문처리와 BFS메소드에 값을 넣어주는 식으로 했다가
    	 *                   입력값없이 돌아가도록 수정
    	 *                   ex) BFS(int r,int c) -> BFS()
    	 *       
    	 * 해결 방법 : 갓영호의 코드리뷰로 해결
    	 * 
    	 * 메모리 : 123584 KB
    	 * 시간 : 676 ms
    	 */
    	
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visited = new boolean[R][C];

        for(int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int N = Integer.parseInt(br.readLine());
        dr = new int[N];
        dc = new int[N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            dr[i] = a;
            dc[i] = b;
        }

        // BFS() 함수호출
        int ans = BFS();
        if(ans==Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);
    }

    public static int BFS() {
        Queue<int []> queue = new LinkedList<>();
        
        // BFS를 여러번 호출하는 것보다는
        // 가능한 모든 시작점을 큐에 넣은 뒤에
        // BFS를 한번만 돌리면 된다.
        for (int i = 0; i < C; i++) {
            if (map[0][i] == 1) {
            	// 초기값 대입(count)
                queue.offer(new int[] {0, i, count});
                // 방문처리를 여기서 처음 해주면 됨.
                visited[0][i] = true;
            }
        }

        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            // 깊이값은 now[2]
            int cnt = now[2];
            if(now[0] == R-1) {
                return cnt;
            }

            for(int d=0; d<dr.length; d++) {
                int nr = now[0] + dr[d];
                int nc = now[1] + dc[d];

                if(nr >= 0 && nr < R && nc >= 0 && nc < C) {
                    if(map[nr][nc] == 1 && !visited[nr][nc]) {
                    	// 방문 처리 여기다가
                        visited[nr][nc] = true;
                        queue.offer(new int[] {nr, nc, cnt+1});
                    }
                }
            }
        }
        // 해당이 되지 않으면 다음 값 리턴
        return Integer.MAX_VALUE;
    }
}