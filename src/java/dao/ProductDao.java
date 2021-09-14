/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author pqpca
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import util.DbUtil;

public class ProductDao {
    
    private Connection connection;
    
    public ProductDao() throws ClassNotFoundException, SQLException {
        connection = DbUtil.getConnection();
    }
    
    public void addProduct(Product product) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into products (name, brand, price) values (?, ?, ?)");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getBrand());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteProduct(int productId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from products where productid=?");
            // Parameters start with 1
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateProduct(Product product) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update products set name=?, brand=?, price=?"  +
                            "where productid=?");
            // Parameters start with 1
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getBrand());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getProductid());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<Product>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from products");
            while (rs.next()) {
                Product product = new Product();
                product.setProductid(rs.getInt("productid"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }
    
    public Product getProductById(int productId) {
        Product product = new Product();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from products where productid=?");
            preparedStatement.setInt(1, productId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                product.setProductid(rs.getInt("productid"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }
}
