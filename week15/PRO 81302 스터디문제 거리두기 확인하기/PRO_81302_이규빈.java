class Solution {
    static char[][] map;
    static boolean[][] visited;
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for (int tc = 0; tc < 5; tc++) {
            // 각 테스트케이스마다 map과 방문배열 새로 생성
            map = new char[5][5];
            visited = new boolean[5][5];
            
            // map 입력
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    map[r][c] = places[tc][r].charAt(c);
                }
            }
            
            // P를 만나면 맨해튼 거리 2만큼 탐색
            boolean isSafe = true;
            out:
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    if (map[r][c] == 'P') {
                        if(!dfs(r, c, 0)) {
                            isSafe = false;
                            break out;
                        }
                    }
                }
            }
            
            answer[tc] = isSafe ? 1 : 0;
        }
        
        return answer;
    }
    
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    static boolean dfs(int r, int c, int dist) {
        // base case
        if (dist != 0 && map[r][c] == 'P')  return false;
        if (dist == 2 || map[r][c] == 'X')  return true;
        
        visited[r][c] = true;
        
        // recursive case
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            
            if (!inMap(nr, nc) || visited[nr][nc])  continue;  // 경계조건 위반 또는 이미 방문시 패스
            if (!dfs(nr, nc, dist + 1))  return false;
        }
        
        visited[r][c] = false;  // 백트래킹 되어 돌아온 경우, 방문체크 해제
        
        return true;
    }
    
    
    static boolean inMap (int r, int c) {
        return 0 <= r && r < 5 && 0 <= c && c < 5;
    }
}
