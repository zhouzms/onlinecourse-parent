<%--
  Created by IntelliJ IDEA.
  User: zms
  Date: 2019/12/19
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.2</title>
    <jsp:include page="head.jsp"/>
<body>
<div class="layui-fluid">
    <div class="layui-row">
        <div class="layui-col-md4 layui-col-md-offset3">
            <form class="layui-form" style="margin-top: 50px;">
            <div class="layui-form-item">
                <label for="L_username" class="layui-form-label">姓名</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_username" name="username" disabled="" value="${user.realName}" class="layui-input"></div>
            </div>
            <div class="layui-form-item">
                <label for="L_repass" class="layui-form-label">
                    <span class="x-red">*</span>旧密码</label>
                <div class="layui-input-inline">
                    <input type="password" id="oldpass" name="oldpass" required="" lay-verify="required" autocomplete="off" class="layui-input"></div>
                <div class="layui-form-mid" id="checkOldPassMsg" style="color: red;"></div>
            </div>
            <div class="layui-form-item">
                <label for="L_pass" class="layui-form-label">
                    <span class="x-red">*</span>新密码</label>
                <div class="layui-input-inline">
                    <input type="password" id="L_pass" name="newpass" required="" lay-verify="required" autocomplete="off" class="layui-input"></div>
                <div class="layui-form-mid layui-word-aux">密码大于6位由数字和字母组成</div>
            </div>
            <div class="layui-form-item">
                <label for="L_repass" class="layui-form-label">
                    <span class="x-red">*</span>确认密码</label>
                <div class="layui-input-inline">
                    <input type="password" id="L_repass" name="repass" required="" lay-verify="required" autocomplete="off" class="layui-input"></div>
            </div>
            <div class="layui-form-item">
                <label for="L_repass" class="layui-form-label"></label>
                <button class="layui-btn" lay-filter="save" lay-submit>修改</button></div>
        </form>
        </div>
    </div>
</div>
<script>
    var flag;
    <!--检查原密码-->
     $(function () {
        $("#oldpass").blur(function () {
            var oldpass=$("#oldpass").val();
            if(oldpass !=null && oldpass!=''){
                $.ajax({
                    type: "post",
                    url: "${adminContext}/user/checkPassword",
                    data: {"oldpass":oldpass},
                    dataType: "json",
                    success:function(data){
                        if(data.code==="300"){
                            $("#checkOldPassMsg").text(data.msg);
                            $("#oldpass").focus();
                            flag=false;
                        }
                        if(data.code==="200"){
                            $("#checkOldPassMsg").text(data.msg);
                            flag=true;
                        }
                    }
                });
            }
        });
     });
     <!--修改密码请求-->
    layui.use(['form', 'layer'],
    function() {
        $ = layui.jquery;
        var form = layui.form,
            layer = layui.layer;
        //监听提交
        form.on('submit(save)', function() {
            //检查两次密码一致性
            var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}$/;
            var l_pass=$("#L_pass").val();
            var rel_pass=$("#L_repass").val();
            if(!reg.test(l_pass)){
                layer.alert("密码长度要大于6位，由数字和字母组成");
                $("#L_pass").focus();
                return false;
            }
            if(l_pass!=rel_pass){
                layer.alert("两次密码不一致");
                $("#L_pass").focus();
                return false;
            }
                //发异步
            $.ajax({
                type: "post",
                url: "${adminContext}/user/upNewPassword",
                data: {"password":l_pass},
                dataType: "json",
                success:function(data){
                    if(data.code=="200"){
                        layer.alert(data.msg, {icon: 6});
                        setTimeout("goTo(\"${adminContext}/logout\")",700);
                    }else {
                        layer.alert(data.msg);
                    }
                }
            });
                return false;
            });
    });
    function goTo(url){
        top.location.href=url;
    }
</script>
</body>

</html>
