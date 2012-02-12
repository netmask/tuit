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
<div class="row">
    <form:form modelAttribute="user" action="user" method="post" class="form-horizontal" enctype="multipart/form-data" id="new-user-form">
    <div class="span6 offset2">
        <h3>
            Create Account
        </h3>
         <div>
             <span>Create a new account for tuit. </span>
         </div>

            <fieldset>

                <form:label	for="username" path="username" cssErrorClass="username" cssClass="control-label">Username</form:label>
                <form:input path="username" /> <form:errors path="username" cssClass="alert alert-error"/>

                <form:label	for="email" path="email" cssErrorClass="error" cssClass="control-label">Email</form:label>
                <form:input path="email" /> <form:errors path="email" cssClass="alert alert-error"/>

                <form:label for="password" path="password" cssErrorClass="password" cssClass="control-label">Password</form:label>
                <form:password path="password" /> <form:errors path="password" cssClass="alert alert-error"/>
                <br/>
                <br/>

                <input type="submit" class="btn btn-primary"/>

            </fieldset>

    </div>
    <div class="span2">
        <div class='row well' style="width: 50px; height: 70px; margin-top: 50px;">
            <img src="/resources/img/no_avatar_perfil.jpeg" alt="no avatar" id="avatar"/>
        </div>
        <div class="row">
            <input id="file" type="file" name="file" />
            <span>Chose your photo</span>
        </div>
    </div>
    </form:form>
</div>

</body>
</html>