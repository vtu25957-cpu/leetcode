public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        // Step 1: Detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                // Step 2: Find cycle start
                ListNode pointer = head;
                
                while (pointer != slow) {
                    pointer = pointer.next;
                    slow = slow.next;
                }
                
                return pointer;
            }
        }

        return null; // No cycle
    }
}
