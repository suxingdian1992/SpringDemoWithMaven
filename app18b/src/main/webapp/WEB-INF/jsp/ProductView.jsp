<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<title>View Product</title>
<!-- <style type="text/css">@import url(css/main.css);</style> -->
<!-- 此处使用外部引用css文件方式导入页面样式 -->
<!-- <link rel="stylesheet" type="text/css" href="/app18b/css/main.css"/> -->
<style type="text/css">
@import url("<c:url value="/css/main.css" />");
</style>
</head>
<body>
<div id="global">
    <h4>${message}</h4>
    <p>
        <h5>Details:</h5>
        Product Name: ${product.name}<br/>
        Description: ${product.description}<br/>
        Price: $${product.price}
    </p>
</div>
</body>
</html>