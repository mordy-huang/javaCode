<%--搜索界面--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<div id="searchResult">
    <div class="searchResultDiv">
        <div class="searchProducts">
            <c:forEach items="${ps}" var="p" varStatus="stc">
                <div class="productUnit" price="${p.promotePrice}">
                    <div class="productUnitFrame">
                        <a href="foreProduct?pid=${p.id}">
                            <img class="productImage" src="img/productSingleMiddle/${p.firstProductImage.id}.jpg">
                        </a>

                        <span class="productPrice">¥<fmt:formatNumber type="number" value="${p.promotePrice}" minFractionDigits="2"/></span>
                        <a class="productLink" href="foreproduct?pid=${p.id}">
                                ${fn:substring(p.name, 0,50)}
                        </a>

                        <a  class="tmallLink" href="foreproduct?pid=${p.id}">天猫专卖</a>

                        <div class="show1 productInfo">
                            <span class="monthDeal ">月成交 <span class="productDealNumber">${p.saleCount}笔</span></span>
                            <span class="productReview">评价<span class="productReviewNumber">${p.reviewCount}</span></span>
                            <span class="wangwang"><img src="img/site/wangwang.png"></span>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <c:if test="${empty ps}">
                <div class="noMatch">无满足条件产品</div>
            </c:if>
            <div style="clear:both"></div>
        </div>
    </div>
</div>