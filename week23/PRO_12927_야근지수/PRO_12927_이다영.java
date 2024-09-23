//10,12,13 실패
import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
          Arrays.sort(works);
        //뒤집기
        for (int i = 0; i < works.length / 2; i++) {
            int temp = works[i];
            works[i] = works[works.length - 1 - i];
            works[works.length - 1 - i] = temp;
        }
        System.out.println(Arrays.toString(works));

        int range = 0;
        while(range<works.length-1 && n>=0){
//            System.out.println(" 큰 while" +range+" "+n);
            // range가 다음 숫자와 같아질때까지 사이 값들 앞에서부터 감소시킴
            while( works[range]> works[range+1]){
                for(int i = 0;i<=range;i++){
                    n--;
                    if(n< 0 ) break;
                    works[i]--;
                    System.out.println(Arrays.toString(works));
                    System.out.println(range+" "+n);
                }
                if(n< 0 ) break;

            }
            if(works[range]==works[range+1]){
                System.out.println("같아짐");
                range++;

            }

        }
        if(n>0){

        for(int i = 0;i<=range;i++){
            works[i]--;
            n--;
            System.out.println("밖" +Arrays.toString(works));
            System.out.println(range+" "+n);
            if(n< 0 ) break;
        }
        }
        System.out.println(Arrays.toString(works));

        long answer = 0;
        for(int i : works){
            answer += i*i;
        }
        System.out.println(answer);
        return answer;}
    
}
