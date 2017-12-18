<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<title>Add Employee Form</title>
<style type="text/css">@import url("<c:url value="/css/main.css"/>");</style>
</head>
<body>

<div id="global">
<form:form modelAttribute="employee" action="employee_save" method="post">
    <fieldset>
        <legend>Add an employee</legend>
        <p>
            <label for="firstName">First Name: </label>
            <form:input path="firstName" tabindex="1"/>
        </p>
        <p>
            <form:errors path="lastName" cssClass="error"/>
        </p>
        <p>
            <label for="lastName">LastName Name: </label>
            <form:input path="lastName" tabindex="2"/>
        </p>
        <p>
            <form:errors path="birthDate" cssClass="error"/>
        </p>
        <p>
            <label for="birthDate">Date Of Birth: </label>
            <form:input path="birthDate" tabindex="3" />
        </p>
        <p>
            <form:errors path="endDate" cssClass="error"/>
        </p>
        <p>
            <label for="endDate">Date Of end: </label>
            <form:input path="endDate" tabindex="3" />
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
