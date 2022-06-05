package com.stylefeng.guns;

import com.stylefeng.guns.rest.AliPayApplication;
import com.stylefeng.guns.rest.common.util.FTPUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AliPayApplication.class)
public class GunsRestApplicationTests {

	@Autowired
	private FTPUtil ftpUtil;
	@Test
	public void contextLoads() {

		File file = new File("C:\\Users\\HP\\Desktop\\新建文件夹\\qr-399d5e34f188424fbfed09b91cc0d13d.png");
		boolean b = ftpUtil.uploadFile("qr-399d5e34f188424fbfed09b91cc0d13d.png",file);
		System.out.println("上传是否成功 = "+b);
	}

}
