package com.sxd.springmvc.app16c.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author suxin
 * 2017��12��15��
 * prjName:com.sxd.springmvc pakName:com.sxd.springmvc.app16b.controller
 * TODO
 * ʵ�ֽ�ڣ�����¼��ʱ����ת�����Ĳ���
 * 2017��12��15��
 */
public class InputProductController implements Controller {
	
	public String handleRequest(HttpServletRequest request, 
			HttpServletResponse response) {
		return "/WEB-INF/jsp/app16c/ProductForm.jsp";
	}
}