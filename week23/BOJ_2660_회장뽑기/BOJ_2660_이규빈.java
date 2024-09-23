import java.io.*;
import java.util.*;

public class Main {
    // 모든 노드 간의 최단경로를 구해야 한다 => 플로이드-워셜
    // O(N^3)이지만, N = 50이기 때문에 가능

    static final int INF = 51;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] D = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    D[i][j] = 0;
                } else {
                    D[i][j] = INF;
                }
            }
        }

        while (true) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if (A == -1) break;

            // 친구관계는 양방향
            D[A][B] = 1;
            D[B][A] = 1;
        }

        // ----- 입력 끝 -----


        // 플로이드-워셜
        for (int k = 1; k <= N; k++) {         // k = 경유지
            for (int i = 1; i <= N; i++) {     // i = 출발지
                for (int j = 1; j <= N; j++) { // j = 도착지
                    D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
                }
            }
        }


        // 점수 계산
        int[] score = new int[N + 1];
        int minScore = INF;

        for (int i = 1; i <= N; i++) {
            int maxDist = 0;
            for (int j = 1; j <= N; j++) {
                maxDist = Math.max(maxDist, D[i][j]);
            }
            score[i] = maxDist; // 개별 회원의 점수를 계산한다.
            minScore = Math.min(minScore, maxDist); // 최소 점수와 비교한다.
        }


        // 회장 후보 찾기
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (score[i] == minScore) {
                list.add(i);
            }
        }


        // 결과 출력
        StringBuilder sb = new StringBuilder();
        sb.append(minScore + " " + list.size() + "\n");
        for (int i : list) {
            sb.append(i + " ");
        }
        System.out.println(sb);
    }
}
