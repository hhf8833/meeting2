package com.hhf;

import java.util.List;

/**
 * @author HP
 * 25. K 个一组翻转链表
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 进阶：
 *
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 */
public class No_25_reverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head,end=head,pre=null,next=null;
        while (end!=null){

            for (int i = 0; i < k-1; i++) {
                if (end == null){
                    break;
                }
                end=end.next;
            }
            if (end ==null){
                break;
            }
            next =end.next;
            end.next=null;
            fanzhuan(start);
            start.next =next;
            if (pre !=null){
                pre.next=end;
            }else {
                head = end;
            }
            pre =start;
            end = next;
            start =next;
        }
        return head;
    }

    private void fanzhuan(ListNode start) {
        if (start==null ||start.next==null){
            return;
        }
        fanzhuan(start.next);
        start.next.next=start;

    }
}
