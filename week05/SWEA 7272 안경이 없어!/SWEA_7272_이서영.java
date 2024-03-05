import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class SWEA_7272_이서영 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Map<Character, Integer> alphMap = new HashMap<>();
		alphMap.put('A', 1);
		alphMap.put('D', 1);
		alphMap.put('O', 1);
		alphMap.put('P', 1);
		alphMap.put('Q', 1);
		alphMap.put('R', 1);
		alphMap.put('B', 2);
		for (int i = 0; i < n; i++) {
			String[] words = br.readLine().split(" ");
			boolean isSame = true;
			String word1 = words[0];
			String word2 = words[1];
			if (word1.length() != word2.length()) {
				isSame = false;
			}else {
				for (int j = 0; j < word1.length(); j++) {
					if (alphMap.getOrDefault(word1.charAt(j), 0) != alphMap.getOrDefault(word2.charAt(j), 0)) {
						isSame = false;
						break;
					}
				}
			}
			if (!isSame) {
				System.out.println("#" + (i+1) + " DIFF");
			}else {
				System.out.println("#" + (i+1) + " SAME");
			}
		}
	}
}
