package leetcode.No_146_LRUCache;

import sun.dc.pr.PRError;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 146. LRU 缓存机制
 *         运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 *         实现 LRUCache 类：
 *
 *         LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 *         int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 *         void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
 *         当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *         进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *          方法一：
 *          使用LinkedHashMap，其有两种方式对map进行排序，一种是按插入顺序排序，即调用newNode()中的linkNodeLast()完成
 *          一种是按LRU机制排序（访问过的元素会放在链尾部，调用afterNodeAccess()，
 *                            插入的时候，会判断map容量，但是源码中removeEldestEntry(first)一直返回的false，即不会删除，所以题中要重写这个方法）
 *         方法二：
 *         双向链表加hash表 ，hash表用来存储元素的key值，以及元素，
 *                          双向链表也存key，存元素加上虚拟的表头表尾，每次将元素放在头的时候是新生成一个对象放进去，之前的直接不要了
 *                          最新的在链头，老的在链尾
 */

/*该方法通过使用hashmap的已存在方法进行重写，复用
class LRUCache extends LinkedHashMap {
    private  int capacity;
    public LRUCache(int capacity) {
        super(capacity,0.75F,true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return (int) super.getOrDefault(key, -1);
    }

    public int put(int key, int value) {
        return (int) super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > capacity;
    }
}
*/

public class LRUCache {
    class DLinkedNode{
        private DLinkedNode next;
        private DLinkedNode pre;
        private int value;
        private int key;
        public DLinkedNode(){}
        public DLinkedNode(int key ,int value){
            this.key=key;
            this.value=value;
        }
    }
    private Map<Integer,DLinkedNode> cache = new HashMap<>();
    private DLinkedNode head;
    private DLinkedNode tail;
    private int capacity;
    private int size;
    public LRUCache(int capacity) {
        //初始化链表的长度为零
        this.size = 0;
        this.capacity=capacity;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next=tail;
        tail.pre = head;
    }
    //get 需要看链中是否存在 ，才把其移到链头
    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);

        if (node == null){
            //表中不存在该节点
            DLinkedNode newnNode = new DLinkedNode(key,value);
            cache.put(key,newnNode);
            addToHead(newnNode);
            ++size;
            if (size > capacity){
                //越界了 要删除最久未使用的节点和hash表
                DLinkedNode oldNode = tail.pre;
                System.out.println("oldnode:"+oldNode);
                removeTail(tail.pre);
                cache.remove(oldNode.key);
                --size;
            }
        }else {
            //表中存在该节点，不管值是否一样，直接修改调到头
            node.value = value;
            moveToHead(node);
        }
    }
    public void moveToHead(DLinkedNode node){
        //删除节点
        removeNode(node);
        //将节点添加到链尾
        addToHead(node);
    }
    public void removeNode(DLinkedNode node){
        node.pre.next=node.next;
        node.next.pre = node.pre;
    }
    public  void addToHead(DLinkedNode node){
        node.next=head.next;
        node.pre=head;
        head.next.pre = node;
        head.next=node;
    }
    public void removeTail(DLinkedNode oldNode){
        oldNode.pre.next = tail;
        tail.pre = oldNode.pre;

    }

}
