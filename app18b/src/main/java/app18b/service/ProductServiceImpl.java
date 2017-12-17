package app18b.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import app18b.domain.Product;

/**
 * @author suxin
 * 2017��12��17��
 * prjName:app18b pakName:app18b.service
 * TODO
 * �˴���@Serviceע�����ǰ�ӿڵ�ʵ����Ϊһ��������Ҫ��springmvc�������ļ������ӶԷ����ɨ����ʵ������ע��
 * 2017��12��17��
 */
@Service
public class ProductServiceImpl implements ProductService {

	//�˴�ʹ��һ��map�����Ų�Ʒ��֮���ض��򵽲�ѯҳ��ʱ���ô����id��ö�Ӧ�Ĳ�Ʒ���󼴿�
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
        long newId = generator.incrementAndGet();//�ƺ����Զ�����id��һ�׷���
        product.setId(newId);
        products.put(newId, product);
        return product;
    }

    public Product get(long id) {
        return products.get(id);
    }
}
