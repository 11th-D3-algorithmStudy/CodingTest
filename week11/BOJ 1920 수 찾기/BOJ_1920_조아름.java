package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1920_조아름 {
	static int[] dap, arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		dap = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) {
			dap[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(dap);
		
		int M = Integer.parseInt(br.readLine());
		arr = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0;i<M;i++) {
			System.out.println(binarySearch(arr[i], 0, N-1));
		}
		
		
	}

	private static int binarySearch(int key, int low, int high) {
		int mid;
		
		if(low<=high) {
			mid = (low+high) /2;
			
			if(key==dap[mid]){
				return 1;
			}else if(key<dap[mid]) {
				return binarySearch(key, low, mid-1);
			}else {
				return binarySearch(key, mid+1, high);
			}
		}
		return 0;
	}
}
