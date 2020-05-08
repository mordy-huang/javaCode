
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>

<%--<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" pageEncoding="utf-8" import="java.util.*" %>--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
<html>
<head>
    <script>
        $(function () {
            $(".addFormSingle").submit(function () {
                if (checkEmpty("filepathSingle","图片文件")){
                     $("#filepathSingle").value("");
                     return true;
                }
                return false
            })
        });

        $(function () {
            $(".addFormDetail").submit(function () {
                if (checkEmpty("filepathDetail","图片文件")){
                    $("#filepathSingle").value("");
                    return true;
                }
                return false
            })
        });
    </script>

    <title>产品图片</title>
</head>
<body>
    <div class="workingArea">
        <ol class="breadcrumb">
           <li><a href="admin_category_list">所有分类</a></li>
           <li><a href="admin_product_list?cid=${p.category.id}">${p.category.name}</a></li>
           <li class="active">${p.name}</li>
           <li class="active">产品图片管理</li>
        </ol>

        <table>
            <tr>
                <td class="addPictureTableTD">
                    <div class="panel panel-warning addPictureDiv">
                        <div class="panel-heading">新增产品<b class="text-primary"> 单张 </b>图片</div>
                        <div class="panel-body">
                           <form method="post" class="addFormSingle" action="admin_productImage_add" enctype="multipart/form-data">
                            <table  class="addTable">
                                <tr>
                                    <td>请选择本地图片    尺寸400X400最佳</td>
                                </tr>
                                <tr>
                                    <td>
                                       <input id="filepathSingle" type="file" name="image">
                                    </td>
                               </tr>
                                <tr>
                                    <td>
                                        <input type="hidden" name="type" value="type_single">
                                        <input type="hidden" name="pid" value=${p.id}>
                                        <button type="submit" class="btn-success">提交</button>
                                    </td>
                                </tr>
                            </table>
                           </form>
                        </div>
                    </div>
                    <table  class="table table-striped table-bordered table-hover  table-condensed">
                        <thead>
                             <tr class="success">
                                 <th>ID</th>
                                 <th>单个产品图片缩略图</th>
                                 <th>删除</th>
                             </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${single}" var="s">
                            <tr>
                                <td>${s.id}</td>
                                <td><a title="点击查看原图" href="img/productSingle/${s.id}.jpg"><img height="50px" src="img/productSingle/${s.id}.jpg"></a></td>
                                <td><a href="admin_productImage_delete?id=${s.id}"><span class="glyphicon glyphicon-trash"></span></a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </td>

                <td class="addPictureTableTD">
                    <div class="panel panel-warning addPictureDiv">
                        <div class="panel-heading">新增产品<b class="text-primary"> 详情 </b>图片</div>
                        <div class="panel-body">
                            <form method="post" class="addFormDetail" action="admin_productImage_add" enctype="multipart/form-data">
                                <table  class="addTable">
                                    <tr>
                                        <td>请选择本地图片    宽度790 最佳</td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input id="filepathDetail" type="file" name="image">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="hidden" name="type" value="type_detail">
                                            <input type="hidden" name="pid" value=${p.id}>
                                            <button type="submit" class="btn btn-small">提交</button>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>
                    <table  class="table table-striped table-bordered table-hover  table-condensed">
                        <thead>
                        <tr class="success">
                            <th>ID</th>
                            <th>产品详情图片缩略图</th>
                            <th>删除</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${detail}" var="s">
                            <tr>
                                <td>${s.id}</td>
                                <td><a title="点击查看原图" href="img/productDetail/${s.id}.jpg"><img height="50px" src="img/productDetail/${s.id}.jpg"></a></td>
                                <td><a href="admin_productImage_delete?id=${s.id}"><span class="glyphicon glyphicon-trash"></span></a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </td>
            </tr>
        </table>
    </div>

</body>
</html>
