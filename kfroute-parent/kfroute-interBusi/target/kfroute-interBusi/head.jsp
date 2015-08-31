<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<header role="banner">
    <div id="header_con">
        <div id="mer_name">
            <a href="#">尚果科技</a>
        </div>
        <nav id="menu2" class="menu" role="navigation">
            <ul>
                <li >
                    <a href="${ctx}/index!index.do">首页</a>
                </li>
                <li >
                    <a href="${ctx }/device!getDevicePage.do">设备</a>
                </li>
                <li>
                    <a href="${ctx }/pages/weizhan/weSit.jsp">微站</a>
                </li>
                <li >
                    <a href="${ctx }/orderBase!findAll.do">订单</a>
                </li>
                <li >
                    <a href="${ctx }/pages/diy/diy.jsp">DIY微站</a>
                </li>
            </ul>
        </nav>
<div id="mer_name_left">

<ul id="mobanwang_com" class="first-menu">
  
  <li><a href="#" target="_self">${session_user}▼</a>
    <ul style="display: none;" id="subMusic" class="second-menu">
      <li><a href="${sso_url }" class="mobanwang" target="_self">统一运营平台</a>
             </li>
      <li><a href="${sso_url }/logout" class="mobanwang" target="_self">退出</a>
     
      </li>
    </ul>
  </li>
 </ul></div>

        <div class="cls"></div>
    </div>
</header>