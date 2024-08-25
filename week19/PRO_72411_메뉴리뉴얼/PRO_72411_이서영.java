import java.util.*;

class Solution {
    static Map<String, Integer> counts = new HashMap<>();
    static int[] max;
    public String[] solution(String[] orders, int[] course) {
        max = new int[11];
        List<String> result = new ArrayList<>();
        for (int d : course){   
            max[d] = 2;
            for (String s : orders){
                char[] arr = s.toCharArray();
                boolean[] visited = new boolean[arr.length];
                Arrays.sort(arr);
                comb(arr, visited, 0, arr.length, d);
            }
        }
        for (String s : counts.keySet()){
            // System.out.println("key:" + s + ", val: " + counts.get(s));
            if (counts.get(s) == max[s.length()]){
                result.add(s);
            }
        }
          
        Collections.sort(result);
        String[] answer = result.toArray(new String[result.size()]);
        
        return answer;
    }
    
    static void comb(char[] arr, boolean[] visited, int start, int n, int r){
       if (r == 0){
           StringBuilder sb = new StringBuilder();
           for (int j = 0; j < visited.length; j++){
               if (visited[j]){
                   sb.append(arr[j]);
               }
           }
           String res = sb.toString();
           counts.put(res, counts.getOrDefault(res, 0) + 1);
           max[res.length()] = Math.max(counts.get(res), max[res.length()]);
           return;
       }
        for (int i = start; i < n; i++){
            visited[i] = true;
            comb(arr, visited, i+1, n, r-1);
            visited[i] = false;
        }
    }
}
