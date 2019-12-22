<%--
  Created by IntelliJ IDEA.
  User: zms
  Date: 2019/11/23
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>在线选课</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="${adminContext}/static/plugins/fontawesome-free/css/all.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- icheck bootstrap -->
    <link rel="stylesheet" href="${adminContext}/static/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${adminContext}/static/dist/css/adminlte.min.css">
    <link rel="stylesheet" href="${adminContext}/static/layer/theme/default/layer.css">
</head>
<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
       <b>在线选课系统</b>后台</a>
    </div>
    <!-- /.login-logo -->
    <div class="card">
        <div class="card-body login-card-body">
                    <div class="alert alert-danger alert-dismissible" role="alert" id="msg" style="display: none;">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span></button>
                     &nbsp;&nbsp; <span id="alertmsg"></span>
                    </div>

            <form method="post" action="${adminContext}/user/login/list" id="submitform">
                <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="请输入学号或工号" id="username" name="username">
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-address-card"></span>
                        </div>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <input type="password" class="form-control" placeholder="大于6位-由数字和字母组成" id="password" name="password">
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-lock"></span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-8">
                        <div class="icheck-primary">
                            <input type="checkbox" id="remember" name="rememberMe" checked>
                            <label for="remember">
                                记住我
                            </label>
                        </div>
                    </div>
                    <!-- /.col -->
                    <div class="col-4">
                        <input type="button" class="btn btn-primary btn-block " id="mysub" value="登录"/>
                    </div>
                    <!-- /.col -->
                </div>
            </form>

            <div class="social-auth-links text-center mb-3">
                <p>- OR -</p>
                <a id="github" class="btn btn-block btn-info" style="color: whitesmoke;">
                    <i class="fab fa-github-square mr-2"></i> github
                </a>
            </div>
            <p class="mb-1">
                <a href="#">忘记密码？</a>
            </p>
        <input type="hidden" id="hidden" value="${ajaxResult.code}"/>
        </div>
    </div>
</div>
<!-- /.login-box -->

<!-- jQuery -->
<script src="${adminContext}/static/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="${adminContext}/static/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="${adminContext}/static/dist/js/adminlte.min.js"></script>
<!--layer-->
<script src="${adminContext}/static/layer/layer.js"></script>
<script type="text/javascript">
    var indexs;
    $(function () {
        document.onkeydown =function (){
            if (event.keyCode == 13) {
                checkForm();
            }
        };
        $("#mysub").click(function () {
            checkForm();
        });
        function checkForm() {
            //如果为管理员登录，直接放行
            if(($("#password").val()=="admin"&&$("#username").val()=="admin")){
                $("#submitform").submit();
                indexs=layer.load(0, {shade: false});
                return ;
            }
            if($("#username").val().length==0){
                $("#msg").css("display","block");
                $("#alertmsg").text("编号不为空");
                $("#username").focus();
                return false;
            }
            //验证是否为数字
            var num = /^\d+$/;
            if(!num.test($("#username").val())){
                $("#msg").css("display","block");
                $("#alertmsg").text("编号为数字");
                $("#username").focus();
                return false;
            }
            if($("#password").val().length===0){
                $("#msg").css("display","block");
                $("#alertmsg").text("密码不为空");
                $("#password").focus();
                return false;
            }
            var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}$/;
            if(!reg.test($("#password").val())){
                $("#msg").css("display","block");
                $("#alertmsg").text("密码长度要大于6位，由数字和字母组成");
                $("#password").focus();
                return false;
            }
            $("#submitform").submit();
            indexs=layer.load(0, {shade: false});
        }
        //接收后台的错误
       var error=$("#hidden").val();
          if(error!=""){
              layer.close(indexs);
             if(error=="200"){
                 window.location.href="${adminContext}/index/list";
             }
             if(error=="400"){
                 $("#msg").css("display","block");
                 $("#alertmsg").text("账号有误，请重新输入");
                 $("#username").focus();
             }
              if(error=="500"){
                  $("#msg").css("display","block");
                  $("#alertmsg").text("密码有误，请重新输入");
                  $("#username").focus();
              }
              if(error=="800"){
                  $("#msg").css("display","block");
                  $("#alertmsg").text("未知原因！请稍后再试");
                  $("#username").focus();
              }
          }
          $("#github").click(function () {
              layer.load(1);
              window.location.href="https://github.com/login/oauth/authorize?client_id=7880c46b007cced27620"
          });
    });
</script>
</body>
</html>

