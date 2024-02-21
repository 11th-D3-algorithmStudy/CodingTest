import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2798_김민호 {
    // 문제의 설명이 3개의 카드의 숫자를 더해서
    // 목표하는 값에 가장 가까운 값을 구하는 것이므로
    // for 문 3개를 돌려서, 브루트포스로 문제를 해결함
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());
        int cardNum = Integer.parseInt(st.nextToken());
        int objectNum = Integer.parseInt(st.nextToken());
        int[] cards = new int[cardNum];
        int sum = 0;
        int max = Integer.MIN_VALUE;

        // 카드의 번호들을 cards 배열에 입력
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < cardNum; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        // for문 3개를 중복이 나오지 않게 더해준 뒤
        // 목표로하는 값보다 작거나 같으면서
        // 그 중 가장 큰 값을 저장
        for (int i = 0; i < cardNum - 2; i++) {
            for (int j = i + 1; j < cardNum - 1; j++) {
                for (int k = j + 1; k < cardNum; k++) {
                    sum += cards[i] + cards[j] + cards[k];

                    if (sum > max && sum <= objectNum) {
                        max = sum;
                    }
                    sum = 0;
                }
            }
        }
        System.out.println(max);
    }
}