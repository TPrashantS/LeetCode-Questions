// class NumArray {
//     int arr[];
//     public NumArray(int[] nums) {
//         arr = nums;
//     }
//     public int sumRange(int left, int right) {
//         int sum = 0;
//         for(int i=left; i<=right; i++){
//             sum += arr[i]; 
//         }
//         return sum;
//     }
// }

// class NumArray {
//     int prefix[];
//     public NumArray(int[] nums) {
//         prefix = new int[nums.length];
//         prefix[0] = nums[0];
//         for(int i = 1; i<nums.length; i++){
//             prefix[i] = prefix[i-1] + nums[i];
//         }
//     }
//     public int sumRange(int left, int right) {
//         if(left==0){
//             return prefix[right];
//         } else {
//             int sum = prefix[right] - prefix[left-1];
//             return sum;
//         }
//     }
// }

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */

// Using Segment Tree

class NumArray {
    int[] segTree;
    int n;
    public NumArray(int[] nums) {
        n = nums.length;
        segTree = new int[4 * n];
        build(nums, 0, 0, n - 1);
    }

    void build(int[] nums, int i, int start, int end) {
        if(start == end) {
            segTree[i] = nums[start];
            return;
        }
        int mid = (start + end) / 2;
        build(nums, 2*i + 1, start, mid);
        build(nums, 2*i + 2, mid + 1, end);
        segTree[i] = segTree[2*i + 1] + segTree[2*i + 2];
    }

    public int sumRange(int left, int right) {
        return query(0, 0, n - 1, left, right);
    }

    int query(int i, int start, int end, int left, int right) {
        if(right < start || left > end) 
            return 0;
        if(left <= start && end <= right) 
            return segTree[i];
        int mid = (start + end) / 2;
        int l = query(2*i + 1, start, mid, left, right);
        int r = query(2*i + 2, mid + 1, end, left, right);
        return l + r;
    }
}