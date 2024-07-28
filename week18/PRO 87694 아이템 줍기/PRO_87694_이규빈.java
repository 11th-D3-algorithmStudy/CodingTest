import java.util.*;

class Solution {
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    
    static class Point {
        int x, y, dist;
        
        Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    
    public int solution(int[][] rectangle, // 직사각형 좌표 목록
                        int characterX, int characterY, // 초기 캐릭터의 위치
                        int itemX, int itemY) // 아이템의 위치
    {
        // 맵 그리기
        // - 정확한 경계 처리를 위해 맵을 2배로 증가
        map = new int[101][101];
        visited = new boolean[101][101];
        for (int[] rect : rectangle) {
            int x1 = rect[0] * 2;
            int y1 = rect[1] * 2;
            int x2 = rect[2] * 2;
            int y2 = rect[3] * 2;
            
            // 테두리는 1로 설정
            // 내부는 2로 설정해서 방문하지 않도록 함
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    if (i == x1 || i == x2 || j == y1 || j == y2) {
                        if (map[i][j] != 2)  map[i][j] = 1;
                    } else {
                        map[i][j] = 2;
                    }
                }
            }
        }
        return bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);
    }
    
    static int bfs(int characterX, int characterY, int itemX, int itemY) {
        Queue<Point> q = new LinkedList<>();
        
        // 1. 시작점 넣고 방문처리
        q.offer(new Point(characterX, characterY, 0));
        visited[characterX][characterY] = true;
        
        while (!q.isEmpty()) {
            Point p = q.poll();
            
            // 2. 도착시 BFS 종료
            if (p.x == itemX && p.y == itemY)  return p.dist / 2;
            
            // 3. 사방탐색하며 테두리 따라 진행
            for (int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
               
                // 경계조건 위반 또는 이미 방문시 패스
                if (!inMap(nx, ny) || visited[nx][ny])  continue;
                
                if (map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    q.offer(new Point(nx, ny, p.dist + 1));
                }
            }
        }
        return -1;
    }
    
    static boolean inMap(int x, int y) {
        return 0 <= x && x <= 100 && 0 <= y && y <= 100;
    }
}
