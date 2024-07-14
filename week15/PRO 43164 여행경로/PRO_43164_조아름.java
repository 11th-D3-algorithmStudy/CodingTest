import java.util.*;
class Solution {
    static boolean[] visited;
    static ArrayList<String> answer = new ArrayList<>();
    
    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        
        dfs(0, "ICN", "ICN", tickets);
        Collections.sort(answer);
        
        return answer.get(0).split(" ");
        
    }
    
    static void dfs(int dept, String start, String route, String[][] tickets){
        if(dept == tickets.length){
            answer.add(route);
            return; 
        }
        
        for(int i=0; i<tickets.length; i++){
            if(!visited[i] && start.equals(tickets[i][0])){
                visited[i] = true;
                dfs(dept+1, tickets[i][1], route+" "+tickets[i][1], tickets);
                visited[i] = false;
            }
        }
    }
}