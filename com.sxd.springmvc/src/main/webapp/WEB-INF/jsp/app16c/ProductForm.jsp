<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Add Product Form 16c</title>
<style type="text/css">@import url(css/main.css);</style>
</head>
<body>

<div id="global">
<!-- 此处为出错提示框，注意，可能需要在页面首部加入isELIgnored="false"放开el表达式的阻拦 -->
<c:if test="${requestScope.errors != null}">
        <p id="errors">
        Error(s)!
        <ul>
        <c:forEach var="error" items="${requestScope.errors}">
            <li>${error}</li>
        </c:forEach>
        </ul>
        </p>
</c:if>
<form action="product_save.16caction" method="post">
    <fieldset>
        <legend>Add a product</legend>
            <p>
                <label for="name">Product Name: </label>
                <input type="text" id="name" name="name" 
                    tabindex="1">
            </p>
            <p>
                <label for="description">Description: </label>
                <input type="text" id="description" 
                    name="description" tabindex="2">
            </p>
            <p>
                <label for="price">Price: </label>
                <input type="text" id="price" name="price" 
                    tabindex="3">
            </p>
            <p id="buttons">
                <input id="reset" type="reset" tabindex="4">
                <input id="submit" type="submit" tabindex="5" 
                    value="Add Product">
            </p>
    </fieldset>
</form>
</div>
</body>
</html>