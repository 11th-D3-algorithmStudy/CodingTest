import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BOJ_1296_이서영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] nameArr = br.readLine().toCharArray();
		int numOfNames = Integer.parseInt(br.readLine());
		List<String> teamNames = new ArrayList<>();
		for (int i = 0; i < numOfNames; i++) {
			teamNames.add(br.readLine());
		}
		Collections.sort(teamNames);
		int max = 0;
		String bestName = teamNames.get(0);
		for (String teamname : teamNames) {
			Map<Character, Integer> alph = new HashMap<>();
			for (char c : nameArr) {
				alph.put(c, alph.getOrDefault(c, 0) + 1);
			}
			char[] teamNameArr = teamname.toCharArray();
			for (char c : teamNameArr) {
				alph.put(c, alph.getOrDefault(c, 0) + 1);
			}
			int score = (alph.getOrDefault('L', 0) + alph.getOrDefault('O', 0)) 
					* (alph.getOrDefault('L', 0) + alph.getOrDefault('V', 0))
					* (alph.getOrDefault('L', 0) + alph.getOrDefault('E', 0))
					* (alph.getOrDefault('O', 0) + alph.getOrDefault('V', 0))
					* (alph.getOrDefault('O', 0) + alph.getOrDefault('E', 0))
					* (alph.getOrDefault('V', 0) + alph.getOrDefault('E', 0)) % 100;
			
			if (score > max) {
				max = score;
				bestName = teamname;
			}
		}
		System.out.println(bestName);
	}
}
