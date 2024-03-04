package 스터디;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_19637_정다운 {
	public static void main(String[] args) throws Exception {
		// 칭호 정보를 리스트에 저장해두고
		// (+ 저장할 때 중복되는 전투력 상한값이 여러개라면 먼저 입력된 것만)
		// 캐릭터들의 전투력 정보를 칭호 정보 리스트에서 이진탐색
		// 탐색 성공시 -> 해당 칭호
		// 탐색 실패시 -> 출력되는 음수로 어느 구간인지 파악하기

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 칭호 개수
		int M = Integer.parseInt(st.nextToken()); // 캐릭터 개수
		
		// 중복확인을 List에서 하면 이진탐색을 쓰더라도 시간초과... -> 중복확인용 map을 추가로 사용하자~
		// HashMap의 contains() 메소드는 O(1)
		// ArrayList의 contains() ( = indexOf(object)) 메소드는 O(n)
		// (모든 element를 돌며 비교작업 진행하기 때문에 느리다)
		// https://brocess.tistory.com/185 참고
		HashMap<Integer, String> titleMap = new HashMap<>();
		List<Integer> powerList = new ArrayList<>();
		
		for (int i=0; i<N; i++) {
			StringTokenizer titleTokens = new StringTokenizer(br.readLine());
			
			String title = titleTokens.nextToken();
			int power = Integer.parseInt(titleTokens.nextToken());
			
			if (!titleMap.containsKey(power)) {	// 칭호 중복확인	
				titleMap.put(power, title);
				powerList.add(power);
			}
		}
		
		Collections.sort(powerList);

		for (int i=0; i<M; i++) {
			int cPower = Integer.parseInt(br.readLine());
			// 캐릭터 전투력을 칭호 전투력 리스트에서 찾아보고
			// 전투력과 일치한다면 or 전투력보다 작은 위치에 있다면(들어갈 위치를 음수로 표현해줌)
			// 해당 칭호를 출력한다
			int charIdx = Collections.binarySearch(powerList, cPower);
			if (charIdx < 0) {
				charIdx = (-charIdx)-1; // = 캐릭터에 해당하는 칭호 idx
			}
			sb.append(titleMap.get(powerList.get(charIdx))+"\n");
		}
		System.out.println(sb);
	}
}
