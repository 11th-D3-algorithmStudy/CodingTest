import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        answer[prices.length - 1] = 0; //마지막 가격은 항상 0초

        for(int i = 0; i < prices.length - 1; i++){
            int price = prices[i];
            boolean flag = false;
            for(int j = i + 1; j <= prices.length - 1; j++){
                if(price > prices[j]){ //가격이 떨어지는 순간 시간 기록
                    flag = true;
                    answer[i] = j - i;
                    break;
                }
            }

            if(!flag){ //끝까지 가격이 떨어지지 않은 경우
                answer[i] = prices.length - 1 - i;
            }
        }
        return answer;
    }
}
