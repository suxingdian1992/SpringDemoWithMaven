package app21a.validator;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import app21a.domain.Product;

public class ProductValidator implements Validator {

    public boolean supports(Class<?> klass) {
        return Product.class.isAssignableFrom(klass);
    }

    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        
        //三个参数分别为，错误列表，需要验证的域，验证生效之后返回的错误信息的编码
        ValidationUtils.rejectIfEmpty(errors, "name", "productname.required");
        ValidationUtils.rejectIfEmpty(errors, "price", "price.required");
        ValidationUtils.rejectIfEmpty(errors, "productionDate", "productiondate.required");
        
        Float price = product.getPrice();
        if (price != null && price < 0) {
            errors.rejectValue("price", "price.negative");
        }
        Date productionDate = product.getProductionDate();
        if (productionDate != null) {
            // The hour,minute,second components of productionDate are 0
            if (productionDate.after(new Date())) {
                System.out.println("salah lagi");
                errors.rejectValue("productionDate", "productiondate.invalid");
            }
        }
    }
}