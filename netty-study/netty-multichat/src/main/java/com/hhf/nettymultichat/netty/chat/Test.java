package com.hhf.nettymultichat.netty.chat;

import org.json.JSONObject;

public class Test {
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOnce("SignatureAlgorithm", 1);
        jsonObject.putOnce("SubjectIP", 2);
        jsonObject.putOnce("SubjectName", 2);
        jsonObject.putOnce("IssuerIP", 33);
        jsonObject.putOnce("IssuerName", "sad");
        jsonObject.putOnce("Timestamp","asd");
        jsonObject.putOnce("PublicKey", "asde3r");
        jsonObject.putOnce("Signature", "gfdsgc");
        String string = jsonObject.toString();
        System.out.println(string);
    }
}
