package 스터디;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class SWEA_7272_정다운 {
     
    public static void main(String[] args) throws Exception {
        // 문자 개수는 판단 가능
         
        // 경근이가 구별할 수 있는 문자 종류 3가지
        // CEFGHIJKLMNSTUVWXYZ
        // ADOPQR
        // B
         
        // 입력받은 문자 하나하나가 어느 그룹에 속하는지 표시해두고 최종 비교
        String group1 = "CEFGHIJKLMNSTUVWXYZ";
        String group2 = "ADOPQR";
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
         
        tc:
        for (int t=1; t<=T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
             
            String str1 = st.nextToken();
            String str2 = st.nextToken();
            int L = str1.length(); // 문자열 길이
             
            if (str1.length() != str2.length()) { // 문자열 길이 다른것부터 거른다
                sb.append("#"+t+" DIFF\n");
                continue tc;
            }
             
            // 최종 비교할 문자열
            String res1 = "";
            String res2 = "";
            for (int i=0; i<L; i++) {
                // 문자열 1
                String X = str1.charAt(i)+"";
                if (group1.contains(X)) {
                    res1 += "1";
                } else if (group2.contains(X)) {
                    res1 += "2";
                } else if (X.equals("B")) {
                    res1 += "B";
                }
                 
                // 문자열 2 
                String Y = str2.charAt(i)+"";
                if (group1.contains(Y)) {
                    res2 += "1";
                } else if (group2.contains(Y)) {
                    res2 += "2";
                } else if (Y.equals("B")) {
                    res2 += "B";
                }
            }
             
            if (res1.equals(res2)) {
                sb.append("#"+t+" SAME\n");
            } else {
                sb.append("#"+t+" DIFF\n");
            }
        }
        System.out.println(sb);
    }
}