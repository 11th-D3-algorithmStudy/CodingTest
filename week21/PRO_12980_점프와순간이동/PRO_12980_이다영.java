import java.util.*;

public class Solution {
    public int solution(int n) {
        int[] dp = new int[n+1];
        dp[1]=1;
        for(int i = 1; i<= n ; i++){
            if(i*2<=n){
                dp[i*2] = dp[i];
            }
            if(i+1<=n){
                if(dp[i+1]!=0 && dp[i+1]>dp[i]+1){
                    dp[i+1] = dp[i]+1;
                }
                if(dp[i+1]==0){
                    dp[i+1]=  dp[i]+1;
                }

            }
        }
        return dp[n];
    }
}
