package week09;

import java.io.*;
import java.util.*;

public class BOJ_9205_장현영 {

	// 맥주 마시면서 걸어가기
		// 한짝 20개, 50m에 한 병씩 최대 1000m 이동가능!
		// 중간 지점에서 빈병 버리고, 새 맥주 사기, 편의점오면 또 거기서부터 50m
		// 두 좌표 거리 맨해튼으로 구함

		static List<int[]> route;
		static int n, m;
		static boolean[] visited;

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st;
			int T = Integer.parseInt(br.readLine());
			for (int t = 1; t <= T; t++) {
				n = Integer.parseInt(br.readLine()); // 편의점 개수
				route = new ArrayList<>();
				visited = new boolean[n+2];
				for (int i = 0; i < n + 2; i++) {
					// 출발지, 편의점 n개, 목적지순으로 담김
					st = new StringTokenizer(br.readLine());
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					route.add(new int[] { x, y });
				}
				if(bfs(route.get(0), route.get(n+1))) System.out.println("happy");
				else System.out.println("sad");
			}
			
		}

		// 거리구하기
		public static int calc(int[] left, int[] right) {
			return Math.abs(left[1] - right[1]) + Math.abs(left[0] - right[0]);
		}
		
		public static boolean bfs(int[] start, int[] finish) {
			Queue<int[]> queue = new LinkedList<>();
			queue.add(start);
			visited[0] = true; 
			while(!queue.isEmpty()) {
				int[] curr = queue.poll();
				for(int i=1;i<=n+1;i++) { // start 제외한 나머지 확인하는데, visited인 곳은 pass
					int[] next = route.get(i);
					if(!visited[i] && calc(curr,next)<=1000) {
						if(next == finish) return true;
						queue.offer(next);
						visited[i] = true;
					}
				}
			}
			return false;
		}

	}