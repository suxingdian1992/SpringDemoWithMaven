package com.sxd.springmvc.app16b.servlet;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxd.springmvc.app16b.controller.InputProductController;
import com.sxd.springmvc.app16b.controller.SaveProductController;

public class DispatcherServlet extends HttpServlet {
    
    private static final long serialVersionUID = 748495L;

    @Override
    public void doGet(HttpServletRequest request, 
            HttpServletResponse response)
            throws IOException, ServletException {
        process(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, 
            HttpServletResponse response)
            throws IOException, ServletException {
        process(request, response);
    }

    private void process(HttpServletRequest request,
            HttpServletResponse response) 
            throws IOException, ServletException {

        String uri = request.getRequestURI();
        /*
         * uri is in this form: /contextName/resourceName, 
         * for example: /app10a/product_input. 
         * However, in the event of a default context, the 
         * context name is empty, and uri has this form
         * /resourceName, e.g.: /product_input
         */
        int lastIndex = uri.lastIndexOf("/");
        String action = uri.substring(lastIndex + 1); 
        
        String dispatchUrl = null;
        if (action.equals("product_input.16baction")) {
        	//当请求为输入的时候执行输入页面处理类
        	InputProductController controller = new InputProductController();
        	dispatchUrl = controller.handleRequest(request, response);//返回跳转页面
        } else if (action.equals("product_save.16baction")) {
        	//当请求为保存的时候执行保存页面处理类
        	SaveProductController controller = new SaveProductController();
        	dispatchUrl = controller.handleRequest(request, response);//返回跳转页面
        }

        if (dispatchUrl != null) {
            RequestDispatcher rd = 
                    request.getRequestDispatcher(dispatchUrl);
            rd.forward(request, response);
        }
    }
}