<!DOCTYPE HTML>
<html>
<head>
<title>Add Product Form 17a</title>
<!-- <style type="text/css">@import url(css/main.css);</style> -->
<!-- 此处使用外部引用css文件方式导入页面样式 -->
<link rel="stylesheet" type="text/css" href="/springwithannotation/css/main.css"/>
</head>
<body>

<div id="global">
<!-- 此处的action会追加在之前控制器中定义的product之后，组合为.../product/save -->
<form action="save" method="post">
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