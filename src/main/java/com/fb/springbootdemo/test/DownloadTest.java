package com.fb.springbootdemo.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/download")
@RestController
public class DownloadTest {
	private static final String PREFIX = "D:\\tmp\\download\\";

	@RequestMapping("/{fileName}")
	public Map<String, Object> down(HttpServletRequest req, HttpServletResponse resp,
			@PathVariable("fileName") String fileName) {
		String uri = req.getRequestURI();
		String realFileName = uri.substring(uri.indexOf(fileName));
		File file = new File(PREFIX + realFileName);
		if (!file.exists()) {
			throw new RuntimeException("文件不存在");
		}
		try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
				BufferedOutputStream os = new BufferedOutputStream(resp.getOutputStream());) {
			resp.reset();
			resp.setHeader("Content-Disposition", "attachment;Filename=" + realFileName);
//			resp.setContentType("application/octet-stream");
//			resp.setContentType("application/byte-stream");

			byte[] bys = new byte[2048];
			int len;
			while ((len = is.read(bys)) > -1) {
				os.write(bys, 0, len);
			}
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
