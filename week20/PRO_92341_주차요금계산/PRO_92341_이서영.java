import java.util.*;

class Solution {
    private static final int IN = 0;
    private static final int OUT = 1;
    public int[] solution(int[] fees, String[] records) {
        Set<Integer> cars = new HashSet<>();
        Map<Integer, int[]> carInfos = new HashMap<>();
        for (String s : records){
            String[] info = s.split(" ");
            String time = info[0];
            int plate = Integer.parseInt(info[1]);
            cars.add(plate);
            String inOut = info[2];
            String[] timeSplit = time.split(":");
            int hour = Integer.parseInt(timeSplit[0]);
            int min = Integer.parseInt(timeSplit[1]);
            if (inOut.equals("IN")){   
                int inTime = hour * 60 + min;
                int totalTime = 0;
                int[] lastRec = carInfos.getOrDefault(plate, null);
                if (null != lastRec){
                    totalTime = lastRec[2];
                }
                int[] temp = {IN, inTime, totalTime};
                carInfos.put(plate, temp);
            }else{
                int[] inInfo = carInfos.get(plate);
                int outTime = hour * 60 + min;
                int timePassed = outTime - inInfo[1];
                int[] temp = {OUT, outTime, inInfo[2] + timePassed};
                carInfos.put(plate, temp);
            }
        }
        
        List<Integer> carsList = new ArrayList<>(cars);
        Collections.sort(carsList);
        int[] answer = new int[carsList.size()];
        int idx = 0;
        for (int car : carsList){
            int[] res = carInfos.get(car);
            if (res[0] == IN){
                int fee = endOfTheDay(res[1], res[2], fees);
                answer[idx] = fee;
            }else{
                answer[idx] = calcFee(res[2], fees);
            }
            idx++;
        }

        return answer;
    }
    
    public int endOfTheDay(int inTime, int timePassed, int[] fees){
        int dur = (23 * 60 + 59) - inTime + timePassed;
        return calcFee(dur, fees);
        
    }
    
    public int calcFee(int time, int[] fees){
        int baseMin = fees[0];
        int baseFee = fees[1];
        int unitMin = fees[2];
        int unitFee = fees[3]; 
        
        int total = baseFee;
        
        if (time - baseMin > 0){
            int remaining = time - baseMin;
            int rounds = remaining / unitMin;
            if (remaining % unitMin != 0){
                rounds++;
            }
            total += rounds * unitFee;
        }
        return total;   
    }
}
