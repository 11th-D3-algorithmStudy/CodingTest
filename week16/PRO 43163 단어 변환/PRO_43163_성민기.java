import java.io.*;
import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
         // words를 List로 변환
        List<String> wordList = Arrays.asList(words);
        
        // 포함하지 않는다면 바로 0 반환
        if(!wordList.contains(target)){
            return 0;
        }
        
        // 바로 BFS 실행
        Queue<WordNode> queue = new LinkedList<>();
        // 방문처리를 Set을 사용하여 시도 -> 와 이걸 어떻게 생각하지. 대단쓰
        Set<String> visited = new HashSet<>();
        
        queue.add(new WordNode(begin, 0));
        visited.add(begin);
        
        while(!queue.isEmpty()){
            WordNode node = queue.poll();
            String nowWord = node.word;
            int nowLevel = node.level;
            
            // target과 같은 단어일 때 바꾼 횟수 반환
            if(nowWord.equals(target)){
                return nowLevel;
            }
            
            // wordList에 있는 내용 모두 비교
            for(String word : wordList){
                // 방문처리되지 않은 것과 변환가능 여부 확인
                if(!visited.contains(word) && canTransform(nowWord, word)){
                    queue.add(new WordNode(word, nowLevel + 1));
                    visited.add(word);
                }
            }
        }
        
        // 이거 안쓰면 에러뜨던데 왜?
        // 변환할 수 없는 경우 0 반환 이라함
        return 0;
    }
    
    // 두 단어가 한 글자만 다른지 확인하는 메서드
    public static boolean canTransform(String A, String B){
        int count = 0;
        for(int i=0; i<A.length(); i++){
            if(A.charAt(i) != B.charAt(i)){
                count++;
            }
            // 1보다 크다면 글자의 두 자리 이상 다르다는 것이므로
            // 문제 조건에 해당하지 않음 -> false
            if (count > 1) {
                return false;
            }
        }
        // 아니라면 true
        return true;
    }
    
    // int[]로 bfs 사용하려고 했는데 String, int 구조라서 
    // 클래스로 선언 후 노드 사용하는 방법으로 함.
    static class WordNode {
        String word;
        int level;
        
        WordNode(String word, int level) {
            this.word = word;
            this.level = level;
        }
    }
}