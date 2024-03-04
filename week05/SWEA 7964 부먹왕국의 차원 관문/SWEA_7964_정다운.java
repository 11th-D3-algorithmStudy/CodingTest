package 스터디;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class SWEA_7964_정다운 {
    public static void main(String[] args) throws Exception {
        // 최종 상태 : 모든 연속되는 0의 길이가 D보다 작아야 한다
 
        // 연속하는 0의 길이를 D로 나눈 몫(int)이 최소로 두어야 하는 차원관문 개수
        // 배열 탐색하면서 연속되는 0의 개수가 D보다 크거나 같은 경우를 찾아 저장한다
        // 몇개일지 모르니 동적 자료구조 사용
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
 
            int N = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
 
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            // 도시 정보 저장 배열
            int[] cityArr = new int[N];
            for (int i = 0; i < N; i++) {
                cityArr[i] = Integer.parseInt(st2.nextToken());
            }
 
            // 연속되는 0 길이 저장
            Queue<Integer> q = new LinkedList<>();
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                if (D == 1) { // 모든 위치에 관문이 있어야 하는 경우는 0의 개수만 확인한다
                    if (cityArr[i] == 0) {
                        cnt++;
                    }
                    continue;
                }
                 
                if (cityArr[i] == 0) {
                    cnt++;
                } else {
                    if (cnt >= D) { // 쌓인 cnt가 이동제한거리보다 크거나 같다면
                        q.add(cnt);
                    }
                    cnt = 0; // cnt 초기화
                }
            }
 
            // 최소 차원관문 개수 구하기
            int res = 0;
            // 큐가 빌때까지
            while (!q.isEmpty()) {
                res += q.poll() / D;
            }
             
            if (D == 1) {
                res = cnt;
            }
 
            sb.append("#" + t + " " + res + "\n");
        }
        System.out.println(sb);
    }
}