import java.io.*;
import java.util.*;

class Solution {
    
    public long solution(int n, int[] works) {
        // 가장 남은 시간이 큰 일부터 깎기
        // 내림차순 우선순위 큐!
        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
        for (int i=0; i<works.length; i++) {
            pq.add(works[i]);
        }
        
        for (int i=0; i<n; i++) {
            if (pq.peek() > 0) {
                int input = pq.poll() - 1;
                pq.add(input);
            } else {
                return 0;
            }
        }
        
        long answer = 0;
        while (!pq.isEmpty()) {
            int w = pq.poll();
            answer += w*w;
        }

        return answer;
    }
}
