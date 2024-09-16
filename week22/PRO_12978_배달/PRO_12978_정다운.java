import java.util.*;

class Solution {
    
    static class Road implements Comparable<Road> {
        int v; // 마을 번호
        int t; // 시간
        
        Road(int v, int t) {
            this.v = v;
            this.t = t;
        }
        
        // 시간 순 정렬
        @Override
        public int compareTo(Road o) {
            return this.t - o.t;
        }
    }
    
    public int solution(int N, int[][] road, int K) {
        // 다익스트라.....
        List<Road>[] connInfo = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            connInfo[i] = new ArrayList<>();
        }
        
        // 도로 정보 저장
        for (int[] r : road) {
            connInfo[r[0]].add(new Road(r[1], r[2]));
            connInfo[r[1]].add(new Road(r[0], r[2]));
        }
        
        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.offer(new Road(1, 0)); // 1번 마을에서 시작, 초기 시간 0
        
        // 최단 시간 저장 배열
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0; // 시작점인 1번 마을은 시간 0
        
        while (!pq.isEmpty()) {
            Road next = pq.poll();
            int currV = next.v;
            int currT = next.t;
            
            // 지금 계산된 시간이 이미 계산된 시간보다 크면 패스
            if (currT > dist[currV]) {
                continue;
            }
            
            // 최단 시간 갱신
            for (Road r : connInfo[currV]) {
                int newTime = currT + r.t;
                
                if (newTime < dist[r.v]) {
                    dist[r.v] = newTime;
                    pq.offer(new Road(r.v, newTime));
                }
            }
        }
        
        // K시간 이내에 배달이 가능한 마을의 수 계산
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) {
                answer++;
            }
        }
        
        return answer;
    }
}
