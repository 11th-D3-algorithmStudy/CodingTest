class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++){
            if (!visited[i]){
                // System.out.println(i);
                answer++;
                dfs(i, computers, visited);
            }            
        }  
        return answer;
    }
    
    public void dfs (int idx, int[][] computers, boolean[] visited){
        if (visited[idx]){
            return;
        }
        // System.out.println(idx);
        visited[idx] = true;
        int[] connected = computers[idx];
        for (int i = 0; i < connected.length; i++){
            if (i != idx && connected[i] == 1){
                dfs(i, computers, visited);
            }

        }
    }
}
