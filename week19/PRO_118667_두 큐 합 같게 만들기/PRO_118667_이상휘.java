// 정답은 나왔었지만, 테케 다시 생각하는 중에 오류를 발견해서 코드를 수정함 
// 위: 수정코드, 아래: 오류코드 
// 오류 테스트 : 주어진 테스트케이스 2번항목의 queue1 과 queue2의 입력순서를변경시 -1이 return 됨

// 수정한 코드 
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
        // 전체 큐의 합이 홀수라면, 반반 나눌 수 없음
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

        // 최대 탐색 횟수를 설정
        // 정확하게 최대 탐색횟수를 구하진 못했지만, *2 정도면 적당할 것이라 생각....
        int maxOperations = totalQueue.length * 2;

        while (operations <= maxOperations) {
            if (currentSum == targetSum) return operations;
            if (currentSum > targetSum) {
                currentSum -= totalQueue[left++];
                // 왼쪽 포인터가 끝에 도달하면 처음으로 돌아옴
                if (left == totalQueue.length) left = 0;  
            } else {
                currentSum += totalQueue[right++];
                // 오른쪽 포인터가 끝에 도달하면 처음으로 돌아옴
                if (right == totalQueue.length) right = 0;  
            }
            operations++;
        }

        return -1;
    }
}

// 아래는 오류를 발견한 기존 코드 
class Solution2 {
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
