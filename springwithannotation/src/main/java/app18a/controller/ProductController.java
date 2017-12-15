package app18a.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import app18a.domain.Product;
import app18a.form.ProductForm;

/**
 * @author suxin 2017年12月15日 prjName:springwithannotation
 *         pakName:app18a.controller TODO 此处即为标准的带有注解的springmvc应用程序的控制类应有的样子
 *         2017年12月15日
 */
@Controller
public class ProductController {

	private static final Log logger = LogFactory.getLog(ProductController.class);

	@RequestMapping(value = "/product_input")
	public String inputProduct() {
		logger.info("inputProduct called");
		// 此处在spring-mvc.xml中已经配置了前后坠，所以此处返回的东西会被修改为/WEB-INF/jsp/ProductForm.jsp
		return "ProductForm";
	}

	@RequestMapping(value = "/product_save")
	public String saveProduct(ProductForm productForm, Model model) {
		logger.info("saveProduct called");
		// no need to create and instantiate a ProductForm
		// create Product
		Product product = new Product();
		product.setName(productForm.getName());
		product.setDescription(productForm.getDescription());
		try {
			product.setPrice(Float.parseFloat(productForm.getPrice()));
		} catch (NumberFormatException e) {
		}

		// add product

		model.addAttribute("product", product);

		// 此处在spring-mvc.xml中已经配置了前后坠，所以此处返回的东西会被修改为/WEB-INF/jsp/ProductDetails.jsp
		return "ProductDetails";
	}
}