package com.sxd.springmvc.app16a.servlet;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxd.springmvc.app16a.form.ProductForm;
import com.sxd.springmvc.dependencyinjection.Product;

public class ControllerServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1579L;

    @Override
    public void doGet(HttpServletRequest request, 
            HttpServletResponse response)
            throws IOException, ServletException {
        process(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, 
            HttpServletResponse response)
            throws IOException, ServletException {
        process(request, response);
    }

    private void process(HttpServletRequest request,
            HttpServletResponse response) 
            throws IOException, ServletException {

        String uri = request.getRequestURI();
        /*
         * uri is in this form: /contextName/resourceName, 
         * for example: /app10a/product_input. 
         * However, in the event of a default context, the 
         * context name is empty, and uri has this form
         * /resourceName, e.g.: /product_input
         */
        int lastIndex = uri.lastIndexOf("/");
        String action = uri.substring(lastIndex + 1); 
        // execute an action
        if (action.equals("product_input.action")) {
            // no action class, there is nothing to be done
        } else if (action.equals("product_save.action")) {
            // create form
            ProductForm productForm = new ProductForm();
            // populate action properties
            productForm.setName(request.getParameter("name"));
            productForm.setDescription(
                    request.getParameter("description"));
            productForm.setPrice(request.getParameter("price"));
            
            // create model
            Product product = new Product();
            product.setName(productForm.getName());
            product.setDescription(productForm.getDescription());
            try {
            	product.setPrice(Float.parseFloat(
            			productForm.getPrice()));
            } catch (NumberFormatException e) {
            }
            
            // code to save product
            
            // store model in a scope variable for the view
            request.setAttribute("product", product);
        }

        // forward to a view
        String dispatchUrl = null;
        if (action.equals("product_input.16aaction")) {
            dispatchUrl = "WEB-INF/jsp/app16a/ProductForm.jsp";
        } else if (action.equals("product_save.16aaction")) {
            dispatchUrl = "WEB-INF/jsp/app16a/ProductDetails.jsp";
        }
        if (dispatchUrl != null) {
            RequestDispatcher rd = 
                    request.getRequestDispatcher(dispatchUrl);
            rd.forward(request, response);
        }
    }
}