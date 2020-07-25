package cn.com.newloading.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

@Controller
public class ImageController {

	@Value(value = "${resources_path}")
	private String rootPath;//资源文件绝对地址目录

	/**
	 * 上传图片
	 * 
	 * @return
	 */
	@RequestMapping("/imageUpload")
	@ResponseBody
	public JSONObject image(HttpServletRequest request, @Param("file") MultipartFile file) throws IOException {
//		String rootPath = "D:/WorkSpace2/zhongchou/src/main/resources/static/assets/img/news/";
		File file2 = new File(rootPath);
		rootPath = file2.getCanonicalPath();
		rootPath = rootPath + "\\";
		System.out.println(rootPath);
		// 将文件放于项目部署路径下的upload文件夹下
		File targetFile = new File(rootPath);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();// 磁盘工厂
		factory.setSizeThreshold(2048 * 1024);// 缓存 2M
		factory.setRepository(targetFile);// 缓存路径
		ServletFileUpload upload = new ServletFileUpload(factory);

		String path = request.getSession().getServletContext().getRealPath("assets/img/");
		System.out.println(path);

		String uuid = String.valueOf(UUID.randomUUID());// uuid
		String fileNames = file.getOriginalFilename();// 文件名
		fileNames = new String(fileNames.getBytes("UTF-8"));
		String extendName = fileNames.substring(fileNames.lastIndexOf("."));
		String fileName = rootPath + uuid + extendName;

		try {
			uploadFile(file.getBytes(), path, uuid + extendName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		byte[] content = file.getBytes();
		FileUtils.writeByteArrayToFile(new File(fileName), content);

		JSONObject json = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		json.put("code", 0);// 0表示成功，1失败
		json.put("msg", "上传成功");// 提示消息
		int index = fileName.indexOf("\\assets");
		fileName = ".." + fileName.substring(index, fileName.length());
		map.put("src", "../assets/img/" + uuid + extendName);// 图片url
		map.put("title", fileNames);// 名称，这个会显示在输入框里
		json.put("data", map);
		return json;
	}

	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
		File targetFile = new File(filePath);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		FileOutputStream out = new FileOutputStream(filePath + fileName);
		out.write(file);
		out.flush();
		out.close();
	}
}
