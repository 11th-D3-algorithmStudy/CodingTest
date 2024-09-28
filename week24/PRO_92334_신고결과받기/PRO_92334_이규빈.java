import java.util.*;

class Solution {
    /* 
     * 사용한 메서드 정리
     * - Map: put, get
     * - 배열: split, Arrays.asList(___).indexOf (= 리스트로 변환해 인덱스 구하기)
     * - 리스트: contains
     */
    public int[] solution(String[] id_list, String[] report, int k) {
    
        Map<String, List<String>> map = new HashMap<>();
        int[] count = new int[id_list.length];
        
        // key 저장 (= 신고한 사람)
        for (String id : id_list) {
            map.put(id, new ArrayList<>());
        }
        
        // value 저장 (= 신고 당한 사람들)
        for (String r : report) {
            String[] str = r.split(" ");
            
            List<String> list = map.get(str[0]);
            
            if (!list.contains(str[1])) { // 동일 유저에 대한 신고 횟수는 1회로 처리하므로, 중복 제거
                list.add(str[1]);
                
                // 신고 당한 횟수 카운트
                int idx = Arrays.asList(id_list).indexOf(str[1]); // 배열에는 없는 메서드라 리스트로 변환
                count[idx]++;
            }
        }
        
        // 정지 메일 보내기
        // 시간복잡도: 최대 1000 * 1000 = 100만이므로, 가능하다고 판단
        int[] answer = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            if (count[i] >= k) {
                String suspendedUser = id_list[i]; // = 신고 당한 사람
                
                for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                    String key = entry.getKey();
                    List<String> value = entry.getValue();
                    
                    if (value.contains(suspendedUser)) {
                        int idx = Arrays.asList(id_list).indexOf(key);
                        answer[idx]++;
                    }
                }
            }
        }
        return answer;
    }
}
