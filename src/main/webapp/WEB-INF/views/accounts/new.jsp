<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
</head>
<body>

    <h1>
        Create Account
    </h1>
        <form:form modelAttribute="user" action="user" method="post" class="form-horizontal">
            <fieldset>
                <legend>Account Fields</legend>

                    <form:label	for="email" path="email" cssErrorClass="error" cssClass="control-label">Email</form:label>
                    <form:input path="email" /> <form:errors path="email" />

                    <form:label for="password" path="password" cssErrorClass="password" cssClass="control-label">Password</form:label>
                    <form:input path="password" /> <form:errors path="password" />

                    <input type="submit" class="btn btn-primary"/>

            </fieldset>
        </form:form>

</body>
</html>