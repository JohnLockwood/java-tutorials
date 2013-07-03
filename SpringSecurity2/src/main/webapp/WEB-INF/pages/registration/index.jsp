<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
    <head>
        <title>Registration</title>
        <style>
            .error {
                color: #ff0000;
            }

            .errorblock {
                color: #000;
                background-color: #ffEEEE;
                border: 3px solid #ff0000;
                padding: 8px;
                margin: 16px;
            }
        </style>
    </head>
    <body>
    <h1>Sign Up Now</h1>
    <form:form method="POST"  action="/register/" modelAttribute="user">
        <div>
            <form:label for="username" path="username">Email:</form:label><br />
            <form:input path="username" /><br />
            <form:errors path="username" cssClass="error" />
        </div>

        <div>
            <form:label for="password" path="password">Password:</form:label><br />
            <form:password path="password" /><br />
            <form:errors path="password" cssClass="error" />
        </div>
        <div>
            <form:label for="password" path="password">Confirm password:</form:label><br />
            <form:password path="password" /><br />
            <form:errors path="password" cssClass="error" />
        </div>
        <input type="submit" value="Submit"/>
    </form:form>
    </body>
</html>