<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/10
  Time: 23:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<div class="category">
<div class="categoryPageDiv">
    <img src="img/category/${c.id}.jpg">
    <%@include file="sort.jsp"%>
    <%@include file="productByCategory.jsp"%>
</div>
</div>
