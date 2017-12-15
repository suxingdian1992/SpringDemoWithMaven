package app17a.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import app17a.domain.Product;
import app17a.form.ProductForm;

public class SaveProductController implements Controller {

	private static final Log logger = LogFactory.getLog(SaveProductController.class);

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("SaveProductController called");
		ProductForm productForm = new ProductForm();
		// populate action properties
		productForm.setName(request.getParameter("name"));
		productForm.setDescription(request.getParameter("description"));
		productForm.setPrice(request.getParameter("price"));

		// create model
		Product product = new Product();
		product.setName(productForm.getName());
		product.setDescription(productForm.getDescription());
		try {
			product.setPrice(Float.parseFloat(productForm.getPrice()));
		} catch (NumberFormatException e) {
		}

		// insert code to save Product
		/* 此处不需要再写成完全的地址是因为在springmvc中为返回的请求加入了前缀和后缀，所以可以去除 */
		return new ModelAndView("ProductDetails", "product", product);
	}

}