import java.util.*;

public class PRO_12980_김민호 {
    public int solution(int n) {
        int count = 0;
        while (n > 0) {
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n -= 1;
                count++;
            }
        }
        return count;
    }
}