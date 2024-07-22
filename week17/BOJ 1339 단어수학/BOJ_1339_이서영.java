package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BOJ_1339_이서영 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[][] og = new char[N][8];
		for (int i = 0; i < N; i++) {
			char[] letters = br.readLine().toCharArray();
			int fill = 0;
			for (int j = (8 - letters.length); j < 8; j++) {
				og[i][j] = letters[fill++];
			}
		}
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(og[i]));
		}

		int start = 58;
		char dominant = '\u0000';
		for (int i = 0; i < 8; i++) {
			Map<Character, Integer> freq = new HashMap<>();
			int max = 0;
			for (int j = 0; j < N; j++) {
				char letter = og[j][i];
//				System.out.println(letter);
				if (letter != '\u0000' || (letter >= 48 && letter <= 57)) {
					if (letter == dominant) {
						og[j][i] = (char) start;
					} else {
						freq.put(letter, freq.getOrDefault(letter, 0) + 1);
						max = Math.max(max, freq.get((letter)));
					}

				}
			}
			List<Character> tie = new ArrayList<>();
			for (char c : freq.keySet()) {
				if (freq.get(c) == max) {
					tie.add(c);
				}
			}
			System.out.println(freq);
			System.out.println(tie);
			for (int x = i + 1; x < 8; x++) {
				int tempMax = 0;
				for (int y = 0; y < N; y++) {
					if (tie.contains(og[y][x])) {
						freq.put(og[y][x], freq.getOrDefault(og[y][x], 0) + 1);
						tempMax = Math.max(tempMax, freq.get(og[y][x]));
					}
				}
				for (char candidate : tie) {
					if (freq.get(candidate) < max) {
						tie.remove(candidate);
					}
				}
				if (tie.size() == 1) {
					break;
				}
			}
			if (tie.size() > 0) {
				dominant = tie.get(0);
				System.out.println("dom: " + dominant);
				start--;
				for (int l = 0; l < N; l++) {
					if (og[l][i] == dominant) {
						og[l][i] = (char) start;
					}
				}
			}

		}
		
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(og[i]));
		}
		int total = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < N; j++) {
				char letter = og[j][i];
				if (letter >= 48 && letter <= 57) {
					total += (letter - 48) * Math.pow(10, 7 - i);
				}
			}
		}
		System.out.println(total);
	}
}
