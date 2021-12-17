package leetcode;


/**
 * @author HP
 * 148. 排序链表
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 * 进阶：
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *
 * 时间为nlogn正常情况下归并排序的空间复杂度不是O(n)，但是因为是链表，所以复杂度为常数
 */
public class No_148_sortList {
    public ListNode sortList(ListNode head) {
        if (head.next== null ||head == null){
            return head;
        }
//        采用快慢指针的方法求出中间节点位置
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast!=null && fast.next != null){
            fast =fast.next.next;
            slow=slow.next;
        }
        ListNode temp = slow.next;
        slow.next =null;
        ListNode left = sortList(head);
        ListNode right = sortList(temp);
        ListNode newHead = new ListNode(0);
        //新建一个指针用于移动节点
        ListNode p =newHead;
        while (left != null &&right != null){
            if (left.val < right.val){
                p.next=left;
                left=left.next;
            }else {
                p.next=right;
                right=right.next;
            }
            p=p.next;
        }
//      这一步不能忘，因为奇数的左右长度不一样
        p.next = left!=null ? left:right;
        return newHead.next;
    }
}
