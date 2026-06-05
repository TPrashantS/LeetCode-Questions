class Solution {

    static class Node {
        long cnt;
        long sum;

        Node(long cnt, long sum) {
            this.cnt = cnt;
            this.sum = sum;
        }
    }

    private String num;
    private Node[][][][][][] dp;
    private boolean[][][][][][] vis;

    private Node dfs(int pos, int tight, int started,
                     int lenState, int prev1, int prev2) {

        if (pos == num.length()) {
            return new Node(1, 0);
        }

        if (vis[pos][tight][started][lenState][prev1][prev2]) {
            return dp[pos][tight][started][lenState][prev1][prev2];
        }

        vis[pos][tight][started][lenState][prev1][prev2] = true;

        long totalCnt = 0;
        long totalSum = 0;

        int limit = tight == 1 ? num.charAt(pos) - '0' : 9;

        for (int digit = 0; digit <= limit; digit++) {

            int newTight = (tight == 1 && digit == limit) ? 1 : 0;

            if (started == 0 && digit == 0) {
                Node child = dfs(pos + 1, newTight, 0, 0, 10, 10);

                totalCnt += child.cnt;
                totalSum += child.sum;
            }
            else if (started == 0) {
                Node child = dfs(pos + 1, newTight, 1, 1, digit, 10);

                totalCnt += child.cnt;
                totalSum += child.sum;
            }
            else if (lenState == 1) {
                Node child = dfs(pos + 1, newTight, 1, 2, prev1, digit);

                totalCnt += child.cnt;
                totalSum += child.sum;
            }
            else {
                int add = 0;

                if ((prev2 > prev1 && prev2 > digit) ||
                    (prev2 < prev1 && prev2 < digit)) {
                    add = 1;
                }

                Node child = dfs(pos + 1, newTight, 1, 2, prev2, digit);

                totalCnt += child.cnt;
                totalSum += child.sum + child.cnt * add;
            }
        }

        return dp[pos][tight][started][lenState][prev1][prev2] =
                new Node(totalCnt, totalSum);
    }

    private long solve(long n) {
        if (n < 0) return 0;

        num = String.valueOf(n);

        int len = num.length();

        dp = new Node[len + 1][2][2][3][11][11];
        vis = new boolean[len + 1][2][2][3][11][11];

        return dfs(0, 1, 0, 0, 10, 10).sum;
    }

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }
}