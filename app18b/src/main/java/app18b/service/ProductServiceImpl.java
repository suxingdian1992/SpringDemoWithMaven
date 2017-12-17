package app18b.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import app18b.domain.Product;

/**
 * @author suxin
 * 2017年12月17日
 * prjName:app18b pakName:app18b.service
 * TODO
 * 此处的@Service注解代表当前接口的实现类为一个服务，需要在springmvc的配置文件中增加对服务的扫描来实现依赖注入
 * 2017年12月17日
 */
@Service
public class ProductServiceImpl implements ProductService {

	//此处使用一个map来安放产品，之后当重定向到查询页面时，用传入的id获得对应的产品对象即可
    private Map<Long, Product> products = new HashMap<Long, Product>();
    private AtomicLong generator = new AtomicLong();

    public ProductServiceImpl() {
        Product product = new Product();
        product.setName("JX1 Power Drill");
        product.setDescription("Powerful hand drill, made to perfection");
        product.setPrice(129.99F);
        add(product);
    }

    public Product add(Product product) {
        long newId = generator.incrementAndGet();//似乎是自动生成id的一套方法
        product.setId(newId);
        products.put(newId, product);
        return product;
    }

    public Product get(long id) {
        return products.get(id);
    }
}
