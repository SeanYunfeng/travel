<%@ page import="javax.lang.model.element.VariableElement" %>
<%@ page import="cn.itcast.travel.domain.User" %>
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
        
    </style>
    <script>
        //检查用户填写的邮箱格式否标准
        function checkEmail() {
            //1.获取邮箱
            const email = $("#email").val();
            //2.定义正则表达式
            const reg_email=/^\w+@\w+\.\w+$/;
            //3.判断，给出提示信息
            const flag = reg_email.test(email);
            if(!flag){
                //邮箱效验成功
                alert("邮箱填写不符合格式")
                $("#email").val("");
            }
            return flag;
        }
        $(function () {
            $("#changeIm").submit(function () {
                return checkEmail();
            });
            $("#updatePh").click(function () {
                location="updatePh.jsp";
            });
        })
        //失去焦点时调用检测方法
        $("#email").blur(checkEmail);

    </script>
    
</head>
<body>
<!--引入头部-->
<div id="header"></div>
<!-- 头部 end -->
<div class="rg_layout">
    <div class="rg_form clearfix">
        <div class="rg_form_left">
            <p>用户信息展示</p>
            <p>USER INFORMATION</p>
        </div>
        <div class="rg_form_center" >

            <!--注册表单-->
            <form id="changeIm" action="User/changeUserInfo" method="post">
                <!--提交处理请求的标识符-->
                <input type="hidden" name="action" value="register">
                <table style="margin-top: 100px;" >
                    <tr>
                        <td class="td_left">
                            <label> 用户头像:&emsp;&emsp; </label>
                        </td>
                        <td class="td_right" >
                            <c:choose>
                                <c:when test="${empty User.uimage}">
                                    <img  src="images/jiangxuan_5.jpg"  class="img-circle">
                                </c:when>
                                <c:when test="${not empty User.uimage}">
                                    <img src="img/userImg/${User.uimage}" class="img-circle" />
                                </c:when>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="username">用户名:&emsp;&emsp; </label>
                        </td>
                        <td class="td_right" >
                            <input  type="text" id="username" name="username" value="${User.username}">
                        </td>
                    </tr>

                    <tr>
                        <td class="td_left">
                            <label for="email">Email:&emsp;&emsp; </label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="email" name="email" value="${User.email}" >
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="name">真实姓名:&emsp;&emsp; </label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="name" name="name" value="${User.name}">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="telephone">手机号:&emsp;&emsp; </label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="telephone" name="telephone" value="${User.telephone}">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="sex">性别:&emsp;&emsp; </label>
                        </td>
                        <td class="td_right gender">
                            <c:choose>
                                <c:when test="${User.sex=='男'}">
                                    <input type="radio" id="sex" name="sex" value="男" checked> 男
                                    <input type="radio" name="sex" value="女" > 女
                                </c:when>
                                <c:when test="${User.sex=='女'}">
                                    <input type="radio" id="sex" name="sex" value="男" > 男
                                    <input type="radio" name="sex" value="女" checked> 女
                                </c:when>
                            </c:choose>
                        </td>
                    </tr>

                    <tr>

                        <td class="td_right check" style="margin-left: 10px">
                            <input type="submit" class="btn btn-warning"  value="修改信息">
                        </td>
                        <td class="td_right check">
                            <input type="button" class="btn btn-warning" id="updatePh" value="上传头像">
                        </td>

                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
<!--引入尾部-->
<div id="footer"></div>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="js/include.js"></script>

</body>
</html>