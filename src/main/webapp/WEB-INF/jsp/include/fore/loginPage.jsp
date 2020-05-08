<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/9
  Time: 0:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<script>
$(function(){
    <c:if test="${!empty msg}">
    $("span.errorMessage").html("${msg}");
    $("div.loginErrorMessageDiv").show();
    </c:if>

    $("form.loginForm").submit(function () {
        if ($("#password").val().length==0||$("#name").val().length==0){
            $("span.errorMessage").html("账号或密码不能为空");
            $("div.loginErrorMessageDiv").show();
            return false;
        }
        return true;
    });

    $(function () {
        var left=window.innerWidth/2+162;
        $("div.loginSmallDiv").css("left",left);
    });
})

</script>
<div class="loginDiv" style="position: relative">
    <div class="simpleLogo">
       <a href="#"><img src="img/site/simpleLogo.png"></a>
    </div>
    <img id="loginBackgroundImg" class="loginBackgroundImg" src="img/site/loginBackground.png">
    <form class="loginForm" action="forelogin" method="post">
      <div id="loginSmallDiv" class="loginSmallDiv">

          <div class="loginErrorMessageDiv">
              <div class="alert alert-danger" >
                  <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
                  <span class="errorMessage"></span>
              </div>
          </div>

          <div class="login_account_text">账户登录</div>
          <div class="loginInput" >
                <span class="loginInputIcon">
                    <span class=" glyphicon glyphicon-user"></span>
                </span>
              <input id="name" name="name" placeholder="手机/会员名/邮箱" type="text">
          </div>

          <div class="loginInput">
            <span class="loginInputIcon">
                <span class="glyphicon glyphicon-lock" ></span>
            </span>
           <input  name="password" id="password" placeholder="密码" type="text" type="password">
         </div>

          <span class="text-danger">请不要输入真的账号密码哦！</span>

          <div>
            <a href="#" class="foginPassword">忘记登录密码</a>
            <a class="pull-right" href="RegisterPage">免费注册</a>
          </div>

          <div style="margin-top: 20px">
            <button class="btn btn-block redButton" type="submit">登录</button>
          </div>

      </div>
    </form>
</div>
