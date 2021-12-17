package com.hhf.filterdata.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author HP
 * "cid":{"/":"QmeeS4cQmULKW4xmR1yRQKPTvsQNFhkYXVjWWrFQ6EKSYs"}
 */
public class Cid {
//    @JSONField(name="aa")
    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Cid{" +
                "id='" + id + '\'' +
                '}';
    }
}
