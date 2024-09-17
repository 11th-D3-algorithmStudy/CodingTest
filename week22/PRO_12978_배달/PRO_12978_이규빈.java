import java.util.*;

class Solution {
    
    class Node implements Comparable<Node> {
        int v; // 인접 노드
        int d; // 두 노드의 거리
        
        Node(int v, int d) {
            this.v = v;
            this.d = d;
        }
        
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.d, other.d);
        }
    }
    
    public int solution(int N, int[][] road, int K) {

        // 1. 그래프 구현
        List<Node>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] r : road) {
            int a = r[0], b = r[1], c = r[2];
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        
        // 2. D배열, PQ 초기화
        int[] D = new int[N + 1];
        Arrays.fill(D, Integer.MAX_VALUE);
        D[1] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));
        
        
        // 3. PQ에서 뽑고, 이웃한 노드들 최단거리 갱신 (반복)
        while (!pq.isEmpty()) {
            Node cur = pq.poll(); // = d가 최소인 node
            
            if (cur.d > D[cur.v]) continue; // 방문체크 역할
            
            for (Node next : graph[cur.v]) {
                int oldDist = D[next.v];
                int newDist = cur.d + next.d;
                
                if (oldDist > newDist) {
                    D[next.v] = newDist;
                    pq.offer(new Node(next.v, newDist));
                }
            }
        }
        
        
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (D[i] <= K) answer++;
        }
        return answer;
    }
    
}
