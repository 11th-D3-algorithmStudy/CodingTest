class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long ans = 0;
        
        int d = 0;
        int r = 0;
        for(int i=n-1; i>=0; i--){
            d -= deliveries[i];
            r -= pickups[i];
            
            while(d < 0 || r < 0){
                d += cap;
                r += cap;
                ans += (i+1)*2;
            }
        }
        
        return ans;
    }
}
