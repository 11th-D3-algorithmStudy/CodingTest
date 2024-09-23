class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String digits = "";
        while (n > 0){
            digits = String.valueOf(n % k) + digits;
            n /= k;
        }
        String[] nums = digits.split("0");
        
        for (String num : nums){
            if (num.isBlank()){
                continue;
            }
            if (isPrime(Long.parseLong(num))){
                answer++;
            }
        }
        return answer;
    }
    
    public boolean isPrime(long n) {
        if (n <= 1){
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++){
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }
}
