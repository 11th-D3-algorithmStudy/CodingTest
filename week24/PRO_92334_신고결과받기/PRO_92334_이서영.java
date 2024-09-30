import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Integer> userId = new HashMap<>();
        Map<String, Set<Integer>> reportedMap = new HashMap<>();
        int[] notified = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++){
            userId.put(id_list[i], i);
        }
        for (int i = 0; i < report.length; i++){
            String rep = report[i];
            String[] info = rep.split(" ");
            String reporter = info[0];
            String reported = info[1];
            Set<Integer> valset = reportedMap.getOrDefault(reported, new HashSet<Integer>());
            valset.add(userId.get(reporter));
            reportedMap.put(reported, valset);
        }
        for (String reportedName : reportedMap.keySet()){
            Set<Integer> valset = reportedMap.get(reportedName);
            if (valset.size() >= k){
                System.out.println(reportedName);
                for (int id : valset){
                    notified[id]++;
                }
            }
        }
        return notified;
    }
}
