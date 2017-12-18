<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<title>Add Product Form</title>
<style type="text/css">@import url("<c:url value="/css/main.css"/>");</style>
</head>
<body>

<div id="global">
<!-- 此处最好使用modelAttribute属性，用于绑定后台模型 -->
<form:form modelAttribute="employee" action="employee_save" method="post">
    <fieldset>
        <legend>Add an employee</legend>
        <p>
            <label for="firstName">First Name: </label>
            <input type="text" id="firstName" name="firstName" 
                tabindex="1">
        </p>
        <p>
            <label for="lastName">First Name: </label>
            <input type="text" id="lastName" name="lastName" 
                tabindex="2">
        </p>
        <p>
        <!-- 此处表示模型中的哪个属性需要被格式化 -->
            <form:errors path="birthDate" cssClass="error"/>
        </p>
        <p>
            <label for="birthDate">Date Of Birth: </label>
            <form:input path="birthDate" id="birthDate" />
        </p>
        <p id="buttons">
            <input id="reset" type="reset" tabindex="4">
            <input id="submit" type="submit" tabindex="5" 
                value="Add Employee">
        </p>
    </fieldset>
</form:form>
</div>
</body>
</html>
