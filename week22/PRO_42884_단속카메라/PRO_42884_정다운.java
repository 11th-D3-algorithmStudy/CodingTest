import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        // 진출 지점 정렬 후에 겹치는 지역에 카메라 설치
        // 첫번째 차량 진출 지점에 설치 후
        // 카메라 설치 구역 저장해서 들고다니면서 다음 차량 단속 여부 확인
        Arrays.sort(routes, (o1, o2) -> {
            return o1[1]-o2[1]; 
        });
        
        int len = routes.length;
        int answer = 0;
        
        // 첫번째 차량 진출 지점 설치
        int cmr = routes[0][1];
        answer++;
        
        for (int i=1; i<len; i++) {
            // 카메라 설치 구역이 현재 차량 이동 경로와 겹치지 않는다?
            if (cmr < routes[i][0] || cmr > routes[i][1]) {
                // 현재 차량의 진출지점에 설치
                cmr = routes[i][1];
                answer++;
            }
        }
        
        return answer;
    }
}
