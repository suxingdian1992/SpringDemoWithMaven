package com.sxd.springmvc.app16c.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxd.springmvc.app16c.domain.Product;
import com.sxd.springmvc.app16c.form.ProductForm;
import com.sxd.springmvc.app16c.validator.ProductValidator;

public class SaveProductController implements Controller {

	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		ProductForm productForm = new ProductForm();
		// populate action properties
		productForm.setName(request.getParameter("name"));
		productForm.setDescription(request.getParameter("description"));
		productForm.setPrice(request.getParameter("price"));

		// validate ProductForm ��ʼ����֤��������ɶԱ��������֤�������֤�����򷵻ش����б�������ж���ֵ
		ProductValidator productValidator = new ProductValidator();
		List<String> errors = productValidator.validate(productForm);
		if (errors.isEmpty()) {
			// create Product from ProductForm
			Product product = new Product();
			product.setName(productForm.getName());
			product.setDescription(productForm.getDescription());
			product.setPrice(Float.parseFloat(productForm.getPrice()));

			// no validation error, execute action method
			// insert code to save product to the database

			// store product in a scope variable for the view
			request.setAttribute("product", product);
			return "/WEB-INF/jsp/app16c/ProductDetails.jsp";
		} else {
			// store errors and form in a scope variable for the view
			request.setAttribute("errors", errors);
			request.setAttribute("form", productForm);
			return "/WEB-INF/jsp/app16c/ProductForm.jsp";
		}
	}

}