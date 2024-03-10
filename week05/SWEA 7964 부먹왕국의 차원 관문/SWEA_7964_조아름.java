package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7964_조아름 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int c=0;c<T;c++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken()); // 도시 수
			int D = Integer.parseInt(st.nextToken()); // 거리
			
			int[] arr = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int count = 0; // 차원관문을 세기 위한 변수
			int limit = 0; // 제한 거리만큼 이동했을 때 값을 측정하기 위한 변수
			
			for(int i=0;i<N;i++) {
				if(arr[i] == 1) { // 차원관문일 때는 값을 세지 않는다.
					limit = 0;
				}else if(arr[i]==0) { // 차원관문이 아닐 때는 값을 증가시킨다.
					limit++;
					if(limit==D) {
						count++;
						limit = 0; // **초기화 필요 -> 0에서 1로 바뀌었기 때문에**
					}
				}
			}
			
			System.out.println("#"+(c+1)+" "+count);
		}
	}
}
