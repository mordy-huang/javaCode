
<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<div class="categoryMenu">
    <c:forEach items="${cs}" var="c">
        <div cid="${c.id}" class="eachCategory">
            <a href="foreCategory?cid=${c.id}"><span class="glyphicon glyphicon-link"></span>
                ${c.name}
            </a>
        </div>
    </c:forEach>
</div>