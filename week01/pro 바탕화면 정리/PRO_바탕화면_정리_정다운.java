import java.util.ArrayList;
import java.util.List;

public class PRO_바탕화면_정리_정다운 {
	public static void main(String[] args) {
		// 프로그래머스_바탕화면 정리

		// 최소 드래그 길이 : 
		// 파일이 존재하는 영역 중 가장 작은 행, 열 부터
		// 파일이 존재하는 영역 중 가장 큰 행+1, 열+1 까지
	}
	
	public int[] solution(String[] wallpaper) {
		// 결과 배열, 길이 4로 고정 [시작 행, 시작 열, 마지막 행, 마지막 열]
        int[] answer = new int[4];
        
        // 바탕화면 정보 2차원배열로 변환
        char[][] wpArr = new char[wallpaper.length][wallpaper[0].length()]; 
        
        for (int i=0; i<wallpaper.length; i++) {
        	wpArr[i] = wallpaper[i].toCharArray();
        }
        
        // 파일들의 행 위치 모음 리스트 선언
        List<Integer> rList = new ArrayList<>();
        // 파일들의 열 위치 모음 리스트 선언
        List<Integer> cList = new ArrayList<>();
        
        // 파일들의 행, 열 위치 저장
        for (int i=0; i<wallpaper.length; i++) {
        	for (int j=0; j<wallpaper[0].length(); j++) {
        		if (wpArr[i][j] == '#') {
        			rList.add(i);
        			cList.add(j);
        		}
        	}
        }
        
        // 파일이 존재하는 행, 열의 최소&최대값 구하기
        int rMin = Integer.MAX_VALUE;
        int cMin = Integer.MAX_VALUE;
        int rMax = Integer.MIN_VALUE;
        int cMax = Integer.MIN_VALUE;
        
        for (int i=0; i<rList.size(); i++) {
        	if (rList.get(i) < rMin) {
        		rMin = rList.get(i);
        	}
        	if (rList.get(i) > rMax) {
        		rMax = rList.get(i);
        	}
        	
        	if (cList.get(i) < cMin) {
        		cMin = cList.get(i);
        	}
        	if (cList.get(i) > cMax) {
        		cMax = cList.get(i);
        	}
        }
        
        // 결과 배열에 입력
        answer[0] = rMin;
        answer[1] = cMin;
        answer[2] = rMax+1;
        answer[3] = cMax+1;
        
        return answer;
    }
}

