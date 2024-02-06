import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1296_성민기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String yeondu = br.readLine();
		char[] name = yeondu.toCharArray();
		char[] loveName = {'L', 'O', 'V', 'E'};
		// 아 이름도 LOVE랑 비교하는거네
		
		int N = Integer.parseInt(br.readLine());
		
		int max = Integer.MIN_VALUE;
		String[] hubo = new String[N];
		int[] huboJumsu = new int[N];
		// 이름별 확률의 값을 넣기 위한 배열이므로 크기는 N이 맞다.
		
		// N만큼 반복
		for(int i=0; i<N; i++) {
			// LOVE 문자와 같을 경우 개수를 카운트 하기 위한 배열
			int[] count = new int[4];
			
			int sum = 1;
			// 팀명 입력
			String team = br.readLine();
			char[] teamName = team.toCharArray();
			
			// 팀명 입력한 것은 String 배열 team에 입력
			hubo[i] = team;
			
			// 이름과 LOVE와도 같은지 확인하는 거였어
			for(int j=0; j<4; j++) {
				for(int k=0; k<name.length; k++) {
					// 같으면 그 문자 카운트++;
					if(loveName[j] == name[k]) {
						count[j]++;
					}
				}
			}
			// LOVE와 팀명의 문자 비교
			for(int j=0; j<4; j++) {
				for(int k=0; k<teamName.length; k++) {
					// 같으면 그 문자 카운트++;
					if(loveName[j] == teamName[k]) {
						count[j]++;
					}
				}
			}
			
			// sum *= count[r] + count[c]를 반복하면서
			// (L+O) × (L+V) × (L+E) × (O+V) × (O+E) × (V+E) 조건을 맞춤
			for(int r=0; r<count.length-1; r++) {
				for(int c=r+1; c<count.length; c++) {
					sum *= (count[r] + count[c]);
				}
			}
			// sum은 구했다
			sum = sum % 100;
			// mod 100을 한 값을 후보점수 배열에 넣는다
			huboJumsu[i] = sum;
		}
		
		int maxIdx = 0;
		for(int i=0; i<N; i++) {
			// 후보점수 중 최대점수를 구하고
			// 최고점수일 때의 인덱스 maxIdx를 구한다
			if(max < huboJumsu[i]) {
				max = huboJumsu[i];
				maxIdx = i;
			}
		}
		// 만약 sum이 같다면?
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				// max값과 같은 i값과 j값 비교
				if(huboJumsu[i] == huboJumsu[j] && max == huboJumsu[j]
						&& max == huboJumsu[i]) {
					// 사전 순을 위해 compareTo 메소드를 활용
					// 0일 경우 -> 같은 문자라는 뜻이므로 앞의 인덱스 값을 나오도록
					// 음수 일경우 -> i 인덱스 값이 더 사전적으로 앞선다는 의미
					if(hubo[i].compareTo(hubo[j]) <= 0) {
						maxIdx = i;
					
					// 양수의 경우 -> j 인덱스 값이 사전적으로 앞선다는 의미
					} else if(hubo[i].compareTo(hubo[j]) > 0) {
						maxIdx = j;
					}
				}
			}
		}
		// 최종 출력
		System.out.println(hubo[maxIdx]);	
	}
}
