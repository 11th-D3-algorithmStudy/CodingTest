import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BOJ_2108_이윤주 {
	public static void main(String[] args) throws Exception {
		//avg 평균
		//median 중앙값
		//mode 최빈값
		//range 최대 최소 차이 
		
		//입력 N <= 500000, 정수 절대값 4000 -> 최대 20억 (int 범위)
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//입력되는 정수의 수 n
		int n = Integer.parseInt(br.readLine());
		
		int[] nums = new int[n];
		
		//sum
		int sum = 0;
		
		//count
		Map<Integer, Integer> count = new HashMap<>();
		
//		int[] cnt_plus = new int[4001]; //0포함
//		int[] cnt_minus = new int[4001];
		
		//max, min
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		//최빈값 
		int maxCount = 0;

		for(int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(br.readLine());
			sum += nums[i];
			max = Math.max(max, nums[i]);
			min = Math.min(min, nums[i]);
			if(count.get(nums[i]) != null) { //이미 나왔던 수
				count.put(nums[i], count.get(nums[i]) + 1); //카운트 up
				maxCount = Math.max(maxCount, count.get(nums[i]));
				continue;
			}
			count.put(nums[i], 1); //새로나온 수 key에 처음 접근하는 거면 카운트 up
			maxCount = Math.max(maxCount, count.get(nums[i]));
		}
		//평균
		int avg = (int) Math.round((double) sum / n); 
		
		//n은 홀수 => 중앙값 = n/2 번째 인덱스
		Arrays.sort(nums);
		int median = nums[n / 2];
		
		//리스트에 키값 담기
		List<Integer> keySet = new ArrayList<>(count.keySet());
		
		//맵을 키값 기준으로 오름차순 정렬
		keySet.sort(Comparator.naturalOrder());
		
		//최빈값들을 담을 리스트
		List<Integer> modeList = new ArrayList<>();
		
		for(int key : keySet) {
			if(count.get(key) == maxCount) {//최빈값이 여러개일 수 있으므로 리스트에 담음
				modeList.add(key);
			}
		}
		
		//최빈값이 여러개이면 두번째 값 
		int mode = 0;
		if(modeList.size() >= 2) {
			mode = modeList.get(1);
		} else {
			mode = modeList.get(0);
		}
		
		//범위 = 최댓값 - 최솟값
		int range = max - min;
		
		//평균, 중앙값, 최빈값, 범위 출력
		System.out.println(avg);
		System.out.println(median);
		System.out.println(mode);
		System.out.println(range);

	}
}
