<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员入口</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/buka/js/ie9.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/reset.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/commonv=20170705.css">
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
                    <a href="${pageContext.request.contextPath}/manager/managerIndex.jsp" title="管理" class="nav-btn active ">
                        管理
                    </a>
                    <a href="${pageContext.request.contextPath}/mySpace" title="个人中心" class="nav-btn  ">
                        个人中心
                    </a>
               </div>
				<form action="${pageContext.request.contextPath}/search" method="post"> 
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
	<!--导航条部分-->
	<style type="text/css">
		.main{
			width:1140px;
			background: #fff;
			margin:0 auto;
			padding:10px 0;
			overflow: hidden;
		}
		.modal-data{
			position: relative;
		}
		.modal-wrap{
			width:auto;
			height:auto;
		}
		.main ul li{
			float:left;
			margin:20px 40px 30px 40px;
		}
		ul .libox{
			width:300px;
			height:150px;
			box-sizing: border-box;
			padding:10px;
			background: #F8B62D;
			border-radius: 5px;
		}
		.libox p{
			font-size:17px;
			text-align: center;
			color:#fff;
			height:40px;
			line-height:40px;
			margin-top:10px;
		}
		.libox p:first-child{font-size:20px;}
	</style>
	<!--中间部分-->
	<div class="main box_shadow">
	<ul>
		<li>
				<a href="${pageContext.request.contextPath}/manager/insert.jsp">
					<div class="libox">
						<p>书籍添加</p>
						<p><strong>Add</strong></p>
					</div>
				</a>
		</li>
		<li>
				<a href="${pageContext.request.contextPath}/manager/manaQuery.jsp">
					<div class="libox">
						<p>书籍查询</p>
						<p><strong>Query</strong></p>
					</div>
				</a>
		</li>
		<li>
				<a href="${pageContext.request.contextPath}/manager/manaQuery.jsp">
					<div class="libox">
						<p>书籍信息修改</p>
						<p><strong>Alert</strong></p>
					</div>
				</a>
		</li>
		<li>
				<a href="${pageContext.request.contextPath}/manager/manaQuery.jsp">
					<div class="libox">
						<p>分卷内容添加</p>
						<p><strong>addContent</strong></p>
					</div>
				</a>
		</li>
		<li>
				<a href="${pageContext.request.contextPath}/userMana">
					<div class="libox">
						<p>会员管理</p>
						<p><strong>users</strong></p>
					</div>
				</a>
		</li>
			<li>
				<a href="${pageContext.request.contextPath}/manager/busiMana.jsp">
					<div class="libox">
						<p>事务处理</p>
						<p><strong>business</strong></p>
					</div>
				</a>
		</li>
	</ul>
	</div>
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
</body>
</html>