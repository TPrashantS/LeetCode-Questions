class Solution {
    public int mirrorDistance(int n) {
        int orig=n;
        int reverse = 0;
        while(n>0){
            reverse = reverse*10 + (n%10);
            n /=10;
        }
        return Math.abs(orig-reverse);
    }
}