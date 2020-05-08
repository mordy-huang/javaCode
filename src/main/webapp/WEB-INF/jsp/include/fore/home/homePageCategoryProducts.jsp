<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="homepageCategoryProducts">
    <c:forEach items="${cs}" var="c" varStatus="stc">
        <div class="eachHomepageCategoryProducts">
            <div class="left-mark"></div>
            <span class="categoryTitle">${c.name}</span>
            <br>
            <c:forEach items="${c.products}" var="p" varStatus="stc">
                <%--                只显示5个产品--%>
                <c:if test="${stc.count<=5}">
                    <div class="productItem">
                        <a href="foreProduct?pid=${p.id}"> <img width="100px" src="img/productSingleMiddle/${p.firstProductImage.id}.jpg"></a>
                        <a class="productItemDescLink" href="foreProduct?pid=${p.id}">
                            <span class="productItemDesc">[热销]${fn:substring(p.name,0 ,20 )}
                            </span>
                            <span class="productPrice">
                               <fmt:formatNumber type="number" value="${p.promotePrice}" minFractionDigits="2"/>
                            </span>
                        </a>
                    </div>
                </c:if>
            </c:forEach>
            <div style="clear:both"></div>
        </div>
    </c:forEach>
    <img id="endpng" class="endpng" src="img/site/end.png">
</div>
