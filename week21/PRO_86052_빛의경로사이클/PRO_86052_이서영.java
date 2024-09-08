import java.util.*;

class Solution {
    static int N;
    static int M;
    static int[] dx = {0, 1, 0, -1}; // 우하좌상
    static int[] dy = {1, 0, -1, 0};
    public int[] solution(String[] grid) {
        N = grid.length;
        M = grid[0].length();
        boolean[][][] visited = new boolean[N][M][4];
        char[][] map = new char[N][M];
        List<Integer> cycles = new ArrayList<>();
        for (int i = 0; i < N; i++){
            map[i] = grid[i].toCharArray();
        }
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                for (int d = 0; d < 4; d++){
                    if (!visited[i][j][d]){
                        int newCycle = move(i, j, d, map, visited);    
                        cycles.add(newCycle);
                    }
                }
            }
        }
        int[] answer = new int[cycles.size()];
        for (int i = 0 ; i < cycles.size(); i++){
            answer[i] = cycles.get(i);
        }
        Arrays.sort(answer);
        return answer;
    }
    
    public static int move(int x, int y, int dir, char[][] map, boolean[][][] visited){
        int route = 0;
        while (!visited[x][y][dir]){
            visited[x][y][dir] = true;
            if (map[x][y] == 'L'){
                dir = dir == 3 ? 0 : dir + 1;
            }else if (map[x][y] == 'R'){
                dir = dir == 0 ? 3 : dir - 1;
            }
            x = x + dx[dir];
            y = y + dy[dir];
            if (x >= N){
                x = 0;
            }
            if (x < 0){
                x = N - 1;
            }
            if (y >= M){
                y = 0;
            }
            if (y < 0){
                y = M - 1;
            }
            route++;
        }
        return route;
        
    }
}
