package zz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7272_김민호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testNum = Integer.parseInt(br.readLine().trim());
        String[] sArr;

        // 구멍이 한 개인 알파벳 스트링으로 저장
        for (int i = 1; i < testNum + 1; i++) {
            boolean isSame = true;
            String circle1 = "ADOPQR";
            st = new StringTokenizer(br.readLine().trim());

            sArr = new String[2];
            sArr[0] = st.nextToken();
            sArr[1] = st.nextToken();

            // 비교 대상의 길이가 다르면 break;
            for (int j = 0; j < sArr[0].length(); j++) {
                if (sArr[0].length() != sArr[1].length()) {
                    isSame = false;
                    break;
                }

                char c1 = sArr[0].charAt(j);
                char c2 = sArr[1].charAt(j);


                // B의 위치가 다르면 break;
                if ((c1 == 'B' && c2 != 'B') || (c2 == 'B' && c1 != 'B')) {
                    isSame = false;
                    break;
                }

                // 구멍 한개의 알파벳의 위치가 다르면 break;
                if ((circle1.contains(c1 + "") && !circle1.contains(c2 + "")) || (circle1.contains(c2 + "") && !circle1.contains(c1 + ""))) {
                    isSame = false;
                    break;
                }
            }

            // isSame이 트루면 Same 출력
            // 아니면 DIFF 출력
            if (isSame) {
                System.out.println("#" + i + " SAME");
            } else {
                System.out.println("#" + i + " DIFF");
            }
        }
    }
}