import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int count = 0;
        List<String> q = new LinkedList<>();
        for (int i = 0; i < cities.length; i++){
            boolean found = false;
            int foundIdx = -1;
            for (int j = 0; j < q.size(); j++){
                String city = q.get(j);
                if (city.toLowerCase().equals(cities[i].toLowerCase())){
                    found = true;
                    foundIdx = j;
                    break;
                }
            }
            if (found){
                count++;
                q.remove(foundIdx);
            }else{
                count += 5;
            }  
            q.add(cities[i]);
            if (q.size() > cacheSize){
                q.remove(0);
            }
        }
        return count;
    }
}