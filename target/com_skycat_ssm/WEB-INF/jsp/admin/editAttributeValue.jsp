<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/2
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  import="java.util.*"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
<script src="js/jquery/2.0.0/jquery.min.js"></script>
<html>
<head>
    <title>编辑属性值</title>
<script>
    $(function () {
        $("input.avValue").keyup(function () {
            // 得到input类的value值
            var value=$(this).val();
            //设置跳转链接
            var url = "admin_attributeValue_update";
            //得到id值
            var id=$(this).attr("avid");
            var parentSpan = $(this).parent("span");
            parentSpan.css("border","1px solid yellow");
            $.ajax({
                url: url,
                type:"POST",
                data:{"id":id,"value":value},
                success:function(result){
                    // alert(result);
                    if(result=="success"){
                        parentSpan.css("border","1px solid blue")
                    }
                    else{
                        parentSpan.css("border","1px solid red")
                    }
                },
                error:function () {
                    alert("error...");
                },
                dataType:"text"//设置接受到的响应数据的格式
            });
        });
    });


    // $(function() {
    //     $("input.pvValue").keyup(function(){
    //         var value = $(this).val();
    //         var page = "admin_propertyValue_update";
    //         var pvid = $(this).attr("pvid");
    //         var parentSpan = $(this).parent("span");
    //         parentSpan.css("border","1px solid yellow");
    //         $.post(
    //             page,
    //             {"value":value,"id":pvid},
    //             function(result){
    //                 if("success"==result)
    //                     parentSpan.css("border","1px solid green");
    //                 else
    //                     parentSpan.css("border","1px solid red");
    //             }
    //         );
    //     });
    // });

</script>
</head>
<body>

<div class="workingArea">
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li><a href="admin_attribute_list?cid=${product.category.id}">${product.category.name}</a></li>
        <li class="active">${product.name}</li>
        <li class="active">编辑产品属性</li>
    </ol>
<div class="editDiv">
    <c:forEach items="${attributeValues}" var="av"  >
        <div class="eachPV">
            <span class="pvName">${av.attribute.NAME}</span>
            <span class="pvValue">
                <input class="avValue" type="text" value="${av.value}" avid="${av.id}">
            </span>
        </div>
    </c:forEach>
    <div style="clear:both"></div>
</div>
</div>
</body>
</html>
