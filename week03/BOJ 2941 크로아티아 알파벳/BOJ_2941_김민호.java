import java.util.Scanner;

public class BOJ_2941_김민호 {
    // 해당 문자가 몇 개 인지를 알기 위해서
    // 해당 문자들을 검사하는 문장에서 한 글자로 치환해서
    // 치환을 완료한 문장의 길이를 반환해줬다.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        // 치환이 필요한 문자 배열 저장
        String[] sArr = {"c=" , "c-", "dz=", "d-", "lj", "nj", "s=", "z="};

        // 치환이 필요한 문자를 기준으로 순회하면서
        // 검사하는 문장에 해당 문자가 있다면, 1글자로 치환
        for (String str : sArr) {
            if (s.contains(str)) {
                s = s.replace(str,"1");
            }
        }
        // 치환이 완료된 문장의 길이를 반환
        System.out.println(s.length());
    }
}