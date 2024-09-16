import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        
        // 진출 지점을 기준으로 오름차순 정렬하기
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));
        
        int answer = 1;
        int currentCam = routes[0][1];
        
        for (int i = 1; i < routes.length; i++) {
            // "직전에 설치된 카메라 좌표 < 해당 요소의 진입 지점"인 경우, 새로운 카메라 설치 필요
            if (currentCam < routes[i][0]) {
                answer++;
                currentCam = routes[i][1];
                // "현재 카메라를 설치하는 최적 지점(=진출 지점)" 
                // = "전체 카메라 개수를 최소로 하는 지점"이므로, 그리디 문제임을 알 수 있다.
            }
        }
        return answer;
        
    }
}
