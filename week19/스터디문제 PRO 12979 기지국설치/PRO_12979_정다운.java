class Solution {
    public int solution(int n, int[] stations, int w) {
        // 최대한 그리디하게 설치,,
        // 전파가 닿는 위치의 가장자리 살펴보기
        int cnt = 0;
        int cov = 2*w+1;
        
        // 맨 왼쪽 구간
        int start = 1; // 아파트 시작점
        int s1 = stations[0] - w;
        if (s1 > start) {
            int gap = s1 - start;
            cnt += (gap + cov - 1) / cov;
        }
        
        // 맨 오른쪽 구간
        int s3 = stations[stations.length - 1] + w;
        if (s3 < n) {
            int gap = n - s3;
            cnt += (gap + cov - 1) / cov;
        }
        
        // 사이
        for (int i = 1; i < stations.length; i++) {
            int prevEnd = stations[i - 1] + w; // 이전 구간 끝부분
            int currStart = stations[i] - w; // 현재 구간 시작
            
            if (currStart > prevEnd + 1) { // 안겹치면
                int gap = currStart - prevEnd - 1;
                cnt += (gap + cov - 1) / cov;
            }
        }
        

        return cnt;
    }
}
