/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author pqpca
 */

import dao.ProductDao;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;

@WebServlet(name = "Produtos", urlPatterns = {"/ProductController"})
public class ProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/product.jsp";
    private static String LIST_PRODUCT = "/listProduct.jsp";
    private ProductDao dao;
    
    public ProductController() {
        super();
        dao = new ProductDao();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int productId = Integer.parseInt(request.getParameter("productId"));
            dao.deleteProduct(productId);
            forward = LIST_PRODUCT;
            request.setAttribute("products", dao.getAllProducts());    
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int productId = Integer.parseInt(request.getParameter("userId"));
            Product product = dao.getProductById(productId);
            request.setAttribute("product", product);
        } else if (action.equalsIgnoreCase("listUser")){
            forward = LIST_PRODUCT;
            request.setAttribute("products", dao.getAllProducts());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = new Product();
        product.setName(request.getParameter("name"));
        product.setBrand(request.getParameter("brand"));
        product.setPrice(Double.parseDouble(request.getParameter("price")));
        String productid = request.getParameter("productid");
        if(productid == null || productid.isEmpty())
        {
            dao.addProduct(product);
        }
        else
        {
            product.setProductid(Integer.parseInt(productid));
            dao.updateProduct(product);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_PRODUCT);
        request.setAttribute("products", dao.getAllProducts());
        view.forward(request, response);
    }
}
