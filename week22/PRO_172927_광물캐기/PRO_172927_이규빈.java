import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int totalPicks = picks[0] + picks[1] + picks[2];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        // "돌 곡괭이를 썼을 때의 피로도"를 기준으로 내림차순 저장
        // 왜냐하면 돌 곡괭이만 광물 종류를 식별해주고, "어려운 광물이 많은 순서"로 정렬할 수 있기 때문이다.
        // 따라서 PQ에서 뽑는 순서대로 "가장 좋은 곡괭이"부터 사용해 처리한다.
        
        
        // 1. 광물을 5개씩 그룹화해서 피로도를 계산해 PQ에 저장
        for (int i = 0; i < minerals.length; i += 5) {
            if (i / 5 >= totalPicks) {
                break; // 곡괭이가 모자란 경우, 계산 중단
            }
            
            int dia = 0, iron = 0, stone = 0;
            
            // 반복 종점 계산 주의 (마지막 그룹이 5개 미만일 수 있음)
            for (int j = i; j < Math.min(i + 5, minerals.length); j++) { 
                if (minerals[j].equals("diamond")) {
                    dia++; iron += 5; stone += 25;
                } else if (minerals[j].equals("iron")) {
                    dia++; iron++; stone += 5;
                } else {
                    dia++; iron++; stone++;
                }
            }
            pq.offer(new int[]{dia, iron, stone});
        }
        
        
        // 2. "피로도가 높은 광물 그룹"부터 "가장 좋은 곡괭이"로 처리 (=그리디)
        for (int i = 0; i < 3; i++) {
            while (picks[i] > 0 && !pq.isEmpty()) {
                int[] group = pq.poll();
                answer += group[i];
                picks[i]--;
            }
        }
        
        return answer;
    }
}
