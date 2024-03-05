
class PRO_바탕화면_정리_이서영 {
    public int[] solution(String[] wallpaper) {
    	int row = wallpaper.length;
    	int col = wallpaper[0].length();
    	char[][] arr = new char[row][col];
    	for (int i = 0; i < row; i++) {
    		arr[i] = wallpaper[i].toCharArray();
    	}
    	
    	int minRow = Integer.MAX_VALUE;
    	int maxRow = Integer.MIN_VALUE;
    	int minCol = Integer.MAX_VALUE;
    	int maxCol = Integer.MIN_VALUE;
    	for (int r = 0; r < row; r++) {
    		for (int c = 0; c < col; c++) {
    			if (arr[r][c] == '#') {
    				minCol = Math.min(minCol, c);
    				maxCol = Math.max(maxCol, c);
    				minRow = Math.min(minRow, r);
    				maxRow = Math.max(maxRow, r);
    			}
    		}
    	}
    	
    	
        int[] answer = {minRow, minCol, maxRow + 1, maxCol + 1};
        return answer;
    }
}