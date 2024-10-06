import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        Map<String,Integer> idMap = new HashMap<>();
        
        //사용자id와 인덱스 매핑
        for(int i =0; i<id_list.length ; i++){
            idMap.put(id_list[i],i);
        }
        
        //몇번 신고당했나
        int[] reportTime = new int[id_list.length];
        //내가 누구 신고했나
        Set<Integer>[] reportWho = new HashSet[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            reportWho[i] = new HashSet<>();
        }
        //k번 넘은 사람
         Set<Integer> reportPerson = new HashSet<Integer>();
        
        for(int i = 0; i< report.length;i++){
            String[] reports = report[i].split(" ");
            int toIndex = idMap.get(reports[1]);
            int fromIndex = idMap.get(reports[0]);
            if(!reportWho[fromIndex].contains(toIndex)){
                reportTime[toIndex]++;
            }
            reportWho[fromIndex].add(toIndex);
        }
        
         for(int i =0; i<reportTime.length ; i++){
             if(reportTime[i]>=k){ //k번이상 신고당하면 이사람 신고한 사람들 +1
               for(int j =0; j<reportTime.length ; j++){
                    if(reportWho[j].contains(i)){ //내가 이사람 신고했으면 +1
                        answer[j]++;
                    }
               }
		    }
         }
        return answer;
    }
}
