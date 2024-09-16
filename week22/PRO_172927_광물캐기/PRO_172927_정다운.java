import java.io.*;
import java.util.*;

class Solution {
    
    static class Group implements Comparable<Group>{
        int dia;
        int iron;
        int stone;
        int tot;
        
        Group(int dia, int iron, int stone, int tot) {
            this.dia = dia;
            this.iron = iron;
            this.stone = stone;
            this.tot = tot;
        }
        
        @Override
        public int compareTo(Group o) {
            return o.tot - this.tot; // 내림차순
        }
    }
    
    public int solution(int[] picks, String[] minerals) {
        // 광물을 5개씩 끊어서 피로도 계산 (돌곡괭이 기준)
        // 가장 피로도가 높은 그룹부터 좋은 곡괭이로 캔다
        // + 곡괭이 모자란 경우 처리해주어야 함 !!!
        
        int len = minerals.length % 5 == 0 ? minerals.length/5 : (minerals.length/5)+1;
        Group[] groupArr = new Group[len];
        
        for (int i=0; i<len; i++) {
            groupArr[i] = new Group(0,0,0,0);
        }
        
        // 총 곡괭이 수
        int totPicks = picks[0]+picks[1]+picks[2];
        
        for (int i=0; i<minerals.length; i++) {
            int g = i/5;
            if (g >= totPicks) break; // 곡괭이 모자란 경우 break
            
            if (minerals[i].equals("diamond")) {
                groupArr[g].dia++;
                groupArr[g].tot += 25;
            } else if (minerals[i].equals("iron")) {
                groupArr[g].iron++;
                groupArr[g].tot += 5;
            } else {
                groupArr[g].stone++;
                groupArr[g].tot += 1;
            }
        }
        
        Arrays.sort(groupArr);
        
        int answer = 0;
        int idx = 0;
        
        for (int i=0; i<len; i++) {
            label:
            for (int j=0; j<3; j++) {
                while (picks[j] != 0) {
                    if (j == 0) { // 다이아 곡괭이
                        answer += groupArr[i].dia;
                        answer += groupArr[i].iron;
                        answer += groupArr[i].stone;
                    } else if (j == 1) { // 철 곡괭
                        answer += groupArr[i].dia*5;
                        answer += groupArr[i].iron;
                        answer += groupArr[i].stone;
                    } else { // 돌
                        answer += groupArr[i].dia*25;
                        answer += groupArr[i].iron*5;
                        answer += groupArr[i].stone;
                    }
                    picks[j]--;
                    break label;
                }
            }
        }
        
        return answer;
    }
}
