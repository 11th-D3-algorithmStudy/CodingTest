import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        Map<String, Integer> cntMap = new HashMap<>(); //신고당한 횟수 저장하는 map

        // 한 유저가 같은 유저를 여러 번 신고한 경우는 1번으로 처리해야한다.
        // 중복제거
        Set<String> set = new HashSet<>(Arrays.asList(report));
        List<String> reportList = List.copyOf(set);

        for (String value : reportList) {
            String toName = value.split(" ")[1]; // 신고당한사람

            // 신고당한사람(key)에 대한 신고당한횟수(value) 저장
            if (cntMap.containsKey(toName)) {
                cntMap.put(toName, cntMap.get(toName) + 1);
            } else {
                cntMap.put(toName, 1);
            }
        }

        Map<String, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < id_list.length; i++) {
            // muzi 0, frodo 1, apeach 2, neo 3
            indexMap.put(id_list[i], i);
        }

        for (String s : reportList) {
            String fromName = s.split(" ")[0]; // 신고자
            String toName = s.split(" ")[1]; // 신고당한 사람

            if (cntMap.get(toName) >= k) { // 신고당한 사람의 횟수가 k 이상일 때
                // indexMap.get(신고자명)의 index에 메일 발송 횟수 +1
                answer[indexMap.get(fromName)] += 1;
            }
        }

        return answer;
    }
}
