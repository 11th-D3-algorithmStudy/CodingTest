import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7964_이서영 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			String[] infos = br.readLine().split(" ");
			int num = Integer.parseInt(infos[0]);
			int distance = Integer.parseInt(infos[1]);
			int[] portals = new int[num];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < num; j++) {
				portals[j] = Integer.parseInt(st.nextToken());
			}
			int count = 1;
			int pos = distance - 1;
			for (int k = 0; k < distance; k++) {
				if (portals[k] == 1) {
					pos = k;
					count--;
					break;
				}
			}
			int d = 0;
			while (pos < num) {
				if (d == distance) {
					if (portals[pos] == 0) {
						count++;
						d = 0; 
					}
				}
				if (portals[pos] == 1) {
					d = 0;
				}
				pos++; d++;
			}
			
			System.out.println("#" + (i+1) + " " + count);
		}
	}
}
