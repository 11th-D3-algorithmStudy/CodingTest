import java.util.*;

class Solution {
    
    static class Node {
        int r, c, d; // 가로 세로 거리
        
        public Node(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
    
    static int n, m; // 가로 세로
    static boolean[][] visit;
    static Queue<Node> queue;
    static int min = 987654321; // 결과
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public int solution(int[][] maps) {
        // 0 벽 1 길
        // bfs
        n = maps.length;
        m = maps[0].length;
        visit = new boolean[n][m];
        queue = new LinkedList();
        boolean flag = false;
        
        queue.add(new Node(0, 0, 1));
        visit[0][0] = true;
        while(!queue.isEmpty()) {
            Node next = queue.poll();
            
            if (next.r == n-1 && next.c == m-1) {
                min = Math.min(min, next.d);
                flag = true;
                break;
            }
            
            for (int d=0; d<4; d++) {
                int nr = next.r+dr[d];
                int nc = next.c+dc[d];
                
                if (nr>=0 && nr<n && nc>=0 && nc<m 
                    && !visit[nr][nc] && maps[nr][nc] == 1) {
                    visit[nr][nc] = true;
                    queue.add(new Node(nr, nc, next.d+1));
                }
            }
        }
        
        int answer;
        
        if (flag) {
            answer = min;
        } else {
            answer = -1;
        }
     
        return answer;
    }
}
