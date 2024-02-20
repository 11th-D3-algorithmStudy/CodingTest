import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2941_조아름 {

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
