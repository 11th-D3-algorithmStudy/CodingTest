import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int i = 0; i < works.length; i++) {
            pq.offer(works[i]);
        }
        
        while (n-- > 0) {
            int temp = pq.poll();
            if (temp > 0) temp--;
            pq.offer(temp);
        }
        
        long answer = 0;
        while (!pq.isEmpty()) {
            answer += Math.pow(pq.poll(), 2);
        }
        return answer;
    }
}
