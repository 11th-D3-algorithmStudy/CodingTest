import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);  // 몸무게 배열을 오름차순으로 정렬
        
        int i = 0;  // 가장 가벼운 사람을 가리키는 포인터
        int j = people.length - 1;  // 가장 무거운 사람을 가리키는 포인터
        int boats = 0;  // 필요한 보트의 개수
        
        while (i <= j) {
            // 가장 가벼운 사람과 가장 무거운 사람을 같이 태울 수 있는 경우
            if (people[i] + people[j] <= limit) {
                i++;
                j--;
            } else {
                // 가장 무거운 사람만 태우는 경우
                j--;
            }
            boats++;  // 보트 한 척 사용
        }
        
        return boats;  // 총 필요한 보트의 개수 반환
    }
}
