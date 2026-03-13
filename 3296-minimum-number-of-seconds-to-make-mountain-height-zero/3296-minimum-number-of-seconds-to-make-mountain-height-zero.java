class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long low = 0;
        long high = (long) Arrays.stream(workerTimes).max().getAsInt()
                    * mountainHeight * (mountainHeight + 1) / 2;
        while (low < high) {
            long mid = (low + high) / 2;
            if (canFinish(mid, mountainHeight, workerTimes)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private boolean canFinish(long T, int mountainHeight, int[] workerTimes) {
        long totalReduction = 0;
        for (int wt : workerTimes) {
            totalReduction += maxReduction(wt, T);
            if (totalReduction >= mountainHeight) return true;
        }
        return false;
    }

    private long maxReduction(int workerTime, long T) {
        double ratio = (double) T / workerTime;
        long x = (long) ((-1 + Math.sqrt(1 + 8 * ratio)) / 2);
        while ((long) workerTime * (x + 1) * (x + 2) / 2 <= T) x++;
        while (x > 0 && (long) workerTime * x * (x + 1) / 2 > T) x--;

        return x;
    }
}