package com.sxd.springmvc.app16b.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxd.springmvc.app16a.domain.Product;
import com.sxd.springmvc.app16a.form.ProductForm;

/**
 * @author suxin
 * 2017年12月15日
 * prjName:com.sxd.springmvc pakName:com.sxd.springmvc.app16b.controller
 * TODO
 * 保存操作，表单类的赋值，实现模型类的赋值，跳转到详情页面
 * 2017年12月15日
 */
public class SaveProductController implements Controller {
	
	public String handleRequest(HttpServletRequest request, 
			HttpServletResponse response) {
        ProductForm productForm = new ProductForm();
        // populate form properties
        productForm.setName(
                request.getParameter("name"));
        productForm.setDescription(
                request.getParameter("description"));
        productForm.setPrice(request.getParameter("price"));
        
        // create model
        Product product = new Product();
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        try {
        	product.setPrice(Float.parseFloat(
        			productForm.getPrice()));
        } catch (NumberFormatException e) {
        }

        // insert code to add product to the database

        request.setAttribute("product", product);
        return "/WEB-INF/jsp/app16b/ProductDetails.jsp";
	}

}