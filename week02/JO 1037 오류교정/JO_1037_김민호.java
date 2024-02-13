import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO_1037_김민호 {
    // 처음 문제를 읽을 때는 그냥 2중 배열을 받아서 특이점만 찾으면 될 줄 알았는데
    // Corrupt 조건을 만족시키기 위해서 조금 더 생각이 필요했던 것 같다.
    // 처음에 테스트 케이스 하나가 틀리길래 뭔가 생각해보니
    // 처음(0, 0)에서 특이점으로 탐색이 되버리면, 뒤는 탐색이 안 된다는 점이었다.
    // 그래서 생각해보니 Row와 Col에서 합이 홀수 인 것이 2개 이상이면 무조건 Corrupt라는 것을
    // 생각해서 아래 분기 조건 중 (oddRow > 1 || oddCol > 1) 해당 조건이 추가되었다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 합이 홀수인 row, col 인덱스 찾기 위한 변수 선언
        int oddX = 0;
        int oddY = 0;
        //  row, col의 1 개수 세기 위한 변수 선언
        int cntX = 0;
        int cntY = 0;
        // row, col의 합이 홀 수인 라인 전체 수 구하기
        int oddRow = 0;
        int oddCol = 0;

        int n = Integer.parseInt(br.readLine().trim());
        int[][] matrix = new int[n][n];

        // matrix 에 검사가 필요한 내용 초기화
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                // matrix의 row 부분에서 1인 숫자 개수 구하기
                if (matrix[i][j] == 1) {
                    cntX++;
                }
                // matrix의 col 부분에서 1인 숫자 개수 구하기
                if (matrix[j][i] == 1) {
                    cntY++;
                }
            }
            // matrix의 row의 1의 숫자가 홀수면 oddRow 의 개수 증가
            if (cntX % 2 != 0) {
                oddRow++;
                // row의 1의 숫자가 홀수인 부분의 x 인덱스 구하기
                if (oddX == 0) {
                    oddX = i + 1;
                }
            }
            // matrix의 col의 1의 숫자가 홀수면 oddRow 의 개수 증가
            if (cntY % 2 != 0) {
                oddCol++;
                // col의 1의 숫자가 홀수인 부분의 x 인덱스 구하기
                if (oddY == 0) {
                    oddY = i + 1;
                }
            }
            cntX = 0;
            cntY = 0;
        }

        // oddX 와 oddY 의 변수가 처음 초기화한 0 이면 1의 개수가 홀 수인
        // row와 col이 없으므로 OK 출력
        if (oddX == 0 && oddY == 0) {
            System.out.println("OK");
        } else {
            // oddX와 oddY 중 한 개만 초기화가 되었던가
            // oddRow와 oddCol의 수가 2 이상이면 하나만 고쳐서는
            // 패리티 검사법을 통과할 수 없으므로
            // Corrupt 출력
            if (oddX == 0 || oddY == 0 || oddRow > 1 || oddCol > 1) {
                System.out.println("Corrupt");
            } else {
                // 위의 경우가 아닌, oddRow와 oddCol이 1인 상황에서는
                // 해당 부분의 인덱스만 고치면 패리티 검사법이 통과되므로
                // oddX와 oddY 출력
                System.out.println("Change bit (" + oddX + "," + oddY + ")");
            }
        }
    }
}
