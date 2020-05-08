
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<div class="aliPayPageDiv">
    <div class="aliPayPageLogo">
        <img class="pull-left" src="img/site/simpleLogo.png">
        <div style="clear: both"></div>
    </div>

    <div>
        <span style="color: #8c8c8c">扫一扫付款</span>
        <span style="display: block; color: orangered;font-size: 21px;font-weight: bold;margin: 10px">
            ￥<fmt:formatNumber type="number" value="${param.total}" minFractionDigits="2"></fmt:formatNumber> </span>
    </div>

    <div>
        <img src="img/site/alipay2wei.png">
    </div>

    <div>
        <a href="forePayed?oid=${param.oid}&total=${param.total}">
        <button class="confirmPay">确认支付</button>
    </a>
    </div>
</div>