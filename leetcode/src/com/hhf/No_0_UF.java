package com.hhf;

/**
 * @author HP
 * 并查集
 */
public class No_0_UF {
    //表示当前并查集的连通分量
    private int count;
    //表示父节点集合 例如  parents[i] ==j  i的父节点是j
    private  int[] parents;
    //当前根节点的所在的树的大小，为了平衡树高度才设置的这项
    private int[] size;

    public No_0_UF(int n){
        this.count = n;
        parents=new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            //初始化时所有节点都为一个连通分量，自旋
            parents[i]=i;
            size[i] =1;
        }
    }
    public void Union(int p,int q){
        //连接的时候要看他们所在的两个树的数量，大的去连小的
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP ==rootQ){
            return;
        }
        // 小树接到大树下面，较平衡
        if (size[rootP]>size[rootQ]){
            parents[rootP] = parents[rootQ];
            size[rootQ]+=size[rootP];
        }else {
            parents[rootQ] = parents[rootP];
            size[rootP]+=size[rootQ];
        }
        count--;
    }
    //去找i的根节点
    public int find(int i){
/*        while(parents[i] !=i){
            i = parents[i];
        }*/
        //进行压缩，使得树的高度不超过3
        while(parents[i] !=i){
//            i的parent指向 parents[parents[i]],即将i跳过parent[i]指向了parents[parents[i]]
            parents[i] = parents[parents[i]];
            //i变为parents[parents[i]]的位置，最后parents[parents[i]]下面有两个子节点  曾经的i和parent[i]
            i = parents[i];
        }
        return i;
    }
    //连接的话只要两个节点的根一样就说明他们是连接
    public boolean conneced(int p,int q){
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;

    }

}
