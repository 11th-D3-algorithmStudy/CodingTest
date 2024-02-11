import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_11399_성민기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// withDrawal -> 입력값 받는 배열
		// count -> 카운팅 정렬을 위한 배열
		// result -> 결과를 받기 위한 리스트
		int[] withDrawal = new int[N];
		int[] count = new int[N+1];
		List<Integer> result = new ArrayList<>();
		
		// 입력값을 받은 후 withDrawal 배열에 입력
		String[] withStr = br.readLine().split(" ");
		for(int i=0; i<N; i++) withDrawal[i] = Integer.parseInt(withStr[i]);
		
		for(int i=0; i<N; i++) {
			count[withDrawal[i]]++;
		}
		// 카운팅 정렬
		for(int i=0; i<count.length; i++) {
			// 카운트를 하나라도 한 인덱스가 있다면
			while(count[i] > 0) {
				// result 리스트에 Add
				result.add(i);
				// count[i]-- 
				// => 카운트를 여러번 했다면 result 리스트에 입력 후 
				// 카운트 개수를 감소시키는 명령
				count[i]--;
			}
		}
		
		// 카운팅정렬을 한 결과를 담은 리스트 1 2 3 3 4 값의 합을 구하기 위한 반복문
		// 누적합
		for(int i=1; i<result.size(); i++) {
			// count[i] += count[i-1] 와 같다
			// 이전값과의 합을 구하여 그 위치에 Add
			int tmp = result.remove(i);
			result.add(i, tmp + result.get(i-1));
		}

		int sum = 0;
		for(int i=0; i<result.size(); i++) {
			sum += result.get(i);
		}
		
		System.out.println(sum);
	}
}
