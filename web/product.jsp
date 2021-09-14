<%-- 
    Document   : product
    Created on : 13/09/2021, 20:50:20
    Author     : pqpca
--%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
        <link type="text/css"
            href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="http://www.godtur.no/godtur/js/jquery-ui-1.8.18.custom.min.js"></script>
        <title>Add new Product</title>
    </head>
    <body>
     <form method="POST" action='ProductController' name="frmAddProduct">
        Product ID : <input type="text" readonly="readonly" name="productid"
            value="<c:out value="${product.productid}" />" /> <br /> 
        Name : <input
            type="text" name="name"
            value="<c:out value="${product.name}" />" /> <br /> 
        Brand : <input
            type="text" name="brand"
            value="<c:out value="${product.brand}" />" /> <br /> 
        Price : <input type="number" name="price"
            value="<c:out value="${product.price}" />" /> <br /> <input
            type="submit" value="Submit" />
    </form>
    </body>
</html>
