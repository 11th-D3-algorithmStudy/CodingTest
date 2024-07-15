import java.util.*;

class Solution {
    
    static class UP implements Comparable<UP>{
        int u; // 가입자
        int p; // 판매액
        
        public UP(int u, int p) {
            this.u = u;
            this.p = p;
        }
        
        public int compareTo(UP o) {
            if (this.u != o.u) {
                return o.u - this.u;
            } else {
                return o.p - this.p;
            }
        }
    }
    
    static class Emoji{
        int dis; // 할인율
        int p; // 가격
        
        public Emoji(int dis, int p) {
            this.dis = dis;
            this.p = p;
        }
    }
    
    static int n, m; // 사용자, 이모티콘 수(users, emoticons 배열 길이)
    static PriorityQueue<UP> pq;
    static Emoji[] emoPrices;
    
    public int[] solution(int[][] users, int[] emoticons) {
        // 완탐해서 가입자, 판매액 저장한 객체 pq에 저장
        // 정렬 조건 1. 가입자 2. 판매액
        
        // 할인율 완탐 고고 (백트래킹으로 이모티콘 가격 배열 만들기)
        n = users.length;
        m = emoticons.length;
        pq = new PriorityQueue();
        emoPrices = new Emoji[m];
        
        dfs(users, emoticons, 0);
        
        UP res = pq.poll();
        int[] answer = new int[2];
        answer[0] = res.u;
        answer[1] = res.p;
        
        return answer;
    }
    
    // idx : 이모티콘 idx
    static void dfs(int[][] users, int[] emoticons, int idx) {
        if (idx == m) {
            // 각 유저의 이모티콘 구매비용 확인하고 플러스 가입여부 확인 후 pq에 add
            int plusU = 0; // 플러스 가입자
            int totP = 0; // 총 비용
            for (int i=0; i<n; i++) {
                int userP = 0; // 유저가 쓴 돈
                for (int j=0; j<m; j++) {
                    if (emoPrices[j].dis >= users[i][0]) { // 구매!
                        userP += emoPrices[j].p;
                    }
                }
                if (userP >= users[i][1]) { // 플러스 가입 고고
                    plusU += 1;
                } else {
                    totP += userP;
                }
            }
            pq.add(new UP(plusU, totP));
            return;
        }
        
        // 10~40% 적용하기
        for (int i=1; i<=4; i++) {
            emoPrices[idx] = new Emoji(i*10, emoticons[idx]*(10-i)/10);
            dfs(users, emoticons, idx+1);
        }
    }
}
