<%--
  Created by IntelliJ IDEA.
  User: 云峰
  Date: 2020/12/28
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.lang.model.element.VariableElement" %>
<%@ page import="cn.yunfeng.travel.domain.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>用户信息展示</title>
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" href="css/register.css">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!--导入jquery-->
    <script src="js/jquery-3.3.1.js"></script>
    <style>
        .rg_form_center{
            margin-top: 30px;
        }
        .img-circle{
            width: 50px;
            height: 50px;
        }
        .td_right{
            margin-left: 50px;
        }
        .uploadFile{

        }

    </style>
    <script>
        function validate() {//判断是否上传时有值
            var headShot = document.getElementById("headShot");
            if (headShot.value == "") {
                alert("请选择要上传的头像！");
                headShot.focus();
                return false;
            }
            return true;
        }

        $(function () {
            $("#headShot").change(function () {
                var input = document.getElementById("headShot");
                var fReader = new FileReader();
                fReader.readAsDataURL(input.files[0]);
                fReader.onloadend = function(event){
                    var img = document.getElementById("uploadImg");
                    img.src = event.target.result;
                }
            });

        })

    </script>


</head>
<body>
<!--引入头部-->
<div id="header"></div>
<!-- 头部 end -->
<div class="rg_layout">
    <div class="rg_form clearfix">

        <div class="rg_form_center" >
            <div style="margin-left: 100px" >
                <form action="User/fileUpload" method="post"
                      enctype="multipart/form-data" onsubmit="return validate();">
                    <div class="table_style" style="margin-left: 150px;">
                        <div class="uploade">
                            <div align="center">
                                <img style="width: 200px;height: 200px" src="img/userImg/${sessionScope.User.uimage}"  class="img-circle" id="uploadImg"/>
                                <p>&nbsp;</p>
                                <input class="uploadFile" name="headShot" id="headShot" type="file" value="上传照片">
                            </div>
                        </div>
                        <div align="center" style="margin-top: 20px;">
                            <input style="margin-left: 30px"  type="submit" class="btn btn-info" value="保存">
                        </div>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>
<!--引入尾部-->
<div id="footer"></div>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="js/include.js"></script>

</body>
</html>
