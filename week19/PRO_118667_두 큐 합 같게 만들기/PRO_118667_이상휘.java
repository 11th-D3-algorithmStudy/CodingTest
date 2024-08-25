class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;

        // 각각의 큐의 합 sum1, sum2
        long sum1 = 0;
        long sum2 = 0;
        for (int n : queue1) sum1 += n;
        for (int n : queue2) sum2 += n;

        // 찾는 값 : 전체 큐의 합의 절반
        long totalSum = sum1 + sum2;
        // 전체 큐의 합이 홀수라면, 반반 나눌수없음
        if (totalSum % 2 != 0) return -1;

        // 목표값
        long targetSum = totalSum / 2;

        // 두 큐를 하나의 배열로 연결
        int[] totalQueue = new int[queue1.length + queue2.length];

        for (int i = 0; i < queue1.length; i++) {
            totalQueue[i] = queue1[i];
        }

        for (int i = 0; i < queue2.length; i++) {
            totalQueue[i + queue1.length] = queue2[i];
        }

        int left = 0;
        int right = queue1.length;

        long currentSum = sum1;
        int operations = 0;

        // 각각 큐를 전체 순회시 정답 불가
        while (left < right && right < totalQueue.length) {
            if (currentSum == targetSum) return operations;
            if (currentSum > targetSum) {
                currentSum -= totalQueue[left++];
            } else {
                currentSum += totalQueue[right++];
            }
            operations++;
        }
        return -1;
    }
}
