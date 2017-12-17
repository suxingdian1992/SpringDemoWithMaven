package app18b.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app18b.domain.Product;
import app18b.form.ProductForm;
import app18b.service.ProductService;

/**
 * @author suxin
 * 2017��12��17��
 * prjName:app18b pakName:app18b.controller
 * TODO
 * 2017��12��17��
 */
@Controller
public class ProductController {

    private static final Log logger = LogFactory
            .getLog(ProductController.class);

    @Autowired
    private ProductService productService;//�˴���ע��@Autowired�����ӿڶ���ProductServiceע�뵽��ǰ��������

    @RequestMapping(value = "/product_input")
    public String inputProduct() {
        logger.info("inputProduct called");
        return "ProductForm";
    }

    
    /**
     * ����1:37:12
     * Args:@param productForm
     * Args:@param redirectAttributes
     * Args:@return
     * ReturnType:String
     * Author:suxin
     * 1������ע�룬ע��Autowired��ʶ
     * 2���ض���ע���ض���ת������
     */
    @RequestMapping(value = "/product_save", method = RequestMethod.POST)
    public String saveProduct(ProductForm productForm, RedirectAttributes redirectAttributes) {
        logger.info("saveProduct called");
        // no need to create and instantiate a ProductForm
        // create Product
        //�˴������ɻ�Ϊ��ֱ�Ӵ���������Ĳ�����ȡ��ǰ̨ҳ��ı�ֵ
        Product product = new Product();
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        try {
            product.setPrice(Float.parseFloat(productForm.getPrice()));
        } catch (NumberFormatException e) {
        }

        // add product
        Product savedProduct = productService.add(product);
        
        //����ɹ�֮��ҳ���ض���ȫ����ͼ����ֹҳ��ˢ��֮�������ύ������
        //�����ض��򾭹��ͻ��ˣ���ת�������������ԣ�ת���������׵İ���Ϣ��ֵ��model��������ֵ
        //�ض������ھ����ͻ��ˣ�����model��������Ϣ�����ض����ʱ��ʧ
        //���Դ˴�ʹ��flash����Ϊ�ض����ҳ�洫��ֵ
        //Ҫʹ��flash���ԣ���Ҫ��spring�������ļ�����һ��<mvc:annotation-driven/>����
        //����ҪΪ��������һ��RedirectAttributes���͵Ĳ���
        redirectAttributes.addFlashAttribute("message", "The product was successfully added.");

        return "redirect:/product_view/" + savedProduct.getId();
    }

    /**
     * ����1:49:50
     * Args:@param id
     * Args:@param model
     * Args:@return
     * ReturnType:String
     * Author:suxin
     * TODO
     * ע��˴���ע��@PathVariable����ע���ʾ�ò���Ϊֱ�Ӵ�@RequestMappingע�ⲿ�ֵĻ����Ų��ֻ�ȡ
     * ���÷��������õ�ʱ��id���ᱻ��Ϊ�������ݸ��÷���
     */
    @RequestMapping(value = "/product_view/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        Product product = productService.get(id);
        model.addAttribute("product", product);
        return "ProductView";
    }
}
