class Solution {
    static int count;
    static boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        count = 0;
        visited = new boolean[computers.length];
        
        int answer = 0;
        for(int i = 0; i < computers.length; i++){
            if(!visited[i]){ //아직 확인하지 않은 컴퓨터
                findNetwork(computers, n, i);  //이 컴퓨터에 연결된 모든 컴퓨터 표시하기
                answer++; //이 컴퓨터부터 시작하는 네트워크 하나 추가
            }
        }
        
        return answer;
    }
    
    static void findNetwork(int[][] computers, int n, int idx){
        //현재 컴퓨터 표시
        visited[idx] = true;
        
        //현재 컴퓨터에 연결된 모든 컴퓨터 표시
        for(int i = 0; i < n; i++){
            if(!visited[i] && computers[idx][i] == 1){ //연결된 컴퓨터 중 안 가본 컴퓨터로 타고들어가기
                findNetwork(computers, n, i);
            }
        }
    }
}
