
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
<script>
    $(function () {
        $("button.orderPageCheck").click(function () {
            var oid =$(this).attr("oid");
            $("tr.orderPageOrderItemTR[oid="+oid+"]").toggle();
        });
    });
</script>
<html>
<head>
    <title>Title</title>

</head>
<body>
 <div class="workingArea">
    <h1 class="label label-info" >订单管理</h1>
    <br><br>
    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover table-condensed">
            <thead>
            <tr class="success">
                <td>ID</td>
                <td>状态</td>
                <td>金额</td>
                <td>商品数量</td>
                <td>买家名称</td>
                <td>创建时间</td>
                <td>支付时间</td>
                <td>发货时间</td>
                <td>确认收货时间</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.statusDesc}</td>
                    <td>￥${order.orderMoney}</td>
                    <td>${order.orderNumber}</td>
                    <td>${order.user.name}</td>

                    <td><fmt:formatDate value="${order.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td><fmt:formatDate value="${order.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td><fmt:formatDate value="${order.deliveryDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td><fmt:formatDate value="${order.confirmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>
                        <button href="#" oid="${order.id}" class="orderPageCheck btn btn-primary btn-xs" >查看详情</button>
                        <c:if test="${order.status=='waitDelivery'}">
                            <a href="admin_Order_Delivery?id=${order.id}&page=${page.start}">  <button  class="btn btn-primary btn-xs" >发货</button></a>

                        </c:if>
                    </td>
                </tr>

                <tr class="orderPageOrderItemTR" oid="${order.id}">
                    <td colspan="10" align="center" >
                      <div class="orderPageOrderItem">
                        <table width="800px" align="center" class="orderPageOrderItemTable">
                            <c:forEach items="${order.orderItems}" var="oi">
                                <tr>
                                    <td align="left">
                                        <img width="50px" height="50px" src="img/productSingle/${oi.product.firstProductImage.id}.jpg">
                                    </td>
                                    <td align="right">
                                        <a href="#">${oi.product.name}</a>
                                    </td>

                                    <td align="right">
                                          <span class="text-muted">${oi.number}个</span>
                                    </td>
                                    <td align="right">
                                        <span class="text-muted">单价${oi.product.promotePrice}</span>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                       </div>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
    <div class="text-center">
        <%@include  file="../include/admin/adminPage.jsp"%>
    </div>
 </div>
</body>
</html>
