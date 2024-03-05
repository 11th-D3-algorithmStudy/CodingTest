import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BOJ_19637_이서영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] infos = br.readLine().split(" ");
		int titleN = Integer.parseInt(infos[0]);
		int dataN = Integer.parseInt(infos[1]);
		List<String> titleName = new ArrayList<>();
		List<Integer> titleVal = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		int prev = -1;
		for (int i = 0; i < titleN; i++) {
			String[] title = br.readLine().split(" ");
			String name = title[0];
			int val = Integer.parseInt(title[1]);
			if (val != prev) {
				titleName.add(name);
				titleVal.add(val);
				prev = val;
			}
		}
		for (int i = 0; i < dataN; i++) {
			int num = Integer.parseInt(br.readLine());
			int index = Collections.binarySearch(titleVal, num);
			if (index < 0) {
				sb.append(titleName.get(-index-1) + "\n");
			}else {
				sb.append(titleName.get(index) + "\n");
			}
		}
		System.out.println(sb);
	}
}
