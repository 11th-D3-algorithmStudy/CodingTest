package algorithm;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class Solution {
     public int[] solution(String[] genres, int[] plays) {
    	Map<String, TreeMap<Integer, Integer>> chart = new HashMap<>();
        Map<String, Integer> total = new HashMap<>();
        for (int i = 0; i < genres.length; i++){
            String genre = genres[i];
            TreeMap<Integer, Integer> perGenre = chart.get(genre);
            if (perGenre == null){
                TreeMap<Integer, Integer> played = new TreeMap<Integer, Integer>();
                played.put(plays[i], i);
                chart.put(genre, played);
            }else {
            	if (perGenre.get(plays[i]) == null){
            		perGenre.put(plays[i], i);
            	}
            }

            
            
            total.put(genre, total.getOrDefault(genre, 0) + plays[i]);
        }
        int genreSize = total.size();
        int[] answer = new int[genreSize * 2];
        
        String maxGenre = "";
        int x = 0;
        while (!total.isEmpty()){
        	for (String g : total.keySet()) {
        		if (total.get(g) > total.getOrDefault(maxGenre, 0)) {
        			maxGenre = g;
        		}
        	}
            total.remove(maxGenre);  
            Map.Entry<Integer, Integer> entry = chart.get(maxGenre).pollLastEntry();
            System.out.println(chart);
            answer[x++] = entry.getValue();
            if (!chart.get(maxGenre).isEmpty()) {
	            Map.Entry<Integer, Integer> entry2 = chart.get(maxGenre).pollLastEntry();
	            answer[x++] = entry2.getValue();
            }
        }
        
        return answer;
    }
}