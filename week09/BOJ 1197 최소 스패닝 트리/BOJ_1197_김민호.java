import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1197_김민호 {
    // 다익스트라와는 다르게
    // 강의에서 배웠던 코드를 조금 참고해서
    // 코드를 작성함..
    // 유니온파인드를 완전하게 이해하지는 못해서
    // 추가 공부가 필요함..
    static int[] p;
    static class Edge implements Comparable<Edge>{
        int x, y, w;

        public Edge(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        p = new int[v + 1];

        for (int i = 0; i < v + 1; i++) {
            p[i] = i;
        }

        Edge[] edges = new Edge[e + 1];

        edges[0] = new Edge(0,0,-10000000);

        for (int i = 1; i < e + 1; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i] = new Edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(edges);

        int result = 0;
        int pick = 0;

        for (int i = 1; i < e + 1; i++) {
            int px = findSet(edges[i].x);
            int py = findSet(edges[i].y);

            if (px != py) {
                union(px, py);
                result += edges[i].w;
                pick++;
            }

            if (pick == v - 1) break;
        }

        System.out.println(result);
    }

    public static int findSet(int x) {
        if (p[x] != x) {
            return p[x] = findSet(p[x]);
        }
        return p[x];
    }

    public static void union(int x, int y) {
        p[y] = x;
    }
}