class Solution {    
    public int solution(int n, int[] stations, int w) {
        //아파트 개수 n < 200000000
        //stations : 기지국 설치된 아파트 번호 배열 < 10000 (1~n)
        //w : 전파 도달거리 < 10000
        
        int answer = 0;
        //처음 설치된 기지국 전까지 구간
        int first = stations[0] - 1 - w;
        if(first > 0)
            answer += first % (2 * w + 1) == 0 ? first / (2 * w + 1) : first / (2 * w + 1) + 1;
        
        //기지국 사이에 전파가 안닿는 아파트들
        for(int i = 0; i < stations.length - 1; i++){
            int apt1 = stations[i];
            int apt2 = stations[i+1];
            
            int noSignal = apt2 - apt1 - 1 - 2 * w;
            if(noSignal > 0){
                answer += noSignal % (2 * w + 1) == 0 ? noSignal / (2 * w + 1) : noSignal / (2 * w + 1) + 1;
            }
        }
        
        //마지막 구간
        int last = n - stations[stations.length - 1] - w;
        if(last > 0)
            answer += last % (2 * w + 1) == 0 ? last / (2 * w + 1) : last / (2 * w + 1) + 1;

        return answer;
    }
}
