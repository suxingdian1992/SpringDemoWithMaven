<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Save Product 16b</title>
<style type="text/css">@import url(css/main.css);</style>
</head>
<body>
注意，jsp页面默认可能有isELIgnored为true的情况，此时el表达式不生效，或者在jsp页面直接修改 isELIgnored="false"，或者在部署描述符中添加描述<br />
session id: ${pageContext.session.id}
<br/>
<div id="global">
    <h4>The product has been saved.</h4>
    <p>
        <h5>Details:</h5>
        Product Name: ${product.name}<br/>
        Description: ${product.description}<br/>
        Price: $${product.price}
    </p>
</div>
</body>
</html>