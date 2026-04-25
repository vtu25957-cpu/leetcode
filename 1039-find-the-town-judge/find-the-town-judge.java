class Solution {
    public int findJudge(int n, int[][] trust) {
        // We use n + 1 to align with 1-based indexing of people
        int[] trustScores = new int[n + 1];
        
        for (int[] relation : trust) {
            int personWhoTrusts = relation[0];
            int personTrusted = relation[1];
            
            // If you trust someone, your score decreases
            trustScores[personWhoTrusts]--;
            
            // If someone trusts you, your score increases
            trustScores[personTrusted]++;
        }
        
        // The judge must have a score of exactly n - 1
        for (int i = 1; i <= n; i++) {
            if (trustScores[i] == n - 1) {
                return i;
            }
        }
        
        return -1;
    }
}