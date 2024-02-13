import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_24511_김민호 {
    // 문제 이름이 큐스택이라서
    // 1. 큐와 스택 선언
    // -> 생각해보니 스택은 선언을 안 해줘도 됨.. 코드 작성 중 중지
    // 2. 큐만 선언
    // -> 시간 초과.. -> 입력값 최대 개수가 10억개...
    // 3. 배열 선언
    // -> 배열로 선언해서 분기 조건 설정해서 코드 작성
    // -> 31% 에서 계속 실패..
    // 4. substring() 메서드 삭제
    // -> 초기 컨셉은 큐의 개수만큼 StringBuilder에 담아서
    // input값들의 개수보다 큐의 개수가 많다면, substring으로 StringBuilder의 값을
    // input값들의 개수 * 2만큼 잘라줬었다.. -> (값 + " ")로 값과 빈칸을 같이 sb에 담아줬었기 때문에
    // 계속 틀려서 생각해보니 위의 방법은 inputValue가 한 자리 숫자 일 때만 고려한 거였어서
    // substring() 메서드를 삭제하고 중간에 StringBuilder에 값들을 담을 때, 처리하는 것으로 변경

    // 제목에 낚이지말고, 조건을 잘 보자...
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;

        // 모든 값들을 배열에 담아줬다.
        // 세 번째, 다섯 번째 줄의 최대 입력 값 개수가 10억개다 보니 for 문 한 개만 전체로 돌려도 시간 초과..)
        int n = Integer.parseInt(br.readLine());
        String[] qOrStk = br.readLine().split(" ");
        String[] initValues = br.readLine().split(" ");
        int inputValueNum = Integer.parseInt(br.readLine());
        String[] inputValues = br.readLine().split(" ");
        int qNum = 0;

        sb = new StringBuilder();

        // 큐마다 큐의 개수를 세주고, StringBuilder에 값을 바로 담아줬다.
        // 큐의 개수가 입력값의 개수 보다 커지면 StringBuilder에 값을 담는 것을 멈춘다.
        for (int i = n - 1; i >= 0; i--) {
            if (qOrStk[i].equals("0")) {
                qNum++;
                if (qNum > inputValueNum) {
                    break;
                }
                sb.append(initValues[i] + " ");
            }
        }

        // 큐의 개수가 입력값의 개수보다 크거나 같으면, sb 그대로 출력
        // 큐의 개수가 입력값의 개수보다 작으면 현재까지 sb에다가
        // 입력값의 개수에서 큐의 개수를 빼준만큼 부족한 거니
        // 해당 개수만큼 반복문을 돌려 sb에 추가 후 출력
        if (qNum >= inputValueNum) {
            System.out.println(sb.toString().trim());
        } else {
            for (int i = 0; i < inputValueNum - qNum; i++) {
                sb.append(inputValues[i] + " ");
            }
            System.out.println(sb.toString().trim());
        }
    }
}
