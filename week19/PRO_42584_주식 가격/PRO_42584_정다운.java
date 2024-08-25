import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        // stack 이용
        int len = prices.length;
        int[] answer = new int[len];
        Stack<Integer> stack = new Stack<>();
        
        for (int i=0; i<len; i++) {
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                answer[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            
            stack.push(i);
        }
        
        while (!stack.isEmpty()) {
            answer[stack.peek()] = len - stack.peek() - 1;
            stack.pop();
        }
        
//         // 2중 for문 이용
//         int len = prices.length;
//         int[] answer = new int[len];
        
//         for (int i=0; i<len; i++) {
//             int cnt = 0;
//             int n = prices[i];
            
//             if (i == len-1) {
//                 answer[i] = 0;
//                 break;
//             }
            
//             for (int j=i+1; j<len; j++) {
//                 cnt++;
//                 if (prices[j] < n || j == len-1) {
//                     answer[i] = cnt;
//                     break;
//                 }
//             }
//         }
        
        return answer;
    }
}
