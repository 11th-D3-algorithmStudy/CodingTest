package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

// map에서 key만 정렬했는데 어떻게 value도 그에 따라서 순서가 바뀌지?
// map에서 k,v는 한 쌍으로 k는 인덱스 개념
// int[] arr = {10,20,30,40}
// arr[1] --> 20, arr[3] --> 40 이 변하지 않 듯
// key는 고유 인덱스로 사용되어 그대로 해당 value에 접근함.
// 
public class BOJ_27522_장현영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Map<String, Character> map = new HashMap<>();

		// map에 데이터 받기
		for (int i = 0; i < 8; i++) {
			st = new StringTokenizer(br.readLine());
			map.put(st.nextToken(), st.nextToken().charAt(0));
		}

		List<String> keyList = new ArrayList<>(map.keySet());

		Collections.sort(keyList);

		int[] scores = { 10, 8, 6, 5, 4, 3, 2, 1 }; // 계산할 점수
		int red = 0;
		int blue = 0;
		int idx = 0;
		
		for (String key : keyList) {
			if ('B' == map.get(key)) {
				blue += scores[idx];
			} else {
				red += scores[idx];
			}
			idx++;
		}
		// 최종출력
		if (red > blue) {
			System.out.println("Red");
		} else {
			System.out.println("Blue");
		}
		

	}
}

