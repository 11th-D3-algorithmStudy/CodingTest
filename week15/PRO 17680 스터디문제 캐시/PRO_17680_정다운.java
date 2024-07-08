import java.util.*;

class Solution {

    static Queue<String> queue;
    
    public int solution(int cacheSize, String[] cities) {
		// 큐 사용!
        // 일단 큐에 도시 포함되어 있는지 확인
        // 포함? cache hit + 예전 데이터 지우고 새로 큐에 넣어주기
        // 안포함? cache miss + 큐 사이즈 확인
        // cacheSize > 큐 사이즈? 지금 도시 큐에 add
        // cacheSize <= 큐 사이즈? 맨 앞 도시 빼고 지금 도시 큐에 add
        
        // + 대소문자 구분 노노
        
		int answer = 0;
		
		int N = cities.length;

        // + 캐시 크기 0일때 처리!!!!!
		if (cacheSize == 0) {
			answer = 5 * N;
			return answer;
		}
		
		queue = new LinkedList<>();
		
		for (int i=0; i<N; i++) {
			String input = cities[i].toLowerCase();
			if (queue.contains(input)) { // cache hit
				answer += 1;
				queue.remove(input);
				queue.add(input);
			} else { // cache miss
				answer += 5; 
				if (queue.size() < cacheSize) {
					queue.add(input);
				} else { // 캐시 크기 0일때 이쪽으로 빠져버림 ㅠ 위에서 처리 해주자
					queue.poll();
					queue.add(input);
				}
			}
		}
		
		return answer;

	}
}
