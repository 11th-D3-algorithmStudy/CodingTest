package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_1406_장현영 {
	    public static void main(String args[]) throws IOException {
	        // 처음에 string 형태로 cursor ++ -- 하면서 수정했지만 
	    	// 역시 어마어마한 n값으로 인해 시간초과 발생
	    	// 구글링을 통해서 stack을 2개 사용해서 cursor를 대처하는 방법을 보고 문제 풀음.
	    	// 풀이시간 40분
	    	// 최종적으로는 stack 삽입, 삭제가 O(1)임을 이용해서 시간초과를 대처한 문제
	    	
	    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        Stack<Character> stack1 = new Stack<>();
	        Stack<Character> stack2 = new Stack<>();
	        
	        StringBuilder sb = new StringBuilder();
	 
	        String str = br.readLine();
	        for(int i = 0; i < str.length(); i++)
	            stack1.push(str.charAt(i));
	 
	        int cnt = Integer.parseInt(br.readLine());
	        
	        // cursor가 stack1,2의 top 역할을 함
	        // 기존 stack1에 다 담겨져 있는 상태(커서는 맨 뒤)
	        for(int i = 0; i < cnt; i++){
	            String command = br.readLine();
	            switch(command.charAt(0)){
	                case 'L': // 커서 한 칸 옮김
	                    if(!stack1.isEmpty()) 
	                    	stack2.push(stack1.pop());
	                    break;
	                case 'D': // 커서 오른쪽 한 칸 옮김
	                    if(!stack2.isEmpty()) 
	                    	stack1.push(stack2.pop());
	                    break;
	                case 'B': // 왼쪽에 있는 문자 삭제
	                    if(!stack1.isEmpty()) 
	                    	stack1.pop();
	                    break;
	                case 'P': // 문자 추가
	                    stack1.push(command.charAt(2));
	            }
	        }
	        
	        // command 후 최종적으로 stack2에 옮겨서
	        // stack1 + stack2 형태로 출력하기 위해
	        // stack2 위로 stack1 차례로 pop한거 넣음
	        while(!stack1.isEmpty())
	            stack2.push(stack1.pop());
	        
	        // 여기서 stack2에 담겨져있는거 싹 다 받아서 출력하기
	        while(!stack2.isEmpty())
	            sb.append(stack2.pop());
	 
	        System.out.print(sb);
	    }
	}
