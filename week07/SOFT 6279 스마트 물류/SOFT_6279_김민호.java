import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SOFT_6279_김민호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int count = 0;
        boolean[] visited = new boolean[n];
        String s = br.readLine().trim();
        boolean find = false;

        //입력값 받기

        // 전체 길이만큼 반복물 돌리기
        for (int i = 0; i < n; i++) {
            // 해당 인덱스에 P가 있을 때
            // P 기준 왼쪽부터 H가 있는지 확인
            // 왼쪽에 있을 시 find 를 트루로 바꾸고
            // 오른쪽 검사는 패스
            if (s.charAt(i) == 'P') {
                for (int j = k; j >= 0; j--) {
                    if (i >= j) {
                        if (s.charAt(i - j) == 'H' && !visited[i - j]) {
                            visited[i - j] = true;
                            count++;
                            find = true;
                            break;
                        }
                    }
                }
                // 왼쪽에서 H를 못 찾았을 시
                // 오른쪽에 H가 있는지 검사
                if (!find) {
                    for (int j = 1; j < k + 1; j++) {
                        if (i + j < n) {
                            if (s.charAt(i + j) == 'H' && !visited[i + j]) {
                                visited[i + j] = true;
                                count++;
                                break;
                            }
                        }
                    }
                }
                find = false;
            }
        }
        System.out.println(count);
    }
}