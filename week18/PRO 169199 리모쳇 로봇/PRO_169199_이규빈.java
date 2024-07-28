import java.util.*;

class Solution {
    static char[][] map;
    static boolean[][] visited;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int startR, startC;
    
    static class Point {
        int r, c, cnt;
        
        Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
    
    public int solution(String[] board) {
        map = new char[board.length][board[0].length()];
        visited = new boolean[board.length][board[0].length()];
        
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length(); c++) {
                map[r][c] = board[r].charAt(c);
                
                if (map[r][c] == 'R') {
                    startR = r;
                    startC = c;
                }
            }
        }
        return dfs();
    }
    
    static int dfs() {
        Queue<Point> q = new LinkedList<>();
        
        // 1. 시작점 넣고 방문 처리
        q.offer(new Point(startR, startC, 0));
        visited[startR][startC] = true;
        
        while (!q.isEmpty()) {
            Point p = q.poll();
            
            // 2. 도착시 BFS 종료
            if(map[p.r][p.c] == 'G')  return p.cnt;
            
            for (int i = 0; i < 4; i++) {
                int nr = p.r;
                int nc = p.c;
                
                // nr, nc에 "해당 방향으로 끝까지 이동한 좌표"를 저장하기
                while (true) {
                    int rr = nr + dr[i];
                    int cc = nc + dc[i];
                    
                    if (!inMap(rr, cc) || map[rr][cc] == 'D')  break;
                    
                    nr = rr;
                    nc = cc;
                }
                
                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new Point(nr, nc, p.cnt + 1));
                }
            }
        }
        return -1;
    }
    
    static boolean inMap(int r, int c) {
        return 0 <= r && r < map.length && 0 <= c && c < map[0].length;
    }
}
