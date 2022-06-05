package com.stylefeng.guns;

import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.rest.OrderApplication;
import com.stylefeng.guns.rest.common.util.FTPUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class GunsRestApplicationTests {

	@Autowired
	private FTPUtil ftpUtil;
	@Test
	public void contextLoads() {
		String fileStrByAddress = ftpUtil.getFileStrByAddress("seats/123214.json");
		System.out.println(fileStrByAddress);
		//将其转为json
		JSONObject jsonObject = JSONObject.parseObject(fileStrByAddress);
		System.out.println(jsonObject.toString());
		String ids = jsonObject.get("ids").toString();
	}

}
