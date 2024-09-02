import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		Map<String, Integer> totalTimeMap = new HashMap<>();

		List<Integer> answer = new ArrayList<>();
		List<int[]> list = new ArrayList<>();
		for (int i = 0; i < records.length; i++) {
			String inout = records[i].split(" ")[2];
			String number = records[i].split(" ")[1];
			int hour = Integer.parseInt(records[i].split(":")[0]);
			int minute = Integer.parseInt(records[i].split(" ")[0].split(":")[1]);
			int totalM = hour*60 + minute;
			if (inout.equals("IN")) {
				map.put(number, totalM); // 시작 시간을 Map

			} else {
				// 사용시간
				int usingTime = totalM - map.get(number);
                map.remove(number);
                boolean flag= false;
				for (int j=0;j< list.size();j++) {
					if (list.get(j)[0] == Integer.parseInt(number)) {
					
						flag=true;
						list.get(j)[1] += usingTime;
					break;
					}
				}
				if(!flag) {
					list.add(new int[] { Integer.parseInt(number), usingTime });
					
				}
			}
		}
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			int inTime = entry.getValue();
			int usingTime = (23 * 60 + 59) - inTime; 
			int number = Integer.parseInt(entry.getKey());
			
			boolean flag= false;
			for (int j=0;j< list.size();j++) {
				if (list.get(j)[0] == number) {
					flag=true;
					list.get(j)[1] += usingTime;
				break;
				}
			}
			if(!flag) {
				list.add(new int[] {number, usingTime });
				
			}
		}
			for (int j=0;j< list.size();j++) {
			int price = 0;
			if (list.get(j)[1] > fees[0]) {
				price = fees[1] + fees[3] * (int)Math.ceil((double)(list.get(j)[1] - fees[0]) / fees[2]);
			} else {
				// 기본 요금 적용
				price = fees[1];
			}
			list.get(j)[1]=price;
		}


		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
		for (int[] entry : list) {
			pq.add(new int[] { entry[0], entry[1] });
		}

            int[] resultArray = new int[pq.size()];
        int index = 0;

        while (!pq.isEmpty()) {
            int[] entry = pq.poll();
            resultArray[index++] = entry[1]; // 배열에 담기
        }
            return resultArray;
	}
}
