package app17a.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class InputProductController implements Controller {

    private static final Log logger = LogFactory
            .getLog(InputProductController.class);

    public ModelAndView handleRequest(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        logger.info("InputProductController called");
        /* 此处不需要再写成完全的地址是因为在springmvc中为返回的请求加入了前缀和后缀，所以可以去除 */
        return new ModelAndView("ProductForm");
    }

}