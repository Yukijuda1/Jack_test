<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人空间</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
<script type="text/javascript" src="${pageContext.request.contextPath}/buka/js/ie9.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/reset.css">
		<link href="${pageContext.request.contextPath}/buka/css/common2.css" rel="stylesheet" type="text/css" media="all">
		<link href="${pageContext.request.contextPath}/buka/css/list.css" rel="stylesheet" type="text/css" media="all">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/commonv=20170705.css">
				<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/index.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/works.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/buka/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/buka/js/identity.js"></script>
	<style type="text/css">
		.modal_title{padding-bottom:10px;}
		.span_title{
			position: absolute;
			top:20px;
			left:30px;
			border-left:3px solid #F8B62D;
			text-indent: 10px;
			font-size: 16px;
			color:#666;
		}
	</style>
</head>
<body>
		<header class="head">
		<div class="head-wrap">
			<div class="head-center">
				<a href="${pageContext.request.contextPath}/index.jsp" class="head-logo"><img src="${pageContext.request.contextPath}/buka/img/logo.png"></a>
			</div>
			<div class="head-nav">
				<!--导航-->
				<div class="nav-lists">
					<a href="${pageContext.request.contextPath}/index.jsp" title="首页" class="nav-btn ">
							首页
					</a>
                    <a href="${pageContext.request.contextPath}/manager/managerIndex.jsp" title="管理" class="nav-btn  ">
                        管理
                    </a>
                    <a href="${pageContext.request.contextPath}/mySpace" title="个人中心" class="nav-btn active">
                        个人中心
                    </a>
               </div>

				<form action="search" method="post"> 
				<nav class="head-nav-right-banner">
						<input type="submit" title="搜索" class="tougao" value="搜索"></input>
								<div class="actionbtns" id="actionbtns">
					</div>
				</nav>
				<div class="search">
					<!-- <a href="#" id="search_btn" class="search_btn"></a> -->
					<input type="submit" class="search_btn" value=""/>
					<input type="text" id="search" class="search-input" placeholder="请输入搜索条件" value="" name="search">
				</div>
				</form>
			</div>
		</div>
	</header>
	<!--导航条-->
	<div class="page">
		<div class="width_240 pr floatl">
			<!--头像-->
			<div class="user_part user_meg box_shadow mar_bot">
    <div class="user_top"><a href="${pageContext.request.contextPath}/logout" class="logout" style="display: inline;">注销</a>
               <c:choose>
    <c:when test="${sessionScope.user.pictureurl==null }">
    <img id="viewsHead" src="${pageContext.request.contextPath}/imgs/noman.png" alt="${sessionScope.user.username}">
    </c:when>
    <c:otherwise>
            <img id="viewsHead" src="${sessionScope.user.pictureurl}" alt="${sessionScope.user.username}">
            </c:otherwise>
            </c:choose>
    </div>
    <div class="user_cen">
    	<p class="author">${sessionScope.user.username}</p>
    </div>
</div>
<!---->
<div class="user_read box_shadow" style="top: 173px;">
    <ul>
        <li class="select">
        	<a href="${pageContext.request.contextPath}/mySpace">会员基础信息</a></li>
        	<li><a href="${pageContext.request.contextPath}/business">操作信息</a></li>
        	<li><a href="${pageContext.request.contextPath}/history">历史记录</a></li> 
    </ul>
</div>
		</div>
		<!--左边完毕-->
		<div class="width_880 floatr">
	<div class="manga_con box_shadow mar_bot">
               <div id="top" data-id="13502342" class="main-header td_lh clearfix">
               <p class="top_name">会员信息</p>
			</div> 
           <div class="td_main minheight">
						<div class="applyTips"></div>
                 <!--表单位置-->
                 <div class="ap_form">
                 	<!--内容-->
                 <div class="width_240 pr floatl">
                 	<div class="user_part user_meg box_shadow mar_bot">
    <div class="user_top">
              <c:choose>
    <c:when test="${sessionScope.user.pictureurl==null }">
    <img id="viewsHead" src="${pageContext.request.contextPath}/imgs/noman.png" alt="${sessionScope.user.username}">
    </c:when>
    <c:otherwise>
            <img id="viewsHead" src="${sessionScope.user.pictureurl}" alt="${sessionScope.user.username}">
            </c:otherwise>
            </c:choose>
    </div>
    
</div> 	
                 </div>
                 
<div class="layui-upload">
  <button type="button" class="layui-btn" id="test1">上传头像</button>
  <div class="layui-upload-list">
    <img class="layui-upload-img" id="demo1">
    <p id="demoText"></p>
  </div>
</div> 
<div class="ap_input clearfix">
<label class="ap_type fl" for="td_contact">用户名：</label><input value="${sessionScope.user.username}" name="username" maxlength="32" class="input td_contact fl"  id="td_contact" size="35" disabled="disabled" />
<a href="${pageContext.request.contextPath}/forget.jsp" type="button" class="btn_on">修改密码</a>
</div>
<div class="ap_input clearfix">
<label class="ap_type fl" for="td_contact">会员等级：</label><input value="${requestScope.auth_name}" type="text" name="auth_name" maxlength="32" class="input td_contact fl"  id="td_contact" size="35" disabled="disabled" />
<c:choose>
<c:when test="${requestScope.auth_name=='manager'}"></c:when>
<c:otherwise>
<a href="${pageContext.request.contextPath}/authority" type="button" class="btn_on">成为管理员</a>
</c:otherwise>
</c:choose>
</div>
<div class="ap_input clearfix">
<label class="ap_type fl" for="td_contact">邮箱：</label><input value="${sessionScope.user.email}" type="text" name="email" maxlength="32" class="input td_contact fl"  id="td_contact" size="35" disabled="disabled" />
</div>
      <!--内容-->               
</div>
                 <!--表单位置-->
           </div>     
		</div>
		</div>
		<!--中间完毕-->
	</div>
	
	<!--底部-->
	<div class="clear"></div>
    <div class="footer">
		<style type="text/css">
		.concat-font li{
			margin-right:20px !important;
		}
		.gxb_link:hover{
			color:#2DA6DB;
			text-decoration: underline;
		}
	</style>
		<footer class="concat">
			<ul class="concat-font">
			<li>
				<span class="icon addr"></span>
				<div class="concat-list">
					<p style="text-align: left;">地址：浙江理工大学</p>
					<p>
						课题：图书馆管理网站
					</p>
					<p>
					刘萧然
					</p>
				</div>
			</li>
			<li>
				<span class="icon email"></span>
				<div class="concat-list">
					<p style="text-align: left;">
						邮箱：512748799@qq.com
					</p>
					<p style="text-align: left;">
						QQ：<a href="tencent://AddContact/?fromId=50&fromSubId=1&subcmd=all&uin=512748799">512748799</a>
					</p>
					<!--<p style="text-align: left;">
						合作：<a href="mailto:DB@buka.cn">bd@buka.cn</a>
					</p>-->
				</div>
			</li>
			<li>
				<span class="icon phone"></span>
				<div class="concat-list">
					<p style="text-align: left;">
						电话:
					</p>
					<p style="text-align: left;">
						15067148656
					</p>
				</div>
			</li>
			<li>
				<span class="icon global"></span>
				<div class="concat-list">
					<p style="text-align: left;">
						公网id:111.231.104.48
					</p>
					<!--<p style="text-align: left;position:relative">
						<span class="icon wwwen"></span>网络文化经营许可证:<a target="_blank" class="gxb_link" href="http://c-r6.sosobook.cn/static/www.buka.cn/img/wenwangwenzheng.png">粤网文[2015]2094-438号</a>
					</p>-->
				</div>
			</li>	
		</ul>
		</footer>
	</div>
	<script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
<script>
	layui.use('upload', function(){
  var $ = layui.jquery
  ,upload = layui.upload;
	 var uploadInst = upload.render({
    elem: '#test1'
    ,url: '${pageContext.request.contextPath}/headpicture'
    ,before: function(obj){
      //预读本地文件示例，不支持ie8
      obj.preview(function(index, file, result){
        $('#demo1').attr('src', result); //图片链接（base64）
      });
    }
    ,done: function(res){
      //如果上传失败
 if(res.code > 0){
        return layer.msg('上传失败');
      }
      else{
    	  return layer.msg('上传成功');  
      } 
      //上传成功
    }
  });
	})
</script>
</body>
</html>