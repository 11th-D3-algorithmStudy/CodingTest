import java.util.*;

class Player {
    int time;
    String team;
    
    Player(int time, String team) {
    	this.time = time;
    	this.team = team;
    }
}

public class BOJ_27522_이규빈 {
	public static void main(String args[]){
		
		Scanner sc = new Scanner(System.in);
		
		// Player 객체배열 생성
		Player[] arr = new Player[8];
		
		// 완주기록을 배열에 저장
		for (int i = 0; i < 8; i++) {
			String oldTime = sc.next();  // 공백 전까지만 읽어야 하므로, nextLine()이 아닌 next() 사용
			String newTime = oldTime.replace(":", "");  // ":" 제거
			int time = Integer.parseInt(newTime);
			
			String team = sc.nextLine();
			
			arr[i] = new Player(time, team);
		}
		
		// 시간을 기준으로 오름차순으로 정렬
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7 - i; j++) {
				if (arr[j].time > arr[j + 1].time) {
					Player temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		
	    // 등수에 따른 점수 부여
		int[] rankScore = {10, 8, 6, 5, 4, 3, 2, 1};  // 순위에 따른 점수
		
		int redScore = 0;
		int blueScore = 0;
			
	    for (int i = 0; i < 8; i++) {
	    	if (arr[i].team == "R") {
	    		redScore += rankScore[i];
	    	} else {
	    		blueScore += rankScore[i];
	    	}
	    }
	    
	    // 결과 출력
	    if (redScore > blueScore) {
	    	System.out.println("Red");
	    } else if (redScore < blueScore){
	    	System.out.println("Blue");
	    } else {
	    	System.out.println("무승부");
	    }
		
	}
}