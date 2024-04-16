package Study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1920_이윤주 {
	// 수 찾기
	// N개의 정수 안에 X라는 정수가 존재하는지 알아내라
	// N 1~100000
	// M 1~100000
	// 정수 범위 -2^31~2^31
	// 4바이트 정수 = int
	// 이분탐색

	static int N, M;
	static int[] nums;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(nums);
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int X = Integer.parseInt(st.nextToken());

			System.out.println(binarySearch(X, 0, N - 1));
		}

	}

	// nums 배열안에 x가 있는지 찾는 이진탐색
	private static int binarySearch(int x, int start, int end) {

		if (start <= end) { //등호 꼭 있어야됨!
			int mid = (start + end) / 2;
			if (x > nums[mid]) {
				return binarySearch(x, mid + 1, end); //오른쪽 반
			} else if (x < nums[mid]) {
				return binarySearch(x, start, mid - 1); //왼쪽 반
			} else { // x == nums[mid]
				return 1; //탐색 성공
			}
		}
		return 0; //탐색 실패
	}

}
