import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_7465_이서영 {
	/*
	 * 그래프 문제인듯? dfs로 풀었습니다
	 * 한 사람의 이웃을 타고타고 들어가다 보면 결국 무리의 끝까지 가게되니까
	 * 타고 들어간 이웃을 체크해놓고 그 이웃을 검색할 차례가 됐을 때 체크여부확인
	 * 체크 안되어있으면 재귀검색하고 무리숫자 증가시키는 방식(전의 무리에서 한번도 검색되지 않았음 -> 연결되지 않았다는 뜻이므로)
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());
		for (int i = 0; i < cases; i++) {
			String[] infos = br.readLine().split(" ");
			int num = Integer.parseInt(infos[0]);
			int relations = Integer.parseInt(infos[1]);
			boolean[] checked = new boolean[num + 1]; // 사람들 체크 여부
			Person[] persons = new Person[num + 1]; // 사람들
			for (int j = 1; j < num + 1; j++) {
				persons[j] = new Person(j); // 사람객체로 초기화
			}
			for (int k = 0; k < relations; k++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int p1 = Integer.parseInt(st.nextToken());
				int p2 = Integer.parseInt(st.nextToken());
				persons[p1].addNeighbor(persons[p2]); // 각자 이웃으로 추가 (나중에 체크 여부 확인해서 중복은 안 검색할거임)
				persons[p2].addNeighbor(persons[p1]);
			}
			
			int count = 0;
			for (int l = 1; l < persons.length; l++) {
				count += search(persons, l, checked); // 해당 사람을 검색한 전적이 없으면 새로운 무리의 일원이라는 뜻이므로 카운트 증가
			}
			
			System.out.println("#" + (i+1) + " " + count);
		}
	}
	
	static int search(Person[] persons, int index, boolean[] checked) {
		if (!checked[index]) {
			Person person = persons[index];
			checked[index] = true;
			for (Person p : person.getNeighbors()) {
				if (!checked[p.getNumber()]) { // 타고 들어온 이웃은 다시 가지 않음
					search(persons, p.getNumber(), checked);
				}
			}
			return 1;
		}
		return 0;
	}
}

class Person {
	int number;
	List<Person> neighbors = new ArrayList<>();
	
	public Person(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void addNeighbor(Person person) {
		neighbors.add(person);
	}
	
	public List<Person> getNeighbors(){
		return neighbors;
	}
}

