<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
    </head>
    <body>
        <h1>Admin</h1>
        <p>Hello, ${userDetails.username}!</p>

        <p>User authorities: ${userAuthorities}</p>

        <a href="/">Back</a>
    </body>
</html>