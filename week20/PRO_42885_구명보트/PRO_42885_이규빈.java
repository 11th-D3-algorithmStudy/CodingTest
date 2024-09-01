import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        int lightest = 0; // 가장 가벼운 사람 인덱스
        int heaviest = people.length - 1; // 가장 무거운 사람 인덱스
        int answer = 0;

        while (lightest <= heaviest) {
            if (people[lightest] + people[heaviest] <= limit) {
                lightest++; // 가벼운 사람은 가능할때만 태우기
            }
            heaviest--; // 무거운 사람은 항상 태우기
            answer++;
        }

        return answer;
    }
}
