import java.util.*;

class Solution {
    
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long tot1 = 0;
        long tot2 = 0;

        for (int i=0; i<queue1.length; i++) {
            tot1 += queue1[i];
            q1.add(queue1[i]);
            tot2 += queue2[i];
            q2.add(queue2[i]);
        }
        
        long target = (tot1 + tot2) / 2;
        
        if ((tot1 + tot2) % 2 != 0) return -1;

        int cnt = 0;
        int limit = queue1.length * 4; // 각 큐의 최대 이동 횟수 : * 2, 양방향 : * 2 

        while (cnt <= limit) {
            if (tot1 == target) return cnt;
            if (tot1 > target) {
                int poll = q1.poll();
                tot1 -= poll;
                q2.add(poll);
                tot2 += poll;
            } else {
                int poll = q2.poll();
                tot2 -= poll;
                q1.add(poll);
                tot1 += poll;
            }
            cnt++;
        }
        return -1;
    }
}
