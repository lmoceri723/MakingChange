/**
 * The class Making Change solves a classic problem:
 * given a set of coins, how many ways can you make change for a target amount?
 *
 * @author Zach Blick
 * @author Landon Moceri
 */

public class MakingChange {
    /**
     * TODO: Complete this function, countWays(), to return the number of ways to make change
     *  for any given total with any given set of coins.
     */
    private static long[][] results;

    public static long countWays(int target, int[] coins) {
        // Initialize the results array to avoid duplicate recursive calls
        results = new long[target + 1][coins.length + 1];

        // Fill the array in with -1 to indicate uninitialized values
        for (int i = 0; i <= target; i++) {
            for (int j = 0; j <= coins.length; j++) {
                results[i][j] = -1;
            }
        }

        // Call the recursive helper function
        //return findWaysMemoization(target, coins, coins.length);
        return findWaysTabulation(target, coins);
    }

    // Recursively finds the number of ways to make change given a target and a set of coins
    private static long findWaysMemoization(int target, int[] coins, int coinIndex) {
        // Base cases
        if (target == 0) {
            return 1;
        }
        // If target is negative or no coins left, return 0
        if (target < 0 || coinIndex == 0) {
            return 0;
        }
        // Check if the result is already computed
        if (results[target][coinIndex] != -1) {
            return results[target][coinIndex];
        }

        // Store the result in the array
        // Create two recursive calls, one doing nothing and omitting the last coin
        // The other subtracting the last coin from the target and not omitting it
        // This processes all paths of the tree
        results[target][coinIndex] = findWaysMemoization(target - coins[coinIndex - 1], coins, coinIndex) +
                findWaysMemoization(target, coins, coinIndex - 1);

        // Return the result
        return results[target][coinIndex];
    }

    private static long findWaysTabulation(int target, int[] coins) {
        // Initialize the tabulation array
        long[] waysToMake = new long[target + 1];
        waysToMake[0] = 1;

        // Iterate over each coin
        for (int coin : coins) {
            // Look at every subsequent index of the array until the end
            for (int j = 0; j <= target - coin; j++) {
                // Add the number of ways to make change for the current index
                // To the current index + the coin value
                waysToMake[j + coin] += waysToMake[j];
            }
        }

        // Return the number of ways to make change for the target
        return waysToMake[target];
    }
}

