package week02.구명보트;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;

        LinkedList<Integer> list = new LinkedList<>();
       
        // 정렬함
        Arrays.sort(people);
        // 내림차순 형태로 LinkedList에 담음
        for(int x : people) {
            list.addFirst(x);
        }
        // 맨앞과 맨뒤를 뽑아서, 제한보다 작으면 둘다뻄
        // 맨앞과 맨뒤를 뽑아서, 제한보다 크면 , 맨앞만 빼고, 맨뒤는 다시 넣어줌
        while (list.size() >= 2) {
            int first = list.removeFirst();
            int last = list.removeLast();

            if (first + last <= limit) {
                answer++;
            } else {
                list.add(last);
                answer++;
            }
        }
        if (!list.isEmpty()) {
            answer++;
        }
        return answer;
    }

}
