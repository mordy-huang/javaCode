<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java"  isELIgnored="false" pageEncoding="UTF-8" %>
<div >
    <a href="${contextPath}">
    <img id="simpleLogo" src="img/site/simpleLogo.png" class="simpleLogo">
    </a>
  <form action="foreSearch" method="post" >
    <div class="simpleSearchDiv pull-right">
        <input name="keyWord" type="text" value="${param.keyword}" placeholder="热血日漫  一拳超人 ">
        <button  type="submit" class="searchButton">搜天猫</button>
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
  <div style="clear: both"></div>
</div>