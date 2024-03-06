import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Chingho {
	String name;
	int max;
	
	Chingho () {}
	
	Chingho (String name, int max){
		this.name = name;
		this.max = max;
	}
}
public class BOJ_19637_이윤주 {
	/*
	 * Q. IF문 좀 대신 써줘
	 *  #문제요약
	 *  	전투력 구간에 따른 칭호를 출력하기
	 *  	입력 : 칭호의 개수 N (<= 10^5), 캐릭터 개수 M (<=10^5)
	 *  	N개의 칭호명 문자열 + 칭호의 전투력 상한값
	 *  	M개의 전투력 정수
	 *  	출력 : M개 줄에 걸쳐 전투력에 맞는 칭호 출력 
	 *  	(칭호 여러개 해당하는 경우 가장 먼저 입력된 칭호 하나만 출력)
	 *  #풀이시간 : 3시간...
	 *  #메모리/시간 : 
	 *  #메인접근법
	 *  	1. 일단 칭호의 전투력 상한값을 정렬해야됨
	 *  	2. 근데 여러개인 경우 먼저 입력된 것 -> 안정정렬이 필요함 
	 *  	3. Arrays.sort() : dual-pivot Quicksort 알고리즘 사용, 불안정 정렬
	 *  		평균시간복잡도 O(nlogn) (최악 O(n^2))
	 *  	4. Collections.sort() : Timsort 알고리즘 = 병합 + 삽입정렬
	 *  		두 정렬 모두 안정 정렬
	 *  		평균,최악 모두 O(nlogn)
	 *  	5. 하지만 공부를 위해 과감히 병합정렬 구현하기로 결정!
	 *  	6. 칭호문자열 : 전투력 상한값 -> 클래스로 구현
	 *  	7. 다시 뒤엎고 이진탐색 구현~
	 *  #문제해결
	 *  	시간초과...
	 *  	질문게시판 확인하고 이진탐색이었다는 것을 깨달음...
	 *  	이진탐색 등호 때문에 안됨......
	 *  	이진탐색에서 원래는 key == mid이면 mid를 출력하는데 
	 *  	앞의 구간에 포함시켜줘야되는 것을 생각해내지 못했다...
	 */
	static int N, M;
	static Chingho[] list;
	static int[] playerPower;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//칭호 개수 N, 캐릭터 개수 M
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new Chingho[N];
		playerPower = new int[M];
		
		//칭호 N개 받기
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int max = Integer.parseInt(st.nextToken());
			
			list[i] = new Chingho(name, max); 
		}
		
		StringBuilder sb = new StringBuilder();
		
		//캐릭터 전투력 M개 받으면서 출력
		for(int i = 0; i < M; i++) {
			playerPower[i] = Integer.parseInt(br.readLine());
			
			//이진탐색 해서 출력하기
			int idx = binarySearch(playerPower[i], 0, list.length - 1);
			
			sb.append(list[idx].name + "\n");
		}
		
		System.out.println(sb);
		
	}
	
	//이진탐색으로 key가 포함되는 구간에 해당하는 idx 리턴하는 메서드
	static int binarySearch(int key, int st, int ed) {
		//기저조건...?
		if(st <= ed) { //st 랑 ed가 교차되면 그 구간에 값이 있는 것임		
			int mid = (st + ed) / 2;
			
			//mid값보다 작거나 같을 때, key는 그 이전 구간에 있음 => 값이 같을 땐 앞에 나온 칭호로 출력하니까 앞의 구간에 포함됨
			if(key <= list[mid].max) {
				return binarySearch(key, st, mid - 1);
			} else { //mid값보다 클 때 key는 그 다음 구간에 있음
				return binarySearch(key, mid + 1, ed);
			}	
			
		}
		//그 구간에 값이 있으니까 앞의 인덱스에 포함됨
		return st;
	}

}
