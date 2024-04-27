import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
    int row;
    int col;
    int cost;

    public Node(int row, int col, int cost) {
        this.row = row;
        this.col = col;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node other) {
        return this.cost - other.cost;
    }
}


public class BOJ_4485_이상휘 {
    static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 상하좌우 이동
    static int N;
    static int ans;
    static int pNum = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            N = Integer.parseInt(br.readLine());
            pNum++;
            if (N == 0) {
                break;
            }
            int[][] cost = new int[N][N];
            int[][] map = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    cost[i][j] = Integer.MAX_VALUE; // 최대값으로 초기화
                }
            }
            ans = 0;
            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(0, 0, map[0][0]));
            cost[0][0] = map[0][0];
            while (!pq.isEmpty()) {
                Node curr = pq.poll();
                if (curr.row == N - 1 && curr.col == N - 1) {
                    ans = curr.cost;
                    break;
                }
                for (int[] direction : directions) {
                    int nr = curr.row + direction[0];
                    int nc = curr.col + direction[1];

                    if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                        int nextCost = curr.cost + map[nr][nc];
                        if (nextCost < cost[nr][nc]) {
                            cost[nr][nc] = nextCost;
                            pq.offer(new Node(nr, nc, nextCost));
                        }
                    }
                }
                ans = cost[N-1][N-1];
            }
            System.out.println("Problem " +pNum+": " + ans);

        }
    }
}
