package cn.com.newloading.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import cn.com.newloading.utils.ImgCodeUtil;
@Controller
public class KeyCodeController {

	@RequestMapping("/getKeyCode.action")
	public void getKeyCode(HttpServletRequest req,HttpServletResponse response, String time) throws IOException {
		String code = ImgCodeUtil.getFourCode();
		//把验证码存入session
		// 用户存入session
		req.getSession().setAttribute("sessionKeyCode", code);
		System.out.println("验证码:"+code);
		BufferedImage newImgCode = ImgCodeUtil.newImgCode(110, 30, code);
		ImageIO.write(newImgCode, "jpg", response.getOutputStream()); // 将图片验证码输出
	}
	
}
