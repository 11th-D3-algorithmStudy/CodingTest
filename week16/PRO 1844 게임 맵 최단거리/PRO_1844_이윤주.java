import java.util.*;

class Solution {
    static boolean[][] visited;
    static boolean possible; //상대 팀 진영에 도착하는지 표시
    static int min;
    static int N, M; //행, 열 개수
    //상하좌우 4방탐색
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public int solution(int[][] maps) {
        N = maps.length; //행 개수
        M = maps[0].length; //열 개수
        
        possible = false;
        visited = new boolean[N][M];
        min = Integer.MAX_VALUE;
         
        bfs(0, 0, maps); //이동 횟수는 1부터 시작
        
        //도착할 수 없는 경우 -1
        if(!possible) return -1;
        
        //상대팀 진영에 도착하는 이동횟수 최솟값 return
        return min;
    }
    
    public void bfs(int row, int col, int[][] maps){
        Queue<int[]> queue = new ArrayDeque<>();
        
        queue.add(new int[] {row, col, 1}); //현재위치(행, 열), 이동횟수(depth)
        
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int r = now[0];
            int c = now[1];
            int depth = now[2];
            
            //지금 상대팀 진영에 도착했는지 확인
            if(r == N - 1 && c == M - 1){
                possible = true;
                min = depth;
                return;
            }
            
            for(int d = 0; d < 4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                //범위 벗어나면 스킵
                if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                
                //벽이거나 갔던 곳이면 스킵
                if(maps[nr][nc] == 0 || visited[nr][nc]) continue;
                
                visited[nr][nc] = true; //방문체크
                queue.add(new int[] {nr, nc, depth + 1});
            }
        }
    }
}