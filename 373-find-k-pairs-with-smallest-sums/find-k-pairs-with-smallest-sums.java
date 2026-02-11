import java.util.*;

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return result;

        // Min-heap: stores [i, j] indices with sum nums1[i] + nums2[j]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> (nums1[a[0]] + nums2[a[1]]) - (nums1[b[0]] + nums2[b[1]])
        );

        // Initialize heap with first element in nums2 paired with each element in nums1
        for (int i = 0; i < nums1.length && i < k; i++) {
            minHeap.offer(new int[]{i, 0});
        }

        // Extract k smallest pairs
        while (!minHeap.isEmpty() && result.size() < k) {
            int[] indices = minHeap.poll();
            int i = indices[0], j = indices[1];

            // Convert int pair to List<Integer>
            result.add(Arrays.asList(nums1[i], nums2[j]));

            // If there is a next element in nums2 for current i, add it to heap
            if (j + 1 < nums2.length) {
                minHeap.offer(new int[]{i, j + 1});
            }
        }

        return result;
    }
}
