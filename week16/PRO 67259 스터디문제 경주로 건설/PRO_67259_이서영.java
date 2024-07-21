import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static int length;
    static boolean[][][] visited;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    public int solution(int[][] board) {
        length = board.length;
        visited = new boolean[length][length][4];
        return bfs(board);
    }

    private int bfs(int[][] board) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, -1, 0));
        int min = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.y == length - 1 && now.x == length - 1) {
                min = Math.min(min, now.cost);
                continue;
            }

            for (int dir = 0; dir < 4; dir++) {
                int moveY = now.y + dy[dir];
                int moveX = now.x + dx[dir];

                if (validation(board, moveY, moveX)) {
                    continue;
                }

                int nextCost = now.cost;

                if (dir == now.dir || now.dir == -1) {
                    nextCost += 100;
                } else {
                    nextCost += 600;
                }

                if (!visited[moveY][moveX][dir] || board[moveY][moveX] >= nextCost) {
                    q.offer(new Node(moveY, moveX, dir, nextCost));
                    visited[moveY][moveX][dir] = true;
                    board[moveY][moveX] = nextCost;
                }
            }
        }
        return min;
    }

    private static boolean validation(int[][] board, int moveY, int moveX) {
        return moveY < 0 || moveY >= length || moveX < 0 || moveX >= length || board[moveY][moveX] == 1;
    }
}

class Node {
    int y;
    int x;
    int dir;
    int cost;

    public Node(int y, int x, int dir, int cost) {
        this.y = y;
        this.x = x;
        this.dir = dir;
        this.cost = cost;
    }
}
