class Solution {
    static boolean isSafe;
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for (int n = 0; n < 5; n++){
            isSafe = true;
            char[][] seats = new char[5][5];
            for (int i = 0; i < 5; i++){
                String line = places[n][i];
                seats[i] = line.toCharArray();
            }
            for (int i = 0; i < 5; i++){
                for (int j = 0; j < 5; j++){
                    if (seats[i][j] == 'P'){
                       eval(i, j, seats, 0, -1);
                    }
                }
            }
            if (isSafe){
                answer[n] = 1;
            }else{
                answer[n] = 0;
            }
        }
        return answer;
    }
    
    public void eval(int i, int j, char[][] seats, int depth, int prev){
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        
        if (depth == 1){
            for (int d = 0; d < 4; d++){
                if (d != (prev + 2) % 4){
                    int r = i + dx[d];
                    int c = j + dy[d];
                    if (r >= 0 && r < 5 && c >= 0 && c < 5){
                        if (seats[r][c] == 'P'){
                            isSafe = false;
                            return;
                        }
                    }
                }
            }
        }else{
            for (int d = 0; d < 4; d++){
                int r = i + dx[d];
                int c = j + dy[d];
                if (r >= 0 && r < 5 && c >= 0 && c < 5){
                    if (seats[r][c] == 'P'){
                        isSafe = false;
                        return;
                    }else if (seats[r][c] == 'O'){
                        eval(r, c, seats, 1, d);
                    }
                }
            }
        }
    } 
}