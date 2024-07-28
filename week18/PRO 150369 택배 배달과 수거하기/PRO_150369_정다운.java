class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int currDelivery = 0; // 트럭에 있는 배달 상자
        int currPickup = 0; // 트럭에 있는 수거 상자

        // 먼 집부터 역순으로 처리
        for (int i = n - 1; i >= 0; i--) {
            if (deliveries[i] > 0 || pickups[i] > 0) { // 지금 집에 배달 or 수거할 상자 있음
                int trips = 0; // 왕복 횟수
                while (currDelivery < deliveries[i] || currPickup < pickups[i]) { // 트럭에 있는 상자가 지금 집에 필요한 상자보다 부족하다?
                    trips++; // 왕복 횟수 ++
                    // 트럭에 최대 용량 만큼 상자 추가
                    currDelivery += cap; 
                    currPickup += cap;
                }
                // 처리 후 남은 상자 수
                currDelivery -= deliveries[i];
                currPickup -= pickups[i];
                // 왕복 거리 계산
                answer += (i + 1) * 2 * trips;
            }
        }

        return answer;
    }
}
