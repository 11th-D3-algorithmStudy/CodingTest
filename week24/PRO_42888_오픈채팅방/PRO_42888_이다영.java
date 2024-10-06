import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        List<String> answer = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        List<String[]> list = new LinkedList<>();
        for(String now : record){
            String[] splitArray = now.split(" ");
            
            String action = splitArray[0];
            String userId = splitArray[1];
            
            if (action.equals("Enter")){
                 String nickname = splitArray[2];  
                 map.put(userId,nickname);
               
                list.add(new String[]{"Enter",userId});
                
            } else if( action.equals("Change")) {
                String nickname = splitArray[2];  
                
                map.put(userId,nickname);
                
            } else if (action.equals("Leave")) {
                   list.add(new String[]{"Leave",userId});
    }
            
        }
             for(String[] now : list){
            String nickname = map.get(now[1]);
                 if(now[0].equals("Enter")){
                       answer.add(nickname+"님이 들어왔습니다.");
                 }
                 else{
                       answer.add(nickname+"님이 나갔습니다.");
                 }
               
             
             }
    return answer.toArray(new String[0]);
    }
}
