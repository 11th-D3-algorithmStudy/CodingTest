class Solution {
    public int[] solution(int[] prices) {
        int totalLength = prices.length;
        int[] answer = new int[totalLength];

        for (int i = 0; i < totalLength; i++) {
            int time = 0;
            for (int j = i + 1; j < totalLength; j++) {
                time++;
                if (prices[i] > prices[j]) {
                    break;
                }
            }
            answer[i] = time;
        }

        return answer;
    }
}
