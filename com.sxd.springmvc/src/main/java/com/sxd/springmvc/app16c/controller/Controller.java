package com.sxd.springmvc.app16c.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author suxin
 * 2017年12月15日
 * prjName:com.sxd.springmvc pakName:com.sxd.springmvc.app16b.controller
 * TODO
 * app16a中的控制器会随着业务逻辑的的复杂而越变越复杂，这时候我们应该
 * 将业务逻辑代码提取到独立的被称为controller类中，当前接口的实现类
 * 通过该方法访问到当前请求的request对象和response对象
 * 2017年12月15日
 */
public interface Controller {
	String handleRequest(HttpServletRequest request, 
			HttpServletResponse response);
}
