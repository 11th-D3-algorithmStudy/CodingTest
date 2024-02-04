package week1;

// import java.io.*
// import java.util.*  주로 사용?

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 팀 정하기
// 정직하게 love 개수 구해서 식 세워서 값 구하고
// 정렬말고 사전순 비교로 확인하기

public class BOJ_1296_장현영 {
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 첫 번째 본인 이름
		String name = br.readLine();
		int n = Integer.parseInt(br.readLine());
		String[] arr = new String[n]; // 본인 이름까지 포함 팀 개수(총 n+1)만큼 1차원 문자원 배열 생성
		String[] compareStr = new String[n]; // 값이 여러개 일 경우에만 사전순 compare함수쓰는 string 배열
		
		// 개수
		int l=0; int o=0; int v=0; int e=0;
		
		// 이름의 love 추가
		for(int i=0; i<name.length();i++) {
			if(name.charAt(i) == 'L')
				l++;
			else if(name.charAt(i) == 'O')
				o++;
			else if(name.charAt(i) == 'V')
				v++;
			else if(name.charAt(i) == 'E')
				e++;
		}
		
		// 인덱스를 뽑아내면서 구하고 싶어요
		int maxRes = -1;
		int ansIdx = -1;
		int cnt = 0; // 최댓값 개수
		
		for(int i=0; i<n; i++) {
			arr[i] = br.readLine();
			int result = calc(arr[i],l,o,v,e);
			//System.out.println(arr[i] +" " + result);
			if(result > maxRes) {
				maxRes = result;
				ansIdx = i;
				cnt = 0; // cnt가 0을 유지하면 값은 1개
			}
			// else if 하면 처음 담긴 값이 compare 배열에 안들어감
			if(result == maxRes) {
				//System.out.println(arr[i] +" compare에 담김");
				compareStr[cnt++] = arr[i];	// 같은 값이 나온다면 cnt가 늘어나면서 문자열 배열에 값이 담긴다.
			}
			}
		//System.out.println(Arrays.toString(arr));
		
		if(cnt ==0) {
			System.out.println(arr[ansIdx]);
		}
		else { // 사전순 값비교 필요
			//System.out.println(Arrays.toString(compareStr));
			String answer = compareStr[0]; // 처음 값
			for(int i=1; i<cnt;i++) {
				if(answer.compareTo(compareStr[i]) > 0) { // 아스키코드 비교(작은값이 사전순으로 더 우선) 
					// 뒤에 있는 값이 더 사전순으로 우선이라는 소리(정답 바꿔주기)
					// 'aab' compareto 'aaa' -> 'b'-'a' 양수!
					answer = compareStr[i]; // 
				}
			}
			System.out.println(answer);
		}
		
		// compareto 함수 주의점
		// 1. 정수간 compareto -> 1,0,-1 
		// 1. 'aaa' compareto 'aab' -> -1 (a와 b 차이만큼) 
		
		
		
	}
	
	public static int calc(String str, int l, int o, int v, int e) {

		for(int i=0; i<str.length();i++) {
			if(str.charAt(i) == 'L')
				l++;
			else if(str.charAt(i) == 'O')
				o++;
			else if(str.charAt(i) == 'V')
				v++;
			else if(str.charAt(i) == 'E')
				e++;
		}
		return (l+o)*(l+v)*(l+e)*(o+v)*(o+e)*(v+e) % 100;
	}

}
