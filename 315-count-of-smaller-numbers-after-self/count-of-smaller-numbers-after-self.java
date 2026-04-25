import java.util.*;

class Solution {
    int[] counts;

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        this.counts = new int[n];
        int[] indices = new int[n];
        
        // Initialize indices to track original positions
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        mergeSort(nums, indices, 0, n - 1);

        List<Integer> result = new ArrayList<>();
        for (int count : counts) {
            result.add(count);
        }
        return result;
    }

    private void mergeSort(int[] nums, int[] indices, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        mergeSort(nums, indices, left, mid);
        mergeSort(nums, indices, mid + 1, right);
        merge(nums, indices, left, mid, right);
    }

    private void merge(int[] nums, int[] indices, int left, int mid, int right) {
        int[] tempIndices = new int[right - left + 1];
        int i = left;      // Pointer for left half
        int j = mid + 1;   // Pointer for right half
        int k = 0;         // Pointer for temp array
        int rightCount = 0; // Count of elements from right half smaller than current left element

        while (i <= mid && j <= right) {
            if (nums[indices[j]] < nums[indices[i]]) {
                // Element on the right is smaller
                rightCount++;
                tempIndices[k++] = indices[j++];
            } else {
                // Element on the left is smaller or equal
                // Add the count of all smaller right elements seen so far
                counts[indices[i]] += rightCount;
                tempIndices[k++] = indices[i++];
            }
        }

        // Clean up remaining elements in left half
        while (i <= mid) {
            counts[indices[i]] += rightCount;
            tempIndices[k++] = indices[i++];
        }

        // Clean up remaining elements in right half
        while (j <= right) {
            tempIndices[k++] = indices[j++];
        }

        // Copy sorted indices back to original indices array
        System.arraycopy(tempIndices, 0, indices, left, tempIndices.length);
    }
}