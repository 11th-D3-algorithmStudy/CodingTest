

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

public class BOJ_1296_이윤주 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//이름, N 입력받기
		String yeonduName = br.readLine();
		int n = Integer.parseInt(br.readLine());
		String[] teamName = new String[n];
		
		//N개의 팀이름 입력받기
		for(int i = 0; i < n; i++) {
			teamName[i] = br.readLine();
		}
		
		//사전순으로 정렬
		Arrays.sort(teamName);
		
		//각 글자의 개수를 담는 배열 선언
		int[] lNum = new int[n];
		int[] oNum = new int[n];
		int[] vNum = new int[n];
		int[] eNum = new int[n];
		
		//연두 이름에서 해당 글자 수 세기
		int lyeon = 0;
		int oyeon = 0;
		int vyeon = 0;
		int eyeon = 0;
		
		for(int i = 0; i < yeonduName.length(); i++) {
			if(yeonduName.charAt(i) == 'L')
				lyeon++;
			else if(yeonduName.charAt(i) == 'O')
				oyeon++;
			else if(yeonduName.charAt(i) == 'V')
				vyeon++;
			else if(yeonduName.charAt(i) == 'E')
				eyeon++;
		}
		
		//연두 이름에 들어간 개수로 초기화
		Arrays.fill(lNum, lyeon);
		Arrays.fill(oNum, oyeon);
		Arrays.fill(vNum, vyeon);
		Arrays.fill(eNum, eyeon);
		
		//각 팀 이름에 들어간 글자의 개수 세기
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < teamName[i].length(); j++) {
				if(teamName[i].charAt(j) == 'L')
					lNum[i]++;
				else if(teamName[i].charAt(j) == 'O')
					oNum[i]++;
				else if(teamName[i].charAt(j) == 'V')
					vNum[i]++;
				else if(teamName[i].charAt(j) == 'E')
					eNum[i]++;
			}
		}
		
		//우승할 확률이 가장 높은 팀의 인덱스 저장할 변수
		int bestIdx = 0;
		int best = 0;
		
		//모든 이름의 확률을 구해서 가장 확률이 높은 것을 찾기
		for(int i = 0; i < n; i++) {
			int chance = 0;

			chance = ((lNum[i]+oNum[i])*(lNum[i]+vNum[i])*(lNum[i]+eNum[i])*(oNum[i]+vNum[i])*(oNum[i]+eNum[i])*(vNum[i]+eNum[i])) % 100;
			
			if(best < chance) {
				best = chance;
				bestIdx = i;
			}
		}
		//출력
		System.out.println(teamName[bestIdx]);
	}
}
