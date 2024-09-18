import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
       
        Arrays.sort(routes, (a, b) -> Integer.compare(a[0], b[0]));
        
        int now = routes[0][1];
        answer++;  

        for (int i = 1; i < routes.length; i++) {
            if (now < routes[i][0]) {
                answer++;
                now = routes[i][1];  
            } else {
                now = Math.min(now, routes[i][1]); 
            }
        }
        return answer;
    }
}
