<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype>
<html>
    <head>
        <title>Home</title>
    </head>
    <body>
        <div>
            <h1>Home</h1>
            <sec:authorize access="isAnonymous()">
                <p>
                    <a href="/spring_security_login">Sign In</a>
                </p>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()">
                <p>Hello, ${userDetails.username}! <a href="/j_spring_security_logout">Sign Out</a></p>
            </sec:authorize>

            <sec:authorize access="hasRole('admin')">
                <p>
                    <a href="/admin">Admin page</a>
                </p>
            </sec:authorize>
        </div>
    </body>
</html>