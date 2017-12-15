package app18a.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import app18a.domain.Product;
import app18a.form.ProductForm;

/**
 * @author suxin 2017��12��15�� prjName:springwithannotation
 *         pakName:app18a.controller TODO �˴���Ϊ��׼�Ĵ���ע���springmvcӦ�ó���Ŀ�����Ӧ�е�����
 *         2017��12��15��
 */
@Controller
public class ProductController {

	private static final Log logger = LogFactory.getLog(ProductController.class);

	@RequestMapping(value = "/product_input")
	public String inputProduct() {
		logger.info("inputProduct called");
		// �˴���spring-mvc.xml���Ѿ�������ǰ��׹�����Դ˴����صĶ����ᱻ�޸�Ϊ/WEB-INF/jsp/ProductForm.jsp
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

		// �˴���spring-mvc.xml���Ѿ�������ǰ��׹�����Դ˴����صĶ����ᱻ�޸�Ϊ/WEB-INF/jsp/ProductDetails.jsp
		return "ProductDetails";
	}
}