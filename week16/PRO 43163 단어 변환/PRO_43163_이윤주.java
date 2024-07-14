import java.util.*;

class Solution {
    static int min;
    static boolean[] visited;
    
    public int solution(String begin, String target, String[] words) {
        //일단 target이 words에 있는지 확인
        boolean isExist = false;
        for(String w : words){
            if(w.equals(target)){
                isExist = true;
                break;
            }
        }
        //없는 경우 0 반환
        if(!isExist) return 0;
        
        //있는 경우
        min = words.length;
        visited = new boolean[words.length];
        dfs(begin, target, 0, words);
        
        return min;
    }
    
    public void dfs(String begin, String target, int count, String[] words){
        //기저조건
        if(begin.equals(target)){
            min = Math.min(min, count);
        }
        
        for(int i = 0; i < words.length; i++){
            //갔던 곳 제외
            if(visited[i]) continue;
            
            //begin이랑 한 글자만 다른 단어로 변경
            int diff = 0;
            String next = words[i];
            for(int j = 0; j < begin.length(); j++){
                if(begin.charAt(j) != next.charAt(j)) diff++;
            }
            if(diff == 1){
                visited[i] = true;
                dfs(next, target, count + 1, words);
                visited[i] = false;
            }
        }
    }
}