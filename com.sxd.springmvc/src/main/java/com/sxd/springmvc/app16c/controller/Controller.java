package com.sxd.springmvc.app16c.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author suxin
 * 2017��12��15��
 * prjName:com.sxd.springmvc pakName:com.sxd.springmvc.app16b.controller
 * TODO
 * app16a�еĿ�����������ҵ���߼��ĵĸ��Ӷ�Խ��Խ���ӣ���ʱ������Ӧ��
 * ��ҵ���߼�������ȡ�������ı���Ϊcontroller���У���ǰ�ӿڵ�ʵ����
 * ͨ���÷������ʵ���ǰ�����request�����response����
 * 2017��12��15��
 */
public interface Controller {
	String handleRequest(HttpServletRequest request, 
			HttpServletResponse response);
}
