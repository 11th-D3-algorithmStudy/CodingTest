import java.util.*;

class Solution {
    
    static boolean[][][] visited; //행, 열, 방향 방문체크
    static int min, N; //최솟값, 배열크기
    
    //우 하 좌 상
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    
    public int solution(int[][] board) {
        
        //코너 500원, 직선도로 100원
        //경주로를 건설하는데 필요한 최소 비용
        //우 하 좌 상 1칸씩 이동
        //0 1 2 3
        //bfs(보드)
        N = board.length;
        min = Integer.MAX_VALUE;
        visited = new boolean[N][N][4]; //4방향별 방문체크
        bfs(board); 
        
        return min;
    }
    
    public void bfs(int[][] board){
        Queue<int[]> queue = new ArrayDeque<>();
        
        queue.offer(new int[] {0, 0, -1, 0});
        
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int r = now[0];
            int c = now[1];
            int dir = now[2];
            int cost = now[3];
            
            if(r == N - 1 && c == N - 1){
                min = Math.min(min, cost);
                continue; //다른 경로에서 최솟값이 나올 수 있으므로 계속함
            }
            
            for(int d = 0; d < 4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if(board[nr][nc] == 1) continue;
                
                int nextCost = cost;
                if(dir == d || dir == -1){ //시작위치인경우, 직선인 경우
                    nextCost += 100;
                } else {
                    nextCost += 600; //코너인 경우
                }
                
                if(!visited[nr][nc][d] || board[nr][nc] >= nextCost){
                    //방문하지 않은 곳 or 방문했지만 더 낮은비용으로 갈 수 있는 경우
                    visited[nr][nc][d] = true;
                    board[nr][nc] = nextCost;
                    queue.offer(new int[]{nr, nc, d, nextCost});
                }
            }
        }
    }
}
