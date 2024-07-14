class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for(int i=0; i<5; i++){
            if(check(places[i])) answer[i] = 1;
            else answer[i] = 0;
        }
        
        return answer;
    }
    
    public boolean check(String[] place){
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(place[i].charAt(j)=='P'){
                    if(!isValid(place, i, j)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public boolean isValid(String[] place, int r, int c){
        // 상 하 좌 우 체크
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        for(int d=0; d<4; d++){
            int nr = r + dr[d];
            int nc = c + dc[d];
            
            if(nr >= 0 && nr < 5 && nc >= 0 && nc < 5){
                if(place[nr].charAt(nc)=='P'){
                    return false;
                }
                if(place[nr].charAt(nc)=='O'){
                    for(int j=0; j<4; j++){
                        int nnr = nr + dr[j];
                        int nnc = nc + dc[j];
                        
                        if (nnr == r && nnc == c) continue;
                        if(nnr >= 0 && nnr < 5 && nnc >= 0 && nnc < 5){
                            if(place[nnr].charAt(nnc)=='P'){
                                return false;
                            }
                        }
                    }
                }
            }
        }
        
        // 대각선도 체크
        int[] ddr = {-1, -1, 1, 1};
        int[] ddc = {-1, 1, 1, -1};
        
        for(int d=0; d<4; d++){
            int nr = r + ddr[d];
            int nc = c + ddc[d];
            
            if(nr >= 0 && nr < 5 && nc >= 0 && nc < 5){
                if(place[nr].charAt(nc) == 'P'){
                    // 대각선으로 P가 있을 때 그 사이에 X(파티션)의 존재여부 판단
                    if (place[r].charAt(nc) != 'X' || place[nr].charAt(c) != 'X') {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
}