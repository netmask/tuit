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
                <li><a  data-toggle="modal" href="#following-md" >Following</a></li>
                <li><a  data-toggle="modal" href="#find-user-md" >Find Users</a></li>
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

<div class="modal fade" id="following-md">
    <div class="modal-header">
        <a class="close" data-dismiss="modal">×</a>
        <h3>Following this user</h3>
    </div>
    <div class="modal-body">
        <p>Netmask unfollow</p>
    </div>
    <div class="modal-footer">
        <a href="#" class="btn btn-primary">Save changes</a>
        <a href="#" class="btn">Close</a>
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
 tuti
</script>


<scri bpt type="text/template" id="user">
    user
</script>

</body>
</html>
