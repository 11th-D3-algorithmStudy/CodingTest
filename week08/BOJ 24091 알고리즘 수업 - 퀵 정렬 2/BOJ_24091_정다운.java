package week08;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_24091_정다운 {
	
	static int N, K;
	static int[] arr; 
	static int cnt = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st2.nextToken());
		}
		
		quickSort(0, arr.length-1);
		
		// 교환이 발생할때의 배열 상태를 어디서 뽑아야 할지 몰라서 실패....

	}

	
	static void quickSort(int left, int right) {
		if (left < right) {
			int pivot = partition(left, right);
			quickSort(left, pivot-1);
			quickSort(pivot+1, right);
		}
	}
	
	// 로무토 파티션
	static int partition(int left, int right) {
		int pivot = arr[right];
		int i = left-1;
		
		for (int j=left; j<right; j++) {
			if(arr[j] <= pivot) {
				i++;
				int tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
			}
		}
		
		// pivot을 자기 위치로 보내야 한다
		int tmp = arr[i+1];
		arr[i+1] = arr[right];
		arr[right] = tmp;
		
		return i+1;
	}
}
