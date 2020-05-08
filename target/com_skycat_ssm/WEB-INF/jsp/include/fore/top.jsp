<%@ taglib prefix="a" uri="http://java.sun.com/jsp/jstl/xml" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<nav class="top">
    <a href="${contextPath}">
        <span style="color:#C40000;margin:0px" class="glyphicon glyphicon-home redColor"/>
        天猫首页
    </a>
    <span>Hello 欢迎来到天猫</span>


    <c:if test="${!empty user}">
        <a href="loginPage">${user.name}</a>
        <a href="foreLogout">退出</a>
    </c:if>

    <c:if test="${empty user}">
        <a href="LoginPage">请登录</a>
        <a href="RegisterPage">免费注册</a>
    </c:if>

    <span class="pull-right">
        <a href="forebought">我的订单</a>
        <a href="foreCart">
            <span style="color:#c7254e;margin:0px" class="glyphicon glyphicon-shopping-cart redColor"/>
            购物车
            <strong>
            <c:if test="${empty cartProductNumber}">0</c:if>
            <c:if test="${!empty cartProductNumber}">${cartProductNumber}</c:if>
            </strong>件
        </a>
    </span>
</nav>
