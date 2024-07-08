import java.util.*;

class Solution {
    static Map<String, PriorityQueue<String>> map;
    static List<String> answer;
    
    
    public String[] solution(String[][] tickets) {
        // 1. 각 출발지에 대해, 도착지들을 알파벳 순으로 저장
        map = new HashMap<>();
        
        for (String[] t: tickets) {
            String from = t[0];
            String to = t[1];
            
            if(map.containsKey(from)) {
                PriorityQueue<String> pq = map.get(from);
                pq.add(to);
            } else {
                PriorityQueue<String> pq = new PriorityQueue<>();
                pq.add(to);
                map.put(from, pq);
            }
        }
        
        
        // 2. DFS를 활용해 경로 찾기
        answer = new ArrayList<>();
        dfs("ICN");
        
        
        // 3. 결과를 배열로 바꿔 반환
        return answer.toArray(new String[0]);
    }
    
    
    static void dfs(String cur) {
        while(map.containsKey(cur) && !map.get(cur).isEmpty()) {
            String next = map.get(cur).poll();
            dfs(next);
        }
        
        answer.add(0, cur);
    }
}