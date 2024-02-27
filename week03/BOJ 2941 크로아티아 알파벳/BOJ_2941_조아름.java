import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2941_조아름 {
	
	/* 맨 처음에는 이 문제를 어떻게 풀어야 하나 고민이 되었는데
	글자를 쌍으로 추출해서 비교해야 하는 만큼 String 배열을 만들어 주고
	그 값과 BufferedReader로부터 받은 값을 비교해가면서 
	글자의 개수를 세 준다.
	이때 String 배열에 있는 글자를 하나의 숫자로 카운트하기 위해 하나의 다른 문자로 변경해준다.
	*/
	public static void main(String[] args) throws IOException {
		String [] alp = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=","z="};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String text = br.readLine();
		
		for(int i=0;i<alp.length;i++) {
			if(text.contains(alp[i])) {
				text = text.replace(alp[i], "#");
			}
		}
		
		System.out.println(text.length());
	
	}

}
