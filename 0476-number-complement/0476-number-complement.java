class Solution {
    public int findComplement(int num) {
        if(num == 0) return 1;
        int bits = 0;
        int temp = num;
        while(temp > 0){
            bits++;
            temp >>= 1;
        }
        int mask = (1 << bits) - 1;
        return mask ^ num;
    }
}