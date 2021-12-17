package leetcode;

/**
 * @author HP
 * 142. 环形链表 II
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 *
 * 说明：不允许修改给定的链表。
 *
 * 进阶：
 *
 * 你是否可以使用 O(1) 空间解决此题？
 *
 * 利用快慢指针 a为环入口的前一个，b为环长度
 * fast=slow+nb ,fast =2slow =>
 * 利用两次相遇去求
 * 第一次相遇 2s=s+nb 即 s=nb，f=2nb 得到nb
 * 第二次相遇  要让f从head开始走到相遇处就是a的大小-1，相遇处即为环入口
 */
public class No_142_detectCycle {
    public ListNode detectCycle(ListNode head) {
        ListNode f= head,s = head;
        while (true){
            if (f==null || f.next==null){
                return null;
            }
            s=s.next;
            f=f.next.next;
            if (f==s){
                break;
            }
        }
        f=head;
        while (s!=f){
            s=s.next;
            f=f.next;
        }
        return f;
    }
}
