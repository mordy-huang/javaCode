<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/3
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="a" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="workingArea">
    <h1 class="label label-info" >用户管理</h1>

    <br>
    <br>

    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover table-condensed">
            <thead>
            <tr class="warning">
                <td>ID</td>
                <td>用户名称</td>
            </tr>
            </thead>
            <tbody>

                    <a:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                    </tr>
                    </a:forEach>

            </tbody>
        </table>
    </div>
    <div class="text-center">
        <%@include  file="../include/admin/adminPage.jsp"%>
    </div>
</div>
</body>
</html>
