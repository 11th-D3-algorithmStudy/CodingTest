import java.util.*;

class PRO_42584 {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        for (int i = 0; i < prices.length - 1; i++) {
            int sum = 0;
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[i] <= prices[j]) {
                    sum++;
                } else {
                    sum++;
                    break;
                }
            }
            answer[i] = sum;
        }
        return answer;
    }
}
