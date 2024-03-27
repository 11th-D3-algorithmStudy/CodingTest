package week08;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class SOFT_6250_정다운 {
	
	// 세번의 대회동안의 참가자의 등수 + 최종 등수 구하기
	// 정렬 & 찾는 값의 idx 확인을 위해 List 사용했었으나 시간초과
	// GPT : "list.indexOf는 선형 탐색이므로 시간이 많이 걸릴 수 있습니다."
	// -> HashMap 사용
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
        int N = Integer.parseInt(br.readLine());

        // 최종 등수 확인을 위한 배열 & 맵
        int[] finalArr = new int[N];
        // 내림차순 정렬을 위해 Wrapper Class(Integer) 사용
        Integer[] sortFArr = new Integer[N];
        HashMap<Integer, Integer> finalMap = new HashMap<>();
        
        // 대회만큼 반복
        for (int i=0; i<3; i++) {
        	int[] arr = new int[N];
        	Integer[] sortArr = new Integer[N];
            HashMap<Integer, Integer> map = new HashMap<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            // 점수 입력
            for (int j=0; j<N; j++) {
                int score = Integer.parseInt(st.nextToken());
                
                arr[j] = score;
                sortArr[j] = score;
                finalArr[j] += score;
                // sortFArr의 요소는 Integer라 초기값 null... += 사용시 에러발생
                // 마지막 대회까지 기다렸다가 finalArr에 있는 값 넣어준다
                if (i == 2) {
                	sortFArr[j] = finalArr[j];
                }
            }
            
            // 내림차순 정렬 (앞에 있는 요소가 낮은 숫자의 등수가 될 수 있도록)
            Arrays.sort(sortArr, Collections.reverseOrder());
            
            int rank = 1;
            for (int j=0; j<N; j++) {
            	if(!map.containsKey(sortArr[j])) {
    				map.put(sortArr[j], rank);
    			}
            	// 중복된 점수이면 map에 입력은 안하지만 다음 점수의 rank 올려준다
            	// '각 사람마다 “나보다 점수가 큰 사람”의 수를 세어 1을 더한 것이 자신의 등수가 된다.'
            	// 는 규칙 만족
            	rank++;
            }
            
            for (int j=0; j<N; j++) {
            	sb.append(map.get(arr[j])+" ");
            }
            sb.append("\n");
        }
        
        // 각 대회의 등수 확인했던 방법과 동일하게 최종 등수 확인
        Arrays.sort(sortFArr, Collections.reverseOrder());
        
        int rank = 1;
        for (int j=0; j<N; j++) {
        	if(!finalMap.containsKey(sortFArr[j])) {
				finalMap.put(sortFArr[j], rank);
			}
        	rank++;
        }
        
        for (int j=0; j<N; j++) {
        	sb.append(finalMap.get(finalArr[j])+" ");
        }
        
        System.out.println(sb);
	}
}
