import java.util.*;

class PRO_118667_김민호 {
    static long count;
    public int solution(int[] queue1, int[] queue2) {
        int len = queue1.length + queue2.length;

        Queue<Long> q1 = new LinkedList<>();
        Queue<Long> q2 = new LinkedList<>();

        long q1sum = 0;
        for (int i = 0; i < queue1.length; i++) {
            q1sum += queue1[i];
            q1.offer((long)queue1[i]);
        }

        long q2sum = 0;
        for (int i = 0; i < queue2.length; i++) {
            q2sum += queue2[i];
            q2.offer((long)queue2[i]);
        }

        while (true) {
            if (q1sum == q2sum) {
                return (int)count;
            }

            if (q1sum > q2sum) {
                long q1element = q1.poll();
                q1sum -= q1element;

                q2sum += q1element;
                q2.offer(q1element);
                count++;
            }

            if (q1sum < q2sum) {
                long q2element = q2.poll();
                q2sum -= q2element;

                q1sum += q2element;
                q1.offer(q2element);
                count++;
            }
            if (count > len * 2) {
                return -1;
            }
        }
    }
}