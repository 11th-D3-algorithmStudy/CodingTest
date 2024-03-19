package week07;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class BOJ_5568_정다운 {
	public static void main(String[] args) throws Exception {
		
		// 모두 조합으로 풀었을 것 같아서 다른 방법으로 품 !
		
		// 은 구라고
		// 조합으로 풀어보려 했으나
		// 카드를 고를 때 입력된 순서대로만 골라지는 문제를 해결하지 못함 ,,,,
		// 2 <= k <= 4 이니까 case 나눠서 for문 돌려도 해결될 것 같아서 이렇게 품..ㅠ
		// 조합 공부하고 다시 풀겠습니다 ~
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		
		String[] arr = new String[n];
		HashSet<String> set = new HashSet<>();
		
		for (int i=0; i<n; i++) {
			arr[i] = br.readLine();
		}
		
		switch (k) {
			case 2: {
				for (int i=0; i<n; i++) {
					for (int j=0; j<n; j++) {
						if (i != j) {
							String str = arr[i]+arr[j];
							set.add(str);
						}
					}
				}
				break;
			}
			case 3: {
				for (int i=0; i<n; i++) {
					for (int j=0; j<n; j++) {
						for (int k2=0; k2<n; k2++) {
							if (i != j && j != k2 && k2 != i) {
								String str = arr[i]+arr[j]+arr[k2];
								set.add(str);
							}
						}
					}
				}
				break;
			}
			case 4: {
				for (int i=0; i<n; i++) {
					for (int j=0; j<n; j++) {
						for (int k2=0; k2<n; k2++) {
							for (int l=0; l<n; l++) {
								if (i != j && j != k2 && k2 != l && l != i &&
										i != k2 && j != l) {
									String str = arr[i]+arr[j]+arr[k2]+arr[l];
									set.add(str);
								}
							}
						}
					}
				}
				break;
			}
		}
		
		System.out.println(set.size());

	}

}
