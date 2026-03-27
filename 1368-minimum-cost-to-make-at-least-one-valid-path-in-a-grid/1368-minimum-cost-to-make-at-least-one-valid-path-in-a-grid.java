class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dist = new int[m][n];
        for (int[] row : dist){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        Deque<int[]> dq = new ArrayDeque<>();
        dq.addFirst(new int[]{0, 0});
        dist[0][0] = 0;
        int[][] dir = {{0, 1},{0, -1},{1, 0},{-1, 0}};
        while (!dq.isEmpty()) {
            int[] curr = dq.pollFirst();
            int currRow = curr[0];
            int currCol = curr[1];
            for (int x = 0; x < 4; x++) {
                int nextRow = currRow + dir[x][0];
                int nextCol = currCol + dir[x][1];
                if (nextRow < 0 || nextCol < 0 || nextRow >= m || nextCol >= n){
                    continue;
                }
                int cost = (grid[currRow][currCol] == x + 1) ? 0 : 1;
                if (dist[currRow][currCol] + cost < dist[nextRow][nextCol]) {
                    dist[nextRow][nextCol] = dist[currRow][currCol] + cost;
                    if (cost == 0) {
                        dq.addFirst(new int[]{nextRow, nextCol});
                    } else {
                        dq.addLast(new int[]{nextRow, nextCol});
                    }
                }
            }
        }
        return dist[m - 1][n - 1];
    }
}