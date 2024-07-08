import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        List<String> list = new LinkedList<>();
        
        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toUpperCase();  // 모두 대문자로 변경
            
            if (!list.contains(city)) {
                answer += 5;
                list.add(city);
                
                // 캐시가 가득찬 경우, 가장 오래된 요소를 제거
                if (list.size() > cacheSize) {
                    list.remove(0);
                }
            } else {
                answer += 1;
                list.remove(list.indexOf(city));
                list.add(city);
            }
        }
        
        return answer;
    }
}
