package com.hhf.filterdata.controller;

import com.alibaba.fastjson.JSON;
import com.hhf.filterdata.bean.BlockRecord;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author HP
 * 使用阿里巴巴的产品
 * {"cid":{"/":"QmaSFy7qxVwXaodfPExpiE4vxrLp6kvCP8BfKLKpKcZH8y"},"event":"BlockService.BlockFetched",
 * "requestId":"HP6F964MI2D04ONNEABMD08L3C======","system":"blockservice","time":"2021-11-25T08:37:20.409185335Z"}
 */
public class JSONTest {

    public static void main(String[] args) throws IOException {
       //System.out.println(args.length);
        BlockRecord blockRecord = null;
        File file;
        FileInputStream fs;
        BufferedReader br;
        Set<String> res = new HashSet<String>();
        String str = null;
        for (int i = 0; i < args.length; i++) {
            System.out.println("第"+(i+1)+"个文件");
            file= new File(args[i]);
            fs = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(fs));
            int count =0;
            while ((str=br.readLine())!=null){

                //System.out.println(str);
                blockRecord = JSON.parseObject(str.replaceFirst("/","id"), BlockRecord.class);
                res.add(blockRecord.getCid().getId());
                if (count == 0){
                    System.out.println(blockRecord.getTime());
                    count = 1;
                }
            }
            System.out.println(blockRecord.getTime());

            fs.close();
            br.close();
        }
       // FileInputStream fs = new FileInputStream("C:\\Users\\HP\\Desktop\\fetch1.txt");
        System.out.println(res.size());
        //System.out.println(res);

    }
}

