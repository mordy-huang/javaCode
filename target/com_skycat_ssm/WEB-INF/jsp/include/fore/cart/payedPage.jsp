<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<div class="payedDiv" >
    <div class="payedTextDiv">
        <img src="img/site/paySuccess.png">
        <span 您已成功付款></span>
    </div>
    <div class="payedAddressInfo">
        <ul>
            <li>收货地址:${order.address} 收件人:${order.receiver} 电话:${order.mobile}</li>
            <li>实付款：<span class="payedInfoPrice">
            ￥<fmt:formatNumber type="number" value="${param.total}" minFractionDigits="2"/>
            </li>
            <li>预计06月06日送达    </li>
        </ul>
        <div class="paedCheckLinkDiv">
            可以
            <a class="payedCheckLink" href="forebought">查看已买到的宝贝 </a>
            <a class="payedCheckLink" href="forebought">查看交易详情 </a>
        </div>
    </div>
    <div class="warningDiv">
            <img src="img/site/warning.png">
            <b>安全提醒：</b>下单后，<span class="redColor boldWord">其他渠道发送的连接都是假的哦 </span>天猫不存在系统升级，订单异常等问题，谨防假冒客服电话诈骗！
    </div>
</div>