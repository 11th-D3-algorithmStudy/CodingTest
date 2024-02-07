class Solution {
	/*
	 * 바탕화면 정리 문제
	 * - #을 모두 포함하도록 왼쪽 위 S점부터, 오른쪽 아래 E점까지 드래그한다.
	 * - S점 : 드래그 시작 칸이 0행 1열이라면, [0,1]을 출력하면 된다.
	 * - E점 : 드래그 종료 칸이 2행 3열이라면, [3,4]를 출력해야 하는 점을 주의해야 한다.
	 */
    public int[] solution(String[] wallpaper) {
    	
    	// S,E점의 r, c 좌표 초기화
    	// - SR : 가장 위에 있는 파일의 r좌표
    	// - SC : 가장 왼쪽에 있는 파일의 c좌표
    	// - ER : 가장 밑에 있는 파일 r좌표 + 1
    	// - EC : 가장 오른쪽에 있는 파일의 c좌표 + 1
    	int SR = 100;
    	int SC = 100;
    	int ER = 0;
    	int EC = 0;
    	
    	// 배열을 순회하며 S,E점 찾기
    	for (int r = 0; r < wallpaper.length; r++) {
    		for (int c = 0; c < wallpaper[0].length(); c++) {
    			if (wallpaper[r].charAt(c) == '#') {
    				if (SR > r) SR = r;
    				if (SC > c) SC = c;
    				if (ER < r + 1) ER = r + 1;  // E점의 좌표는 파일의 좌표보다 +1임에 주의한다.
    				if (EC < c + 1) EC = c + 1;
    			}
    		}
    	}
    	
    	// 결과 출력
        int[] answer = {SR, SC, ER, EC};
        return answer;
    }
}