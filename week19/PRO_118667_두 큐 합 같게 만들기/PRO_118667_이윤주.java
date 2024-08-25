import java.util.*;

class Solution {    
    public int solution(int[] queue1, int[] queue2) {
        int len = queue1.length;
        int limit = 3 * len;
        //각 큐의 합, 두 큐에 담긴 모든 원소의 합 구하기
        long sum1 = 0;
        long sum2 = 0;
        long total = 0;
        
        Queue<Integer> q1 = new ArrayDeque<>();
        for (int i : queue1) {
            q1.offer(i);
            sum1 += i;
        }

        Queue<Integer> q2 = new ArrayDeque<>();
        for (int i : queue2) {
            q2.offer(i);
            sum2 += i;
        }
        
        total = sum1 + sum2;
        if(total % 2 == 1) return -1; //만약 두 큐의 합이 홀수면 같게 만들 수 없음
        
        int answer = 0;
        
        long half = total / 2;
        while(answer < limit){
            if(sum1 == half) break;
            
            else if(sum1 > half){
                int num = q1.poll();
                q2.offer(num);
                sum1 -= num;
                sum2 += num;
            }else {
                int num = q2.poll();
                q1.offer(num);
                sum2 -= num;
                sum1 += num;
            }
            answer++;
        }
        
        return answer >= limit ? -1 : answer;
    }
    
}
