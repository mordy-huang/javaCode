
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" language="java" %>
<title>天猫假的 ${p.name}</title>
<div class="categoryPictureInProductPageDiv">
    <img class="categoryPictureInProductPage" src="img/category/${p.category.id}.jpg">
</div>
<div class="productPageDiv">
<%@include file="imgAndInfo.jsp"%>
<%--评价--%>
<%@include file="productReview.jsp"%>
<%--详情图--%>
<%@include file="productDetail.jsp"%>

</div>


