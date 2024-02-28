import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2108_성민기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] number = new int[N];
		for(int i=0; i<N; i++) {
			number[i] = Integer.parseInt(br.readLine());
		}
		Avg(number);
		median(number);
		bin(number);
		range(number);
	}
	
	public static void Avg(int[] arr) {
		double sum = 0;
		for(int i=0; i<arr.length; i++) {
			sum += arr[i];
		}
		double avg = sum / arr.length;
		double result = Math.round(avg);
		System.out.println((int) result);
	}
	
	public static void median(int[] arr) {
		Arrays.sort(arr);
		System.out.println(arr[arr.length/2]);
	}
	
	public static void bin(int[] arr) {
		Arrays.sort(arr);
		int two = 0;
		Stack<Integer> list = new Stack<>();
		for(int i=0; i<arr.length-1; i++) {
			if(arr[i]==arr[i+1]) {
				two++;
				list.add(arr[i]);
				i++;
			}
		}
		if(two==0) System.out.println(arr[1]);
		else if(list.size() > 1) {
			System.out.println(list.get(1));
		} else {
			
		}
	}
	
	public static void range(int[] arr) {
		Arrays.sort(arr);
		int min = arr[0];
		int max = arr[arr.length-1];
		int result = max - min;
		System.out.println(result);
	}
}
