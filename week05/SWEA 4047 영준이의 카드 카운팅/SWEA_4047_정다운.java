package 스터디;

import java.io.BufferedReader;
import java.io.InputStreamReader;
 
public class SWEA_4047_정다운 {
    public static void main(String[] args) throws Exception {
        // 입력받은 카드 정보 길이를 3으로 나눈 몫 = 제공된 카드 장 수
        // 4종류의 카드 배열을 만든 후 (int)
        // 정보를 입력받으면 각 카드번호에 해당하는 idx에 ++ 해준다
        // 정보 입력 후 배열에서 cnt가 0인 요소의 개수 = 부족한 카드 개수
        // + cnt가 2가 되는 카드가 있다면 바로 ERROR 출력
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
         
        tc:
        for (int t=1; t<=T; t++) {
            String input = br.readLine();
            int len = input.length();
            int cards = len / 3; // 카드 장수
             
            int[] sArr = new int[13];
            int[] dArr = new int[13];
            int[] hArr = new int[13];
            int[] cArr = new int[13];
             
            int stringIdx = 0;
            for (int i=0; i<cards; i++) {
                char pattern = input.charAt(stringIdx);
                int cardNum = Integer.parseInt( input.substring(stringIdx+1, stringIdx+3) );
                 
                switch (pattern) {
                    case 'S': {
                        sArr[cardNum-1]++;
                        if (sArr[cardNum-1] == 2) { // 중복되는 카드가 있다
                            sb.append("#"+t+" ERROR"+"\n");
                            continue tc; // 테스트케이스 탈출
                        }
                        break;
                    }
                    case 'D': {
                        dArr[cardNum-1]++;
                        if (dArr[cardNum-1] == 2) { 
                            sb.append("#"+t+" ERROR"+"\n");
                            continue tc;
                        }
                        break;
                    }
                    case 'H': {
                        hArr[cardNum-1]++;
                        if (hArr[cardNum-1] == 2) { 
                            sb.append("#"+t+" ERROR"+"\n");
                            continue tc;
                        }
                        break;
                    }
                    case 'C': {
                        cArr[cardNum-1]++;
                        if (cArr[cardNum-1] == 2) { 
                            sb.append("#"+t+" ERROR"+"\n");
                            continue tc;
                        }
                        break;
                    }
                }
                 
                stringIdx += 3;
            }
             
            // 무늬별 부족한 카드 개수 확인
            int S = 0;
            int D = 0;
            int H = 0;
            int C = 0;
             
            for (int i=0; i<13; i++) {
                if (sArr[i] == 0) S++;
                if (dArr[i] == 0) D++;
                if (hArr[i] == 0) H++;
                if (cArr[i] == 0) C++;
            }
             
            sb.append("#"+t+" "+S+" "+D+" "+H+" "+C+"\n");
             
        }
         
        System.out.println(sb);
    }
}
