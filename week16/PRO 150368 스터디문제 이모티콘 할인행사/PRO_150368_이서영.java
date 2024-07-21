import java.util.*;

class Solution {
    static int[] percentages = {10, 20, 30, 40};
    static int purchases;
    static int revenue;
    public int[] solution(int[][] users, int[] emoticons) {
        
        purchases = 0;
        revenue = 0;
        
        dfs(emoticons, users, 0, new int[emoticons.length]);
        int[] answer = {purchases, revenue};
        return answer;
    }
    
    public void dfs(int[] emoticons, int[][] users, int depth, int[] res) {
        if (depth == emoticons.length){
            // System.out.println(Arrays.toString(res));
            int sub = 0;
            int total = 0;
            for (int j = 0; j < users.length; j++){
                int subtotal = 0;
                int[] user = users[j];
                for (int x = 0; x < emoticons.length; x++){
                    if (res[x] >= user[0]){
                        subtotal += emoticons[x] * (100 - res[x]) / 100;
                    }
                }
                // System.out.println(subtotal);
                if (subtotal >= user[1]){
                    sub++;
                }else{
                    total += subtotal;
                }
            }
            // System.out.println("sub" + sub);
            if (sub > purchases){
                purchases = sub;
                revenue = total;
            }else if (sub == purchases){
                revenue = Math.max(revenue, total);
            }
            return;
        }
        
        for (int i = 0; i < 4; i++){
            res[depth] = percentages[i];
            dfs(emoticons, users, depth + 1, res);
        }
    }
}
