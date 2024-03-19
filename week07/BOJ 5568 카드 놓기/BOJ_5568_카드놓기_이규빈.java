package week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_5568_카드놓기_이규빈 {
	/*
	* # 풀이시간 : 1시간
	* # 메모리 / 실행시간 : 16736kb / 236ms
	* # 문제 접근법
	*  - 방문체크를 이용해 주어진 정수들을 활용한 순열(nPk)을 만든다. (순서가 의미 있으므로 조합이 아님)
	*  - 만든 순열이 중복되지 않는 경우에 한해 List에 저장한 뒤, List의 크기를 계산한다.
	*/
	static int n, k;
	static String[] nums; // 입력받은 n개의 정수들
	static boolean[] visited; // nums 배열의 방문여부(= 원소 뽑았는지 여부) 저장
	static String[] tmp; // 뽑아낸 k개의 정수들을 임시 저장
	static List<String> list; // 순열로 새로 만든 정수들을 저장
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		
		nums = new String[n];
		visited = new boolean[n];
		tmp = new String[k]; // [주의] 얘만 원소 "k개"
		list = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			nums[i] = br.readLine();
		}
		
		// -------------- INPUT END -----------------
		
		perm(0);
		
		System.out.println(list.size());
	} // End of main
	
	
	// 순열 생성(nPk)
	private static void perm(int tmpIdx) {
		// base case
		// - k개 정수를 다 뽑은 경우, 순열 완성해 중복되지 않았다면 list에 넣고 리턴
		if (tmpIdx == k) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < k; i++) {
				sb.append(tmp[i]);
			}
			
			String newNum = sb.toString();
			if (!list.contains(newNum)) {
				list.add(newNum);
			}
			
			return;
		}
		
		// recursive case
		for (int numsIdx = 0; numsIdx < n; numsIdx++) {
			if (visited[numsIdx])  continue;  // 이미 해당 원소를 뽑았던 경우 넘어간다.
			
			// 여기 도달한 경우, 아직 뽑지 않은 원소
			tmp[tmpIdx] = nums[numsIdx];
			visited[numsIdx] = true;
			perm(tmpIdx + 1); // 재귀가 한 단계 깊어짐 = 순열의 다음 원소 뽑으러 감
			visited[numsIdx] = false; // Backtracking
		}
	} // End of perm
}
