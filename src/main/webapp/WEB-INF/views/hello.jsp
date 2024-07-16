<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>hello</title>
</head>
<body>
    <p>data(EL문법) : ${myData}</p>
    <p>data(jstl문법-java코드) : <%
        String getData = String.valueOf(request.getAttribute("myData"));
        out.print(getData);
    %></p>
    <form action="/hello/servlet/jsp/post" method="post">
        name: <input type="text" name="name" id="name">
        email: <input type="text" name="email" id="email">
        password: <input type="text" name="password" id="password">
        <input type="submit" value="제출">
    </form>
</body>
</html>
