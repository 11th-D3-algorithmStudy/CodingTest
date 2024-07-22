import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
    public class BOJ_1339_김민호 {
        public static void main(String[] args) throws NumberFormatException, IOException {
           BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            int N = Integer.parseInt(br.readLine());
            int[] alpha = new int[26];

            for(int i = 0; i < N; i++) {
                String str = br.readLine();
                for(int j = 0; j < str.length(); j++) {
                    alpha[str.charAt(j) - 'A'] += (Math.pow(10, str.length() - j - 1));
                }
            }

            Arrays.sort(alpha);
            int num = 9;
            int answer = 0;

            for(int i = 25; i >= 0; i--) {
                if(alpha[i] == 0) {
                    continue;
                }
                answer += (alpha[i] * num);
                num--;
            }
            System.out.println(answer);
        }
    }