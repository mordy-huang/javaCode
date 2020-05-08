<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<style>
    img.logo1{
        /*position: absolute;*/
        /*left:300px;*/
        /*top: 40px;*/
        /*!*z-index: -1;*!*/
        /*!*z-index: -1;*!*/
        position: absolute;
        left: -40px;
        top: 0px;
    }
    div.searchOutDiv1{
        max-width: 1024px;
        margin: 0px auto;
        position: relative;
    }
</style>
<div class="searchOutDiv1">
<a href="${contextPath}">
    <img id="logo" src="img/site/logo.gif" class="logo1">
</a>
</div>
<form action="foreSearch" method="post">

    <div class="searchDiv">
        <input name="keyWord" type="text" placeholder="热血日漫   炼金术师">
        <button type="submit" class="searchButton">搜索</button>
        <div class="searchBelow">
            <c:forEach items="${cs}" var="c" varStatus="st">
                <c:if test="${st.count>=1 and st.count<=4}">
                    <span>
                        <a href="foreCategory?cid=${c.id}">
                                ${c.name}
                        </a>
                        <c:if test="${st.count!=4}">
                            <span>|</span>
                        </c:if>
                    </span>
                </c:if>
            </c:forEach>
        </div>
    </div>
</form>
