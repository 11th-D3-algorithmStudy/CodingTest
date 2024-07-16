import java.util.*;

class Solution {
    
	/* 풀이시간 : 240711 20:00 ~ 20:50
	 * 메인풀이법 
	 *    - 최단거리 => BFS 사용
	 *    - 입력값과 다르게 추가로 거리 계산할 수 있는 2차원 배열 추가 선언
	 *    - 방문 시에 1씩 더해준다.
	 *    
	 * 막힌 부분 : 프로그래머스 환경의 입력 시스템이 어떻게되는지 파악을 못했었음
	 * 채점 결과 :
	 *   정확성: 69.9
	 *   효율성: 30.1
	 *   합계: 100.0 / 100.0
	 */
	
    static int[][] temp;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        
        temp = new int[n][m];
        visited = new boolean[n][m];
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                temp[i][j] = maps[i][j];
            }
        }
        
        BFS(maps, 0, 0);
        
        int answer = temp[n-1][m-1];
        if(answer > 1) return answer;
        else return -1;
    }
    
    public static void BFS(int[][] maps, int r, int c){
        int n = maps.length;
        int m = maps[0].length;
        
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {r, c});
        visited[r][c] = true;
        
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            
            for(int d=0; d<4; d++){
                int nr = now[0] + dr[d];
                int nc = now[1] + dc[d];
                
                if(nr >= 0 && nr < n && nc >= 0 && nc < m){
                    if(!visited[nr][nc] && maps[nr][nc] == 1){
                        temp[nr][nc] = temp[now[0]][now[1]] + 1;
                        queue.add(new int[] {nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }
        }
    }
}
