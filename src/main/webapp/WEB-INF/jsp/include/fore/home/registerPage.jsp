
<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<script>
    $(function () {
        <c:if test="${!empty msg}">
        $("span.errorMessage").html("${msg}");
        $("div.registerErrorMessageDiv").css("visibility","visible");
        </c:if>
        $(".registerFrom").submit(function () {
            if ($("#name").val().length==0){
                $("span.errorMessage").html("请输入用户名");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }
            if ($("#password").val().length==0){
                $("span.errorMessage").html("请输入密码");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }
            if ($("#password").val()!=$("#repeatpassword").val()){
                $("span.errorMessage").html("两次密码不相同");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }
            return true;
        });
    });

</script>

<form action="foreRegister" method="post" class="registerFrom">
    <div class="registerDiv">
        <div class="registerErrorMessageDiv">
            <div class="alert alert-danger" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
                <span class="errorMessage"></span>
            </div>
        </div>
    </div>
    <div class="registerDiv">
        <table class="registerTable" align="center">
            <tr>
                <td class="registerTip registerTableLeftTD"> 填写用户名</td>
                <td></td>
            </tr>
            <tr>
                <td class="registerTableLeftTD">登录名</td>
                <td class="registerTableRightTD"><input class="namaVal" name="name"  id="name" type="text" placeholder="会员名设置成功，无法修改"></td>
            </tr>
            <tr>
                <td class="registerTip registerTableLeftTD"> 设置登录密码</td>
                <td class="registerTableRightTD">登陆时验证，保护账号信息</td>
            </tr>
            <tr>
                <td class="registerTableLeftTD">登录密码</td>
                <td class=" registerTableRightTD"><input type="password" name="password" id="password" placeholder="请输入你的登陆密码"></td>
            </tr>
            <tr>
                <td class="registerTableLeftTD">确认密码</td>
                <td class=" registerTableRightTD"><input type="password"  id="repeatpassword" placeholder="请输入你的登陆密码"></td>
            </tr>
            <tr>
                <td colspan="2" class="registerButtonTD" align="center">
                   <a href="registerSuccess.jsp"></a> <button>提 交</button>
                </td>
            </tr>
        </table>
    </div>
</form>