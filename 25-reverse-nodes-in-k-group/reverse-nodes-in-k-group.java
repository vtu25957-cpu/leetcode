class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroup = dummy;
        ListNode curr = head;

        int count = 0;
        while (curr != null) {
            count++;
            curr = curr.next;
        }

        curr = head;

        while (count >= k) {
            ListNode groupPrev = prevGroup;
            ListNode groupEnd = curr;

            // Reverse k nodes
            ListNode prev = null;
            for (int i = 0; i < k; i++) {
                ListNode nextNode = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextNode;
            }

            // Connect reversed group
            groupPrev.next = prev;
            groupEnd.next = curr;

            // Move prevGroup to end of reversed group
            prevGroup = groupEnd;
            count -= k;
        }

        return dummy.next;
    }
}

    