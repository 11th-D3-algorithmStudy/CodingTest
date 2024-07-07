class Solution {  // 던전 개수가 8 이하이므로, 가능한 모든 순열을 완전탐색하는 문제로 보인다.
    static int N;        // 던전 개수
    static int[][] sel;  // 던전 순열 저장하는 배열
    static int max = -1; // 탐험 가능한 최대 던전 수
    
    public int solution(int k, int[][] dungeons) {
        N = dungeons.length;
        sel = new int[N][2];
        perm(0, 0, k, dungeons);
        return max;
    }
    
    // sidx : sel 배열에 저장할 위치
    // check : 비트마스킹에 사용하는 정수
    static void perm(int sidx, int check, int k, int[][] dungeons) {
        // base case
        // 순열이 완성되었으므로 최대 던전 수 계산
        if (sidx == N) {
            int cnt = 0;
            
            for (int i = 0; i < N; i++) {
                if (k >= sel[i][0]) {
                    k -= sel[i][1];
                    cnt++;
                } else {
                    break;
                }
            }
            
            max = Math.max(max, cnt);
            return;
        }
        
        // recursive case
        for (int i = 0; i < N; i++) {
            if ((check & (1 << i)) > 0) {
                continue;  // i번째 원소를 이미 순열에 사용한 경우 패스
            }
            
            // 아직 사용하지 않은 원소인 경우 순열에 추가
            sel[sidx] = dungeons[i];
            
            perm(sidx + 1, check | (1 << i), k, dungeons);
        }
    }
}