import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BOJ_27522_이서영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<String, List<Integer>> scores = new HashMap<>();
		scores.put("B", new ArrayList<>());
		scores.put("R", new ArrayList<>());	
		List<Integer> total = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			String[] recordByTeam = br.readLine().split(" ");
			Integer[] time = Arrays.stream(recordByTeam[0].split(":")).map(Integer::parseInt).toArray(Integer[]::new);
			int runtime = time[0] * 60000 + time[1] * 1000 + time[2];
			scores.get(recordByTeam[1]).add(runtime);
			total.add(runtime);
		}
		Collections.sort(total);
		int red = 0;
		int blue = 0;
		int[] points = {10, 8, 6, 5, 4, 3, 2, 1};
		for (int j = 0; j < total.size(); j++) {
			if (scores.get("R").contains(total.get(j))) {
				red += points[j];
			}else {
				blue += points[j];
			}
		}
		if (red > blue) {
			System.out.println("Red");
		}else {
			System.out.println("Blue");
		}
		
	}
}

