import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_7490_이상휘 {

    static char[] chars;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(br.readLine());
            chars = new char[tmp-1];
            numbers = new int[tmp];
            for (int j = 0; j < tmp; j++) {
                numbers[j] = j + 1;
            }
            combi3(numbers, chars, 0);
            System.out.println();
        }
    }

    private static void combi3(int[] numbers, char[] chars, int idx) {
        if (idx == chars.length) {
            if (calculate(numbers, chars) == 0) {
                printNums(numbers, chars);
            }
            return;
        }

        chars[idx] = ' ';
        combi3(numbers, chars, idx + 1);
        chars[idx] = '+';
        combi3(numbers, chars, idx + 1);
        chars[idx] = '-';
        combi3(numbers, chars, idx + 1);
    }

    private static int calculate(int[] numbers, char[] chars) {
        List<Integer> tempNums = new ArrayList<>();
        int currentNum = numbers[0];

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                currentNum = currentNum * 10 + numbers[i + 1];
            } else {
                tempNums.add(currentNum);
                tempNums.add(chars[i] == '+' ? 1 : -1);
                currentNum = numbers[i + 1];
            }
        }
        tempNums.add(currentNum);

        int result = tempNums.get(0);
        for (int i = 1; i < tempNums.size(); i += 2) {
            result += tempNums.get(i) * tempNums.get(i + 1);
        }
        return result;
    }

    private static void printNums(int[] numbers, char[] chars) {

        StringBuilder sb = new StringBuilder();
        sb.append(numbers[0]);

        for (int i = 0; i < chars.length; i++) {

            sb.append(chars[i]);
            sb.append(numbers[i + 1]);
        }
        System.out.println(sb);
    }
}