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
 * 2017年12月17日
 * prjName:app18b pakName:app18b.controller
 * TODO
 * 2017年12月17日
 */
@Controller
public class ProductController {

    private static final Log logger = LogFactory
            .getLog(ProductController.class);

    @Autowired
    private ProductService productService;//此处的注解@Autowired代表将接口对象ProductService注入到当前控制器内

    @RequestMapping(value = "/product_input")
    public String inputProduct() {
        logger.info("inputProduct called");
        return "ProductForm";
    }

    
    /**
     * 下午1:37:12
     * Args:@param productForm
     * Args:@param redirectAttributes
     * Args:@return
     * ReturnType:String
     * Author:suxin
     * 1、依赖注入，注意Autowired标识
     * 2、重定向，注意重定向转发区别
     */
    @RequestMapping(value = "/product_save", method = RequestMethod.POST)
    public String saveProduct(ProductForm productForm, RedirectAttributes redirectAttributes) {
        logger.info("saveProduct called");
        // no need to create and instantiate a ProductForm
        // create Product
        //此处依旧疑惑，为何直接传入控制器的参数能取到前台页面的表单值
        Product product = new Product();
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        try {
            product.setPrice(Float.parseFloat(productForm.getPrice()));
        } catch (NumberFormatException e) {
        }

        // add product
        Product savedProduct = productService.add(product);
        
        //保存成功之后讲页面重定向到全新视图，防止页面刷新之后重新提交表单数据
        //由于重定向经过客户端，而转发不经过，所以，转发可以轻易的把信息赋值给model来做到传值
        //重定向由于经过客户端，所以model的所有信息会在重定向的时候丢失
        //所以此处使用flash属性为重定向的页面传送值
        //要使用flash属性，需要在spring的配置文件加入一个<mvc:annotation-driven/>属性
        //还需要为方法传入一个RedirectAttributes类型的参数
        redirectAttributes.addFlashAttribute("message", "The product was successfully added.");

        return "redirect:/product_view/" + savedProduct.getId();
    }

    /**
     * 下午1:49:50
     * Args:@param id
     * Args:@param model
     * Args:@return
     * ReturnType:String
     * Author:suxin
     * TODO
     * 注意此处的注解@PathVariable，该注解表示该参数为直接从@RequestMapping注解部分的花括号部分获取
     * 当该方法被调用的时候id将会被作为参数传递给该方法
     */
    @RequestMapping(value = "/product_view/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        Product product = productService.get(id);
        model.addAttribute("product", product);
        return "ProductView";
    }
}
