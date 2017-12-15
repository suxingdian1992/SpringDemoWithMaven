package com.sxd.springmvc.app16c.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author suxin
 * 2017年12月15日
 * prjName:com.sxd.springmvc pakName:com.sxd.springmvc.app16b.controller
 * TODO
 * 实现借口，处理录入时候跳转到表单的操作
 * 2017年12月15日
 */
public class InputProductController implements Controller {
	
	public String handleRequest(HttpServletRequest request, 
			HttpServletResponse response) {
		return "/WEB-INF/jsp/app16c/ProductForm.jsp";
	}
}