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
        <div class="span6 offset2">
            <ul class="nav nav-tabs">
                <li><a  data-toggle="modal" href="#following-md" >Following</a></li>
                <li><a  data-toggle="modal" href="#find-user-md" >Find Users</a></li>
            </ul>
        </div>
        <div class="span2" style="padding-top:9px">
             <span >Welcome ${user.username}</span>
             <span><a href="/session/logout"> Logout </a></span>
        </div>
        <div class="span8 offset2" id="tuit-app">
            <form:form modelAttribute="tuit" action="/user/tuit" id="tuit-post" method="post">
            <div class="row well">
                <div class="span7">
                    <span style="float: right; margin-bottom: 10px;"> <h4>New tuit (<span id="charCount">0</span>/254)</h4></span>
                    <form:textarea id="tuit-content" cssStyle="width: 680px" path="content" cssClass="input-xlarge focused"/>
                    <form:errors path="content" cssClass="alert alert-error"/>
                    <br/>
                    <input type="button" class="btn btn-primary" id="new-tuit" value="Add"/>
                    </form:form>
                </div>
            </div>
            <div id="tuits-container">

            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="following-md">
    <div class="modal-header">
        <a class="close" data-dismiss="modal">×</a>
        <h3>Following this users</h3>
    </div>
    <div class="modal-body" id="following-list">
        <c:forEach var="user" items="${user.following}">
            <div class="row" style=" border-bottom: 1px solid #E5E5E5; padding-bottom: 10px; padding-top: 10px;">
                <div class="span1">
                    <img src="/resources/img/${user.avatarName}" alt="">
                </div>
                <div class="span4">
                    <h5>${user.username}</h5>
                </div>
                <div class="span3">
                    <a href="#/unfollow/${user.username}">Unfollow!</a>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="modal-footer">
    </div>
</div>



<div class="modal fade" id="find-user-md">
    <div class="modal-header">
        <a class="close" data-dismiss="modal">×</a>
        <h3>Find Users</h3>
        <input type="text" class="input-medium search-query" placeholder="username" id="username-search">
    </div>
    <div class="modal-body" id="user-search-res">

    </div>
    <div class="modal-footer">
    </div>
</div>

<script type="text/template" id="tuit">
    <div class="row" style=" border-bottom: 1px solid #E5E5E5; padding-bottom: 10px; padding-top: 10px;">
        <div class="span1">
            <img src="/resources/img/${user.avatarName}" alt="">
        </div>
        <div class="span4">
            <h5>{{username}}</h5>
            <h6>{{stamp}}</h6>
            <span>{{content}}</span>
        </div>
    </div>
</script>


<script type="text/template" id="user">
    <div class="row" style=" border-bottom: 1px solid #E5E5E5; padding-bottom: 10px; padding-top: 10px;">
        <div class="span1">
            <img src="/resources/img/${user.avatarName}" alt="">
        </div>
        <div class="span4">
            <h5>{{username}}</h5>
        </div>
        <div class="span3">
            <a href="#/follow/{{username}}">Follow</a>
        </div>
    </div>
</script>


<script type="text/template" id="uuser">
    <div class="row" style=" border-bottom: 1px solid #E5E5E5; padding-bottom: 10px; padding-top: 10px;">
        <div class="span1">
            <img src="/resources/img/{{avatarName}}" alt="">
        </div>
        <div class="span4">
            <h5>{{username}}</h5>
        </div>
        <div class="span3">
            <a href="#/unfollow/{{username}}">Unfollow!</a>
        </div>
    </div>
</script>



<script type="text/javascript" src="/resources/js/jquery-1.7.1.min.js" ></script>
<script type="text/javascript" src="/resources/js/bootstrap.js" ></script>
<script type="text/javascript" src="/resources/js/underscore-min.js"></script>
<script type="text/javascript" src="/resources/js/backbone-min.js"></script>
<script type="text/javascript" src="/resources/js/tuit.dashboard.backbone.js" ></script>


</body>
</html>
