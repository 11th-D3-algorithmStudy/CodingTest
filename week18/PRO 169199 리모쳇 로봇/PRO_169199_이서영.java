import java.util.*;

class Point {
    int x;
    int y;
    int count;
    
    public Point(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
}

class Solution {
    static Point start;
    static Point goal;
    static boolean reached = false;
    static int total = -1;
    
    public int solution(String[] board) {
        char[][] newBoard = new char[board.length][board[0].length()];
        
        for (int i = 0; i < board.length; i++) {
            newBoard[i] = board[i].toCharArray();
        }
        
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard[0].length; j++) {
                if (newBoard[i][j] == 'R') {
                    start = new Point(i, j, 0);
                } else if (newBoard[i][j] == 'G') {
                    goal = new Point(i, j, 0);
                }
            }
        }
        
        bfs(start, newBoard);
        return total;
    }
    
    static void bfs(Point start, char[][] newBoard) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[newBoard.length][newBoard[0].length];
        q.offer(start);
        visited[start.x][start.y] = true;
        
        while (!q.isEmpty()) {
            Point curr = q.poll();
            
            for (int d = 0; d < 4; d++) {
                Point next = move(curr, newBoard, d);
                
                if (next != null) { 
                    if (next.x == goal.x && next.y == goal.y) {
                        total = next.count;
                        return;
                    }
                    
                    if (!visited[next.x][next.y]) {
                        visited[next.x][next.y] = true;
                        q.offer(next);
                    }
                }
            }
        }
    }
    
    static Point move(Point curr, char[][] newBoard, int d) {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        int r = curr.x + dx[d];
        int c = curr.y + dy[d];
        boolean stop = false;
        
        while (r < newBoard.length && r >= 0 && c < newBoard[0].length && c >= 0) {
            if (newBoard[r][c] == 'D') {
                stop = true;
                break;
            }
            r += dx[d];
            c += dy[d];
        }
        
        int newX = r - dx[d];
        int newY = c - dy[d];
        
        if ((newX != curr.x || newY != curr.y)) { 
            return new Point(newX, newY, curr.count + 1);
        }
        
        return null;
    }
}
