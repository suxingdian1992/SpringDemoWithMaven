<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<title>Book List</title>
<style type="text/css">@import url("<c:url value="/css/main.css"/>");</style>
</head>
<body>

<div id="global">
<h1>Book List</h1>
<a href="<c:url value="/book_input"/>">Add Book</a>
<table>
<tr>
    <th>Category</th>
    <th>Title</th>
    <th>ISBN</th>
    <th>Author</th>
    <th>&nbsp;</th>
</tr>
<c:forEach items="${books}" var="book">
    <tr>
        <td>${book.category}</td>
        <td>${book.title}</td>
        <td>${book.isbn}</td>
        <td>${book.author}</td>
        <td><a href="book_edit/${book.id}" >Edit</a></td>
        <td><a href="book_delete/${book.id}" onclick="javascript:return del()">Delete</a></td>
    </tr>
</c:forEach>
</table>
</div>
</body>
<SCRIPT LANGUAGE=javascript> 
function del() { 
 var msg = "Are you sure?this book will be deleted \n\nplease confirm!"; 
 if (confirm(msg)==true){ 
  return true; 
 }else{ 
  return false; 
 } 
} 
</SCRIPT> 

</html>
