import java.util.*;
//이것도 도저히 모르겠어서 풀이 참고
//https://velog.io/@rari_1110/DFS-프로그래머스-여행경로-JAVA
class Solution {
    //가능한 경로의 모든 경우의 수를 저장하는 리스트
    static List<String> allRoutes;
    //방문체크
    static boolean[] visited;

    public String[] solution(String[][] tickets) {

        allRoutes = new ArrayList<>();
        visited = new boolean[tickets.length];

        dfs("ICN", "ICN", tickets, 0);

        Collections.sort(allRoutes);
        String[] answer = allRoutes.get(0).split(" ");
        return answer;
    }

    //ICN부터 시작하는 경로를 찾는 dfs
    public static void dfs(String start, String route, String[][] tickets, int cnt){
        //기저조건
        if(cnt == tickets.length){
            //모든 항공권 사용하면 route 저장하기
            allRoutes.add(route);
            return;
        }

        //현재 노드에서 할 것
        for(int i = 0; i < tickets.length; i++){
            if(!visited[i] && start.equals(tickets[i][0])){
                visited[i] = true;
                dfs(tickets[i][1], route + " " + tickets[i][1], tickets, cnt + 1);
                visited[i] = false;
            }
        }
    }
}