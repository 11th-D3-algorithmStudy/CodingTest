import java.util.*;

public class Solution {
    public int solution(int n) {
        // top-down 방식으로 ................
        // 짝수이면 /2, 홀수이면 -1(k++)
        int k = 0;
        while(n != 0) {
            if (n % 2 == 0) {
                n = n/2;
            } else {
                n -= 1;
                k += 1;
            }   
        }
        
        return k;
    }
}
