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
                        if(data.code=="300"){
                            $("#checkOldPassMsg").html(${data.msg})
                        }
                    }
                });
            }
        });
     });
</script>
<script>layui.use(['form', 'layer'],
    function() {
        $ = layui.jquery;
        var form = layui.form,
            layer = layui.layer;
        //监听提交
        form.on('submit(save)', function() {
                //发异步，把数据提交给php
                layer.alert("修改成功", {
                        icon: 6
                    },
                    function() {
                        // 获得frame索引
                        var index = parent.layer.getFrameIndex(window.name);
                        //关闭当前frame
                        parent.layer.close(index);
                    });
                return false;
            });

    });</script>
<script>var _hmt = _hmt || []; (function() {
    var hm = document.createElement("script");
    hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(hm, s);
})();</script>
</body>

</html>
