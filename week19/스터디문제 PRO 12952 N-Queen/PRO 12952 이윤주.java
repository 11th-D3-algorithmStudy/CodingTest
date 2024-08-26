import java.util.*;

class Solution {
    static int answer;
    //퀸을 놓는 경우 아래로 3방향 이동 표시
    //좌하, 하, 우하
    static int[] dr = {1, 1, 1};
    static int[] dc = {-1, 0, 1};
    
    public int solution(int n) {
        answer = 0;
        boolean[][] board = new boolean[n][n]; //true : 퀸이 놓여있거나, 다른 퀸의 이동범위/ false: 놓을 수 있는 곳
        
        findQueen(0, n, board);
        
        return answer;
    }
    
    static void findQueen(int row, int n, boolean[][] board){
        //기저조건
        if(row == n){ //모든 퀸 배치한 경우
            answer++;
            return;
        }
        
        //현재 row에서 할 일
        for(int col = 0; col < n; col++){ //col
            if(!board[row][col]){ //놓을 수 있는 곳인 경우
                //깊은 복사
                boolean[][] newBoard = new boolean[n][n];
                for(int i = 0 ; i < n; i++){
                    newBoard[i] = Arrays.copyOf(board[i], n);
                }
                
                newBoard[row][col] = true;
                //3방향 탐색해서 표시하기
                for(int d = 0; d < 3; d++){
                    for(int x = 1; x < n - row; x++){ //마지막줄까지탐색
                        int nr = row + dr[d] * x;
                        int nc = col + dc[d] * x;
                        
                        //범위 벗어나는 경우
                        if(nr >= n || nr < 0 || nc >= n || nc < 0) continue;
                        //이미 놓을 수 없는 곳인 경우
                        if(newBoard[nr][nc]) continue;
                        
                        newBoard[nr][nc] = true; //퀸이 이동할 수 있는 자리 표시
                    }
                } //표시 완료
                findQueen(row+1, n, newBoard); //다음 줄로
            }
        }
    }
}
