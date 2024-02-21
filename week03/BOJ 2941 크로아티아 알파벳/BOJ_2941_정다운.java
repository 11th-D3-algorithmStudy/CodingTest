package week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2941_정다운 {
	public static void main(String[] args) throws Exception {	
		// 크로아티아 알파벳 8개를 큐에 넣고
		// 입력받은 String에 큐 요소가 포함되어 있는지 확인(contains)
		// true일때마다 cnt++
		// & 확인한 크로아티아 알파벳을 input에서 제거(replaceFirst로 구분자로 변경해두기 -> 공백이 아닌 구분자로 변경하는 이유 : ex. nljj)
		// 남은 일반 알파벳 개수(= 문자열 길이) cnt에 더해준다

		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		
		// 큐에 입력
		Queue<String> caQueue = new LinkedList<>();
		caQueue.offer("c=");
		caQueue.offer("c-");
		caQueue.offer("dz="); // z=보다 먼저 큐에 들어와있어서 ddz=z= 케이스를 통과한듯........
		caQueue.offer("d-");
		caQueue.offer("lj");
		caQueue.offer("nj");
		caQueue.offer("s=");
		caQueue.offer("z=");
		
		// 알파벳 개수
		int cnt = 0;
		
		// 크로아티아 알파벳 카운트 & 제거
		while (!caQueue.isEmpty()) {
			// 포함되어 있다면 cnt++ & 구분자로 변환 
			if (input.contains(caQueue.peek())) {
				cnt++;
				input = input.replaceFirst(caQueue.peek(), ",");
			} else { // 아니면 해당 알파벳 큐에서 버리기
				caQueue.poll();
			}
		}
		
		// 구분자 공백으로 치환
		input = input.replace(",", "");
		
		// 남은 일반 알파벳 개수를 cnt에 더한다
		cnt += input.length();
		
		
		System.out.println(cnt);
	}
}

