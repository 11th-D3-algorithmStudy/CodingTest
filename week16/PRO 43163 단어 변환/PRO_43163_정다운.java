import java.util.*;

class Solution {
    
    static String[] arr;
    static boolean[] visit;
    static int min = 100;
    static String beg;
    
    public int solution(String begin, String target, String[] words) {
        // words에 target이 있는지 먼저 확인하고
        // target부터 거꾸로?....
        // dfs로 해야할듯 ??
        int answer = 0;
        beg = begin;
        
        arr = words;
        visit = new boolean[words.length];
        boolean flag = false;
        int idx = 0;
        
        for (int i=0; i<words.length; i++) {
            String word = words[i];
            if (word.equals(target)) {
                idx = i;
                flag = true;
            }
        }
        
        // words에 target 없으면 0 반환
        if (!flag) {
            return 0;
        }
        
        dfs(idx, 1);
        
        answer = min;
        return answer;
    }
    
    // i : 배열 인덱스, n : 단계
    static public void dfs(int idx, int n) {
        if (comp(arr[idx], beg)) {
            min = Math.min(min, n);
            return;
        }
        
        visit[idx] = true;
        for (int i=0; i<arr.length; i++) {
            if (!visit[i] && comp(arr[idx], arr[i])) {
                dfs(i, n+1);
                visit[i] = false;
            }
        }
    } 
    
    static boolean comp(String str1, String str2) {
        int cnt = 0;
        
        for (int i=0; i<str1.length(); i++) {
            if (str1.toCharArray()[i] != str2.toCharArray()[i]) {
                cnt++;
            }
        }
        
        if (cnt == 1) {
            return true;
        } else {
            return false;
        }
    }
}
