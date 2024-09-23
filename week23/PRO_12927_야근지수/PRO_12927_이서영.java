import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
       PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int sum = 0;
        for (int i : works){
            pq.offer(i);
            sum += i;
        }
        if (sum <= n){
            return 0;
        }
        for (int i = 0; i < n; i++){
            int num = pq.poll();
            pq.offer(--num);
        }
        long answer = 0;
        while (!pq.isEmpty()){
            int num = pq.poll();
            answer += num * num;
        }
        return answer;
    }
}
