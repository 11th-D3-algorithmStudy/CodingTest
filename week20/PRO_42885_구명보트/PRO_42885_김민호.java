import java.util.*;

class PRO_42885_김민호 {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        int sum = 0;
        int left = 0;
        int right = people.length - 1;

        while(left <= right) {
            if (people[left] + people[right] <= limit) {
                left++;
            }
            right--;
            sum++;
        }
        return sum;
    }
}