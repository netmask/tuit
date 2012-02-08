<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<title>Tuiter  :)</title>
</head>
<body>
<div style="top-padding:50px">&nbsp;</div>
<div class="hero-unit">
    <div class="row">
        <div class="span5">
            <h3>Login</h3>
            <form:form modelAttribute="user" action="/login" method="post" class="form-horizontal">

                    <form:label	for="email" path="email" cssErrorClass="error" cssClass="control-label">Email</form:label>
                    <form:input path="email" /> <form:errors path="email" />

                    <form:label for="password" path="password" cssErrorClass="password" cssClass="control-label">Password</form:label>
                    <form:input path="password" /> <form:errors path="password" />
                    <br/>
                    <br/>
                    <input type="submit" class="btn btn-primary"/>
            </form:form>
        </div>
        <div class="span5">
            <h1>Tuit</h1>
            <p>Welcome to Tuit small message platform</p>
            <p><a class="btn btn-primary btn-large" href="/user">Create Account</a></p>
        </div>
    </div>
</div>
    <section id="grid-system">
        <div class="page-header">Last Tuets</div>
    </section>
</body>
</html>
