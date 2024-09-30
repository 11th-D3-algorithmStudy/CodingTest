import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> names = new HashMap<>();
        List<String> answer = new ArrayList<>();
        for (int i = 0; i < record.length; i++){
            String rec = record[i];
            String[] info = rec.split(" ");
            if (info[0].equals("Enter") || info[0].equals("Change")){
                names.put(info[1], info[2]);
            }
        }
        for (int i = 0; i < record.length; i++){
            String rec = record[i];
            String[] info = rec.split(" ");
            if (info[0].equals("Enter")){
                answer.add(names.get(info[1]) + "님이 들어왔습니다.");
            }else if(info[0].equals("Leave")){
                answer.add(names.get(info[1]) + "님이 나갔습니다.");
            }
        }
        String[] ans = answer.toArray(new String[answer.size()]);

        return ans;
    }
}
