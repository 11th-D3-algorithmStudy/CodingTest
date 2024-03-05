import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_8958_이윤주 {
	public static void main(String[] args) throws Exception {
		//누적합?
		//9시 59분 시작 - 10시 8분 종료 : 풀이시간 9분
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//테스트케이스
		int test = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= test; tc++) {
			//문자열 입력 받기
			String ox = br.readLine();
			
			//누적합 배열 선언
			int[] count = new int[ox.length()];
			
			//누적합 저장하는 변수
			int cnt = 0;
			
			//ox 문자열을 순회하면서
			for(int i = 0; i < ox.length(); i++) {
				if(ox.charAt(i) == 'O') {
					//O를 만나면 누적합 카운트 배열에 누적합 저장
					count[i] = ++cnt;
				} else {
					//X만나면 초기화
					cnt = 0;
				}
			}
			//총 점수 계산하는 변수
			int sum = 0;
			//누적합 배열 순회하면서 총 점수 더하기
			for(int i = 0; i < count.length; i++) {
				sum += count[i];
			}
			
			//출력
			System.out.println(sum);
		} //테스트케이스 끝
		
	}
}
