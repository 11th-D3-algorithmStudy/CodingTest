package week13;

import java.io.*;

// 포기...
public class BOJ_7490_0만들기_이규빈_FAIL { 
    static char[] option = {' ', '+', '-'};  // ASCII 순서
    static int N;
    static char[] sel;  // 중복순열 결과 저장할 배열
    static char[] res;  // 숫자와 연산자를 조합한 결과를 저장할 배열 (ex. ['1', '+', '2', '-', '3'])
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
        	
            N = Integer.parseInt(br.readLine());
            sel = new char[N - 1];
            res = new char[2 * N - 1];

            for (int i = 0; i < 2 * N - 1; i += 2) {
                res[i] = (char)(i / 2 + 1 + '0');
                // res의 "짝수 인덱스"에 숫자 저장 (int -> char 변환방법 주의)
            }

            perm(0);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    // sidx : sel 배열의 인덱스
    static void perm(int sidx) {
        // base case
        // - 중복순열 완성된 경우, res의 "홀수 인덱스"에 넣어주기
        if (sidx == N - 1) {
            for (int i = 0; i < N - 1; i++) {
                res[2 * i + 1] = sel[i];
            }

            int num = calculate(res);

            if (num == 0) {
                for (int i = 0; i < 2 * N - 1; i++) {
                    sb.append(res[i]);
                }
                sb.append("\n");
            }

            return;
        }

        // recursive case
        for (int i = 0; i < 3; i++) {
            sel[sidx] = option[i];
            perm(sidx + 1);  // 중복순열이므로, 방문처리 등으로 중복을 회피할 필요 없다.
        }
    }

	static int calculate(char[] res) {
		int num = 0;
		int tmp = 0;
		int digit = 1;
		
		for (int i = 2 * N - 2; i >= 1; i--) {
			if (res[i] == ' ') {
				tmp += res[i + 1] * digit;
				digit *= 10;
			}
			else if (res[i] == '+') {
				if (digit == 1) {
					num += res[i + 1];
				} else {
					num += tmp;
					tmp = 0;  // 초기화
				}
			}
			else if (res[i] == '-') {
				if (digit == 1) {
					num -= res[i + 1];
				} else {
					num -= tmp;
					tmp = 0;  // 초기화
				}
			}
		}
		
		return num;
	}


}
