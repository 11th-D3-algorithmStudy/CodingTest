import java.util.Arrays;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int pick=0;
        int mineral=0;
        int idx = 0;
        int[][] group = new int[minerals.length/5+1][3];
        int totalpicks = picks[0]+picks[1]+picks[2];
        while(mineral<minerals.length && idx<totalpicks){ 
            String now = minerals[mineral];
            idx = mineral/5;
            if(now.equals("diamond")){
                group[idx][0]++;
            }
            else if(now.equals("iron")){
                 group[idx][1]++;
            }else  {
                 group[idx][2]++;
            }
            mineral++;
        
        }  
        Arrays.sort(group, (a, b) -> {
            if (b[0] != a[0]) {  // 다이아몬드 개수가 다르면, 다이아몬드 기준 내림차순
                return Integer.compare(b[0], a[0]);
            } else if (b[1] != a[1]) {  // 다이아몬드가 같으면, 철 기준 내림차순
                return Integer.compare(b[1], a[1]);
            } else {  // 다이아몬드, 철이 같으면 돌 기준 내림차순
                return Integer.compare(b[2], a[2]);
            }
        });
        int nowidx = 0;
            while(picks[0]>0 && nowidx <group.length){
                answer+= group[nowidx][0]+group[nowidx][1]+group[nowidx][2];
                picks[0]--;
                nowidx++;
            }
        while(picks[1]>0&& nowidx <group.length){
            answer+= group[nowidx][0]*5+group[nowidx][1]+group[nowidx][2];
                picks[1]--;
            nowidx++;
        }
         while(picks[2]>0&& nowidx <group.length){
            answer+= group[nowidx][0]*25+group[nowidx][1]*5+group[nowidx][2];
                picks[2]--;
             nowidx++;
        }
        
        return answer;
        
    }
}
