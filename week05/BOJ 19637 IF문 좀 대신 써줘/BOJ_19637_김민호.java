package zz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_19637_김민호 {
    // 처음에 생각한 것은 숫자가 순서대로 들어온다고 생각해서
    // 배열에 칭호와 경계값을 넣어서
    // ex) String[N][2] 배열 생성
    //     WEAK 10000
    //     NORMAL 1000000
    // 숫자가 리미트 넘어가면 행을 하나씩 늘려주면서 했는데
    // 순서대로 오는게 아니어서 틀렸다.

    // 오랫동안 고민하다 모르겠어서 gpt에 코드 올리고 시간복잡도
    // 개선할 방법 검색하니까 이진탐색을 말해서
    // 이진탐색으로 고쳐서 냈다.
    static String[][] map;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        // 입력값 넣어주기
        st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new String[N][];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().split(" ");
        }

        sb = new StringBuilder();

        // left 와 rigth를 나누고
        for (int j = 0; j < M; j++) {
            int power = Integer.parseInt(br.readLine().trim());
            int left = 0;
            int right = N - 1;

            // right가 left보다 클 경우에만
            while (left < right) {

                // 중간값을 계속 갱신하면서
                int mid = (left + right) / 2;

                // power가 경계값들보다 크면
                // left를 mid + 1 해주고
                // power가 작으면 right 를 mid로 갱신
                if (Integer.parseInt(map[mid][1]) < power) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            // 탐색완료되면 left의 칭호를 sb에 append
            sb.append(map[left][0]).append("\n");
        }
        System.out.println(sb.toString());
    }
}