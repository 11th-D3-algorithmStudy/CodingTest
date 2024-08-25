class Solution {
    public int solution(int[] queue1, int[] queue2) {
      
        // 먼저 절반 값 구하기
        int sum = 0;
        int halfSum = 0;
        
        for(int i=0;i<queue1.length();i++){
            sum += queue1[i]
        }
        
        for(int i=0;i<queue2.length();i++){
            sum += queue2[i]
        }
        
        halfSum = sum/2;
        
        // 각 큐의 원소 합 같게 만들기 가능한지 계산     
        
        // 큰 queue 에서 작은 queue로 이동
        // 하면서 최솟값을 가질 때.....?
        // 모르겠음 ^.^

      
        int answer = -2;
        return answer;
    }
}
