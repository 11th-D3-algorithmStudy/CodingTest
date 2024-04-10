package week09;

import java.io.*;
import java.util.*;


public class BOJ_1753_장현영 {

    // 최단 경로
    // 다익스트라 : 가중치가 있는 경로에서 최단 경로를 구하기
    // 방향그래프
    static List<int[]>[] arrList;
    static int[] dist;
    static int V, E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());
        arrList = new ArrayList[V + 1];
        for (int i = 1; i < V + 1; i++) {
            arrList[i] = new ArrayList<>();
        }
        dist = new int[V + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            arrList[a].add(new int[] { w, b }); // 목적지, 가중치 1차원 배열 담기
        }
        dijkstra(start);

    }

    public static void dijkstra(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((arr1,arr2) -> Integer.compare(arr1[0], arr2[0]));
        dist[start] = 0; // 시작노드와의 거리는 0

        // start와 연결된 노드들 pq 다 넣기
        for (int[] next : arrList[start]) {
            pq.add(next);
        }
        
        // 어차피 최소값만 구해서 push,pop이 node길이만큼만 진행되므로 visited 진행 x
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int weight = curr[0];
            int target = curr[1];
            // 우선순위로 뽑힌 curr은 dist 최신화 진행
            if (dist[target] > weight) { // 현재 가중치로 갱신이 필요한 경우
                dist[target] = weight; // 갱신 진행 후 target과 연결된 모든 node들 heap에 넣기
                for (int[] another : arrList[target]) {
                    pq.add(new int[] { another[0] + weight, another[1] });
                }
            }
        }

        for (int idx = 1; idx <= V; idx++) {
            System.out.println(dist[idx] != Integer.MAX_VALUE ? dist[idx] : "INF");
        }
    }
}
