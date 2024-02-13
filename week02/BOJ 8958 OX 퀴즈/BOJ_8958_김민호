import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_8958_김민호 {
    // OOOXXXOOOX 이렇게 한 줄을 내부의 인덱스로 비교해야한다?
    // 한 줄로 받아서 전에 값 비교하면 되겟다~
    // 굳이 배열로 할 필요가 있나? -> 메모리 낭비?
    // String 한 줄로 charAt으로 한 개씩 비교하자

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String s;
        // 점수를 더할 sum 변수 선언
        int sum;
        // 점수에 1점씩 올릴 score 변수 선언
        int score;

        int n = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            s = st.nextToken();
            sum = 0;
            score = 1;
            // 한 줄의 String을 받고, 1부터 반복문을 돌려서
            // 반복문의 전 값이 'O'인지 'X' 인지 판단 후에
            // 'O'면 score++ 해주면서 sum에 점수 더하기
            // 'X'면 score 1로 초기화
            for (int j = 1; j <= s.length(); j++) {
                if (s.charAt(j - 1) == 'O') {
                    sum += score++;
                } else {
                    score = 1;
                }
            }
            System.out.println(sum);
        }
    }
}
