package app24a.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import app24a.domain.Login;

@Controller

public class ResourceController {

	private static final Log logger = LogFactory.getLog(ResourceController.class);

	@RequestMapping(value = "/login")
	public String login(@ModelAttribute Login login, HttpSession session, Model model) {
		model.addAttribute("login", new Login());
		if ("paul".equals(login.getUserName()) && "secret".equals(login.getPassword())) {
			session.setAttribute("loggedIn", Boolean.TRUE);
			return "Main";
		} else {
			return "LoginForm";
		}
	}

	@RequestMapping(value = "/resource_download")
	public String downloadResource(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		if (session == null || session.getAttribute("loggedIn") == null) {
			return "LoginForm";
		}
		String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/data");
		File file = new File(dataDirectory, "secret.pdf");
		if (file.exists()) {
			//文件类型请去http://www.iana.org/assignments/media-types
			//如果不知道是什么类型，并且希望浏览器能一直显示另存为对话框，则设置为application/octet-stream
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=secret.pdf");
			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			// if using Java 7, use try-with-resources
			try {
				//新建文件输入流和缓冲输入流
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				//从响应中获取输出流
				OutputStream os = response.getOutputStream();
				//每次读取1024byte输出，直到i为小于零为止，表示文件发送完毕
				int i = bis.read(buffer);
				while (i != -1) {
					os.write(buffer, 0, i);
					i = bis.read(buffer);
				}
			} catch (IOException ex) {
				// do something,
				// probably forward to an Error page
			} finally {
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException e) {
					}
				}
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
					}
				}
			}
		}
		return null;
	}

}
