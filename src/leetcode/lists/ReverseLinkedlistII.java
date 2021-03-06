package leetcode.lists;

/*Reverse a linked list from position m to n. Do it in-place and in one-pass.*/
public class ReverseLinkedlistII {

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public class ReturnData {
		ListNode lastNode;
		ListNode nextNode;

		public ReturnData(ListNode x, ListNode y) {
			this.lastNode = x;
			this.nextNode = y;
		}

	}

	public ListNode reverseBetween(ListNode head, int m, int n) {
		if (head == null)
			return null;
		int count = 0;
		ListNode p = head;
		ListNode dummy = new ListNode(0);
		if (n < m || n == m)
			return head;

		if (m == 1) {
			p = dummy;
			p.next = head;
		}

		while (count < (m - 2)) { // Find parent of first node.
			count++;
			p = p.next;
		}
		// Reverse the portion m-n
		ReturnData re = reverse(p, n - m);

		// Set the portion outer pointers.
		ListNode c = p.next;
		p.next = re.lastNode;
		c.next = re.nextNode;

		if (m == 1)
			return dummy.next;
		return head;
	}

	public ReturnData reverse(ListNode i, int index) {
		int count = 0;
		ListNode prev = i;
		ListNode cur = i.next;
		while (count <= index && cur != null) {
			ListNode next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
			count++;
		}
		return new ReturnData(prev, cur);

	}

	// More readable code.
	public ListNode reverseBetweenI(ListNode head, int m, int n) {
		if (head == null || head.next == null || m == n)
			return head;

		// Dummy.
		ListNode dummy = new ListNode(0);
		dummy.next = head;

		ListNode cur = null;
		ListNode prev = dummy;

		// Find the beginning.
		for (int i = 0; i < m - 1; i++) {
			prev = prev.next;
		}
		cur = prev.next; // The first

		// Reverse.
		ListNode start = cur.next;
		ListNode prevre = cur;

		for (int i = 0; i < n - m; i++) {
			ListNode next = start.next;
			start.next = prevre;
			prevre = start;
			start = next;
		}
		
		// Set outer pointers.
		prev.next = prevre;
		cur.next = start;

		return dummy.next;
	}

}
