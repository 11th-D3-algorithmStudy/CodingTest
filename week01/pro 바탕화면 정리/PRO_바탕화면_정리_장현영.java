package week1;


import java.util.Arrays;

/*
 * 모든 파일을 포함시키려면
 * 열 기준으로 최소~최대, 행기준으로 최소 최대만큼 드래그 해준다.
 * 즉 #들만 봤을 때의 행,열 위치의 최소, 최대를 담아준 다음 값으로 출력 
 * S는 (행위치 최소, 열위치 최소), E(행위치 최대, 열위치 최대)가 정답이고
 * s,e 각각 위치를 int[] 배열에 담아서 답 내기 
 */

public class PRO_바탕화면_정리_장현영 {
	public static void main(String[] args) {
		
		String[] wp1 = {".#...", "..#..", "...#."};
		String[] wp2 = {"..........", ".....#....", "......##..", "...##.....", "....#....."};
		String[] wp3 = {".##...##.", "#..#.#..#", "#...#...#", ".#.....#.", "..#...#..", "...#.#...", "....#...."};
		String[] wp4 = {"..", "#."};
		
		
		String[][] wallpapers = {wp1,wp2,wp3,wp4}; // 2차원 배열로

		for(int t =0; t<4; t++) {
			System.out.println(Arrays.toString(solution(wallpapers[t])));
		}
//		solution()
		
	}
	
	
	    public static int[] solution(String[] wallpaper) {
	        
	    	int rLen = wallpaper.length; // 행 길이만큼 받아 놓기
	    	int cLen = wallpaper[0].length(); // 열 길이만큼 받아 놓기
	    	
	    	int sr = 99; 
	    	int sc = 99; 
	    	int er = -1; 
	    	int ec = -1; 
	    	
	    	char[][] arr = new char[rLen][cLen]; // char -> c 소문자
	    	
	    	// char형 리스트로 바꿔주기
	    	for(int i=0; i<wallpaper.length;i++) {
	    		arr[i] = wallpaper[i].toCharArray();
	    		System.out.println(Arrays.toString(arr[i]));
	    	}
	    	
	    	// 값 바뀔 때 주의
	    	// 격자점에서 S좌표는 # 파일의 좌상단을 가리키므로 그대로
	    	// 격자점에서 E좌표는 # 파일의 우하단을 가리키므로 파일 idx에 +1 씩 더한 값 
	    	// (#:2,3이 max --> 드래그 해서 3,4까지 받아야 함)
	    	// (#:0,0이 min --> 드래그 시작 지점은 0,0은 그대로) 
	    	
	    	
	    	for(int i=0;i<rLen;i++) {
	    		for(int j=0; j<cLen;j++) {
	    			if(arr[i][j] == '#') {
	    				sr = Math.min(i, sr);
	    				sc = Math.min(j, sc);
	    				er = Math.max(i+1, er); // 우하단으로 +1씩
	    				ec = Math.max(j+1, ec); // 우하단으로 +1씩
	    			}
	    		}
	    	}
	    	int[] answer = {sr,sc,er,ec};
	        
	        return answer;
	    }
	
}
