<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!--shiro标签-->
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html class="x-admin-sm">
    <head>
        <meta charset="UTF-8">
        <title>学生选课系统后台</title>
        <jsp:include page="main/head.jsp"/>
    </head>
    <body class="index">
        <!-- 顶部开始 -->
        <div class="container">
            <div class="logo">
                <a href="${adminContext}/index/list">学生选课系统</a></div>
            <div class="left_open">
                <a><i title="展开左侧栏" class="iconfont">&#xe699;</i></a>
            </div>
            <ul class="layui-nav right" lay-filter="">
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <c:if test="${null!=username}">
                            <shiro:hasRole name="学生">
                                <i class="fas fa-user"></i>
                            </shiro:hasRole>
                            <shiro:hasRole name="老师">
                                <i class="fas fa-user-circle"></i>
                            </shiro:hasRole>
                            <shiro:hasRole name="管理员">
                                <i class="fas fa-user-plus"></i>
                            </shiro:hasRole>
                            &nbsp;${username}
                        </c:if>
                    </a>
                    <dl class="layui-nav-child">
                        <!-- 二级菜单 -->
                        <dd>
                            <a onclick="xadmin.add_tab('个人信息','${adminContext}/user/showMsg')">
                                <i class="fas fa-info"></i>
                                <cite>用户信息</cite>
                            </a>
                        </dd>
                        <c:if test="${null != username}">
                           <shiro:hasAnyRoles name="学生,老师">
                            <dd>
                                <a onclick="xadmin.add_tab('修改密码','${adminContext}/user/updatePassword')">
                                    <i class="fas fa-user-lock"></i>
                                    <cite>修改密码</cite>
                                </a>
                            </dd>
                           </shiro:hasAnyRoles>
                        </c:if>
                        <dd>
                            <a onclick="xadmin.add_tab('帮助手册','welcome1.html')">
                                <i class="fas fa-question-circle"></i>
                                <cite>帮助</cite>
                            </a>
                        </dd>
                            <a href="${adminContext}/logout">
                                <i class="fas fa-power-off"></i>
                                退出</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item to-index">
                    <a href="http://www.ndkj.com.cn/">官网</a></li>
            </ul>
        </div>
        <!-- 顶部结束 -->
        <!-- 中部开始 -->
        <!-- 左侧菜单开始 -->
        <div class="left-nav">
            <div id="side-nav">
                <ul id="nav">
                    <c:if test="${null != parentCert}">
                        <c:forEach var="parent" items="${parentCert}">
                            <li>
                                <a href="javascript:void(0);">
                                    <i class="fas ${parent.certPic}"></i>
                                    <cite>${parent.certName}</cite>
                                    <i class="iconfont nav_right">&#xe697;</i></a>
                                <ul class="sub-menu">
                                    <c:if test="${null!=parent.childrenCert}">
                                        <c:forEach items="${parent.childrenCert}" var="child">
                                                <li>
                                                    <a onclick="xadmin.add_tab('${child.certName}','${adminContext}/${child.certUrl}')">
                                                        <i class="iconfont">&#xe6a7;</i>
                                                        <cite>${child.certName}</cite>
                                                    </a>
                                                </li>
                                        </c:forEach>
                                    </c:if>
                                </ul>
                            </li>
                        </c:forEach>
                    </c:if>
                </ul>
            </div>
        </div>
        <!-- <div class="x-slide_left"></div> -->
        <!-- 左侧菜单结束 -->
        <!-- 右侧主体开始 -->
        <div class="page-content">
            <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
                <ul class="layui-tab-title">
                    <li class="home">
                        <i class="layui-icon">&#xe68e;</i>我的桌面</li></ul>
                <div class="layui-unselect layui-form-select layui-form-selected" id="tab_right">
                    <dl>
                        <dd data-type="this">关闭当前</dd>
                        <dd data-type="other">关闭其它</dd>
                        <dd data-type="all">关闭全部</dd></dl>
                </div>
                <div class="layui-tab-content">
                    <div class="layui-tab-item layui-show">
                        <iframe src='${adminContext}/index/main' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
                    </div>
                </div>
                <div id="tab_show"></div>
            </div>
        </div>
        <div class="page-content-bg"></div>
        <style id="theme_style"></style>
        <!-- 中部结束 -->
    </body>

</html>