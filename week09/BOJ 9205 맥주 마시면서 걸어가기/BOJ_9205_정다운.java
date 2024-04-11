package week09;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9205_정다운 {
	
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int n; // 편의점 개수
	static List<Point> convList; // 편의점 좌표 저장 리스트
	static Point start, penta; // 상근이네 집, 락페 좌표
	static boolean flag; // 결과
	static boolean[] visit;
	
	public static void main(String[] args) throws Exception {
		// 맥주 한박스 20병 * 50m => 한번에 1000m 갈 수 있다
		// 출발지 & 경유지로부터 1000m 이내에 락페 장소 또는 편의점이 있는지 확인하자
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t=0; t<T; t++) {
			n = Integer.parseInt(br.readLine());
			convList = new ArrayList<>();
			visit = new boolean[n+2];
			flag = false;
			
			for (int i=0; i<n+2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int R = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				if (i == 0) { // 상근이네 집
					start = new Point(R, C);
				} else if (i == n+1) { // 락페
					penta = new Point(R, C);
				} else {
					Point conv = new Point(R, C);
					convList.add(conv);
				}
			}
			
			bfs(start);
			
			if (flag) {
				sb.append("happy"+"\n");
			} else {
				sb.append("sad"+"\n");
			}
		}
		System.out.println(sb);
	}

	static void bfs(Point p) {
		Queue<Point> queue = new LinkedList<>();
		
		queue.add(p);
		visit[0] = true;
		
		while(!queue.isEmpty()) {
			Point next = queue.poll();
			
			// 우선 펜타포트랑 거리 확인
			int dist = Math.abs(next.r-penta.r)+Math.abs(next.c-penta.c);
			if (dist > 1000) { // 멀다.. 
				// 편의점 거리 확인
				for (int i=0; i<convList.size(); i++) {
					Point cv = convList.get(i);
					dist = Math.abs(next.r-cv.r)+Math.abs(next.c-cv.c);
					if (dist <= 1000 && !visit[i+1]) { // 갈수 있다! & 간적 없는 편의점이다
						queue.add(cv); // 그 편의점 들르자
						visit[i+1] = true; // 편의점 방문처리 (빙빙 돌지 않게)
					}
				}
			} else { // 펜타포트 갈 수 있다!
				flag = true;
				break;
			}
		}
		
	}
}
