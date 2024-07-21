for(int i = 0; i < dp.length; i++) {
			
	dp[i] = 1;	// 최소 개수인 1로 초기화 
			
	/*
	 * i번째 전봇대를 기준으로 이전의 전봇대들의
	 * 전선을 연결하기 위한 탐색
	 * 즉, i번째 전봇대에 연결된 B전봇대는
	 * 탐색할 j번째 전봇대에 연결된 B전봇대보다 값이 커야함 
	 */
	for(int j = 0; j < i; j++) {
		if(wire[i][1] > wire[j][1]) {
			dp[i] = Math.max(dp[i], dp[j] + 1);
		}
	}
}
