import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1753_김민호 {
    // 다익스트라 문제..
    // 강의 끝나고 코드 안 보고 코드 작성해서 제출했는데
    // 추후에 다시 공부가 필요함..
    // 인접행렬, 인접리스트 전부 다 구현해봐야겠다..

    static class Node implements Comparable<Node> {
        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }

    static final int INF = 987654321;
    static int V, E;
    static List<Node>[] adjList;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[V + 1];

        for (int i = 0; i < V + 1; i++) {
            adjList[i] = new ArrayList<>();
        }

        dist = new int[V + 1];
        Arrays.fill(dist, INF);

        int start = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine().trim());
            adjList[Integer.parseInt(st.nextToken())].add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        dijkstra(start);

        for (int i = 1; i < dist.length; i++) {
            if (dist[i] == INF) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }

    }

    private static void dijkstra(int start) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[V + 1];

        dist[start] = 0;

        pq.add(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node curr = pq.poll();

            if (visited[curr.v]) continue;

            visited[curr.v] = true;

            for (Node node : adjList[curr.v]) {
                if (!visited[node.v] && dist[node.v] > dist[curr.v] + node.w) {
                    dist[node.v]  = dist[curr.v] + node.w;
                    pq.add(new Node(node.v, dist[node.v]));
                }
            }
        }
    }
}