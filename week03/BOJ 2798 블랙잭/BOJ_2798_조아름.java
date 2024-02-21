import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2798_조아름 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = 0;
		for(int i=0;i<N-2;i++) {
			for(int j=i+1;j<N-1;j++) {
				for(int k=j+1;k<N;k++) { // 3중 for 문의 시작점과 끝점을 다 다르게 해줘서 조건을 만든다. 
					int sum = arr[i]+arr[j]+arr[k]; // 3개의 수를 구하는데 3중 for 문을 돌린다.
					if(sum<=M) {
						max = Math.max(max, sum); // 최댓값을 찾아야 하기 때문에 Math.max를 쓴다.
					}
				}
			}
		}
		
		System.out.println(max);
		
	}

}
