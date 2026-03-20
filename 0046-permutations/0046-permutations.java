class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums, ans, new ArrayList<>(), used);
        return ans;
    }

    private void backtrack(int[] nums, List<List<Integer>> ans, 
                           List<Integer> curr, boolean[] used) {

        if (curr.size() == nums.length) {
            ans.add(new ArrayList<>(curr));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                curr.add(nums[i]);
                used[i] = true;
                backtrack(nums, ans, curr, used);
                curr.remove(curr.size() - 1);
                used[i] = false;
            }
        }
    }
}