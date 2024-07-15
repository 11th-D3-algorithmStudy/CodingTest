import java.util.*;

class Solution {
    
    static class Block implements Comparable<Block>{
        int r;
        int c;
        int d;
        int p;
        
        public Block(int r, int c, int d, int p) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.p = p;
        }
        
        public int compareTo(Block o) {
            return this.p - o.p;
        }
    }
    
    static int N; // map 크기
    static int[][] map;
    static boolean[][][] visit;
    static PriorityQueue<Block> queue;
    static int min = 987654321;
    
    static int[] dr = {-1, 1, 0, 0}; 
    static int[] dc = {0, 0, -1, 1}; // 상 하 좌 우 (dir: 0 1 2 3)
    
    public int solution(int[][] board) {
        // 완탐하고 최소값 업데이트 ?,,,, ㅠ
        // dfs
        // 직선인지 코너인지 확인하기 위해 직전 방향 dfs에 전달해줘야 함?
        // 80.0 ... 시간초과 ㅠ
        
        // bfs로 고고
        N = board.length;
        map = board;
        visit = new boolean[N][N][4];
        queue = new PriorityQueue();
        
        visit[0][0][0] = true;
        queue.add(new Block(0, 0, -1, 0));
        bfs();
        
        int answer = min;
        return answer;
    }
    
    static void bfs() {
        
        while(!queue.isEmpty()) {
            Block next = queue.poll();
            if (next.d != -1) {
                visit[next.r][next.c][next.d] = true;
            }
            
            if (next.r == N-1 && next.c == N-1) {
                min = Math.min(min, next.p);
                continue;
            }
        
            for (int d=0; d<4; d++) {
                int nr = next.r+dr[d];
                int nc = next.c+dc[d];

                if (nr>=0 && nr<N && nc>=0 && nc<N && !visit[nr][nc][d] && map[nr][nc] == 0) {
                    if (next.d == -1 || d == next.d) { // 시작 위치 or 이전과 같은 방향으로 진행할거다 (직진)
                        queue.add(new Block(nr, nc, d, next.p+100));
                    } else { // 꺾을거임 (코너+직진)
                        queue.add(new Block(nr, nc, d, next.p+600));
                    }
                }
            }
        }
        
    }
}
