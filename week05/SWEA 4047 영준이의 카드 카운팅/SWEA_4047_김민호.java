package zz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_4047_김민호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;

        int[][] havingCards;
        char[] cardOrder = {'S', 'D', 'H', 'C'};

        int testNum = Integer.parseInt(br.readLine().trim());

        for (int i = 1; i < testNum + 1; i++) {
            boolean isSameCard = false;
            String initCards = br.readLine().trim();
            havingCards = new int[4][14];
            String s = "";


            // 소유하고 있는 카드를 입력받은 후
            // 3개씩 끊어가면서 해당 카드의 문양이 일치할 시
            // 해당 카드의 번호 인덱스를 증가 시킴
            for (int j = 1; j < initCards.length() + 1; j++) {
                s += initCards.charAt(j - 1);
                if (j % 3 == 0) {
                    for (int k = 0; k < 4; k++) {
                        if (s.charAt(0) == cardOrder[k]) {
                            String num = "" + s.charAt(1) + s.charAt(2);
                            havingCards[k][Integer.parseInt(num)]++;
                        }
                    }
                    s = "";
                }
            }
            sb = new StringBuilder();
            sb.append("#").append(i).append(" ");

            // 카드 문양 별로
            // 해당 인덱스의 값이 0 이면 필요한 카드 수 1개 ++
            // 해당 인덱스의 값이 1 이상이면 중복 카드이므로 break
            for (int j = 0; j < 4; j++) {
                int count = 0;
                for (int k = 1; k < 14; k++) {
                    if (havingCards[j][k] == 0) {
                        count++;
                    } else if (havingCards[j][k] > 1) {
                        isSameCard = true;
                        break;
                    }
                }
                sb.append(count).append(" ");
            }

            if (isSameCard) {
                System.out.println("#" + i + " ERROR");
            } else {
                System.out.println(sb.toString().trim());
            }
        }
    }
}