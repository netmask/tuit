<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

    <title><decorator:title default="Tuiter"/></title>
    <link rel="stylesheet" href="/resources/css/bootstrap.css"/>
    <link rel="stylesheet" href="/resources/css/bootstrap-responsive.css"/>
    <decorator:head/>
</head>
<body>

<div class="container">

    <div id="content">
        <decorator:body/>
    </div>

</div>

<script type="text/javascript" src="/resources/js/jquery-1.7.1.min.js" ></script>
<script type="text/javascript" src="/resources/js/bootstrap.js" ></script>
<script type="text/javascript" src="/resources/js/tuit.dashboard.js" ></script>

</body>
</html>