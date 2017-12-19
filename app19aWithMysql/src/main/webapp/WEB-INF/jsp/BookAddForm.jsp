<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<title>Add Book Form</title>
<style type="text/css">@import url("<c:url value="/css/main.css"/>");</style>
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div id="global">
<form:form modelAttribute="book" action="book_save" method="post">
    <fieldset>
        <legend>Add a book</legend>
        <p>
            <label for="category">Category: </label>
             <form:select id="category" path="category" 
                items="${categories}" itemLabel="name" 
                itemValue="id"/>
        </p>
        <p>
            <label for="title">Title: </label>
            <form:input id="title" path="title" class="form-control"/>
        </p>
        <p>
            <label for="author">Author: </label>
            <form:input id="author" path="author" class="form-control"/>
        </p>
        <p>
            <label for="isbn">ISBN: </label>
            <form:input id="isbn" path="isbn" class="form-control"/>
        </p>
        
        <p id="buttons">
            <input id="reset" type="reset" tabindex="4" class="btn btn-default">
            <input id="submit" type="submit" tabindex="5" 
                value="Add Book" class="btn btn-default">
        </p>
    </fieldset>
</form:form>
</div>
</body>
</html>
