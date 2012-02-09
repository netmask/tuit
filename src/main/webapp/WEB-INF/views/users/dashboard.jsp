<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Tuiter :)</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="span8 offset2">
            <ul class="nav nav-tabs">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#">Profile</a></li>
                <li><a href="#">Messages</a></li>
            </ul>
        </div>
        <div class="span8 offset2">
            <form:form modelAttribute="tuit" action="/user/tuit" id="tuit-post" method="post">
            <div class="row well">
                <div class="span7">
                    <span style="float: right; margin-bottom: 10px;"> <h4>New tuit (<span id="charCount">0</span>/254)</h4></span>
                    <form:textarea cssStyle="width: 680px" path="content" cssClass="input-xlarge focused"/>
                    <form:errors path="content" cssClass="alert alert-error"/>
                    <br/>
                    <input type="submit" class="btn btn-primary"/>

                    </form:form>
                </div>
            </div>
            <div id="tuits-container">
                <c:forEach var="tui" items="${timeline}">
                    <div class="row" style=" border-bottom: 1px solid #E5E5E5; padding-bottom: 10px; padding-top: 10px;">
                        <div class="span4">
                            <h5>${tui.user.username}</h5>
                            <h6>${tui.stamp}</h6>
                            <span>${tui.content}</span>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>
