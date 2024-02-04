

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_27522_이윤주 {	
	
	//레코드 클래스 - 정렬 가능하게 Comparable 인터페이스 구현
	public static class Record implements Comparable<Record>{
		private int time;
		private String team;
		
		//생성자
		public Record(int time, String team) {
			super();
			this.time = time;
			this.team = team;
		}

		//오름차순으로 정렬할 수 있게 compareTo 오버라이딩
		@Override
		public int compareTo(Record o) {
			return this.time - o.time;
		}
	} //레코드 클래스 끝

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//순위별 점수 배열로 저장
		int[] score = {10, 8, 6, 5, 4, 3, 2, 1, 0};
		int redScore = 0;
		int blueScore = 0;
		List<Record> list = new ArrayList<Record>();
		
		//8줄 입력 받음
		for(int i = 0; i < 8; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			String timeTmp = st.nextToken();
			int time = Integer.parseInt(timeTmp.replaceAll(":", ""));
			String team = st.nextToken();
			
			//리스트에 기록 추가
			list.add(new Record(time, team));
		}
		
		//list를 시간 오름차순으로 정렬 -> 시간이 제일 짧은게 1등
		Collections.sort(list);
		
		//순위 & 팀에 따른 점수 합산
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).team.equals("R")) {
				redScore += score[i];
			} else {
				blueScore += score[i];
			}
		}
		
		if(redScore > blueScore) 
			System.out.println("Red");
		else
			System.out.println("Blue");
	
	} //메인 메서드 끝
} //메인 클래스 끝
