<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>事务管理</title>
</head>
	<script type="text/javascript" src="${pageContext.request.contextPath}/buka/js/ie9.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/reset.css">
		<link href="${pageContext.request.contextPath}/buka/css/common2.css" rel="stylesheet" type="text/css" media="all">
		<link href="${pageContext.request.contextPath}/buka/css/list.css" rel="stylesheet" type="text/css" media="all">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/commonv=20170705.css">
				<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/index.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/works.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/bookDiv.css"/>
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
			.messageError{
				color: red;
			}
			.messageRight{
			color:green;
			}
	</style>
		<script type="text/javascript">
		$(function(){
			$(":button[name='out']").click(function(){
		       var flag=confirm("确定借出吗?");
		      var bi_id=$(this).val();
		       if(flag == false){
		       	return false;
		       }
		       else{
		    var url="${pageContext.request.contextPath}/busiMana";
			var args={bi_id:bi_id,method:"out",time:new Date()};
$.get(url,args,function(data){
			})
			alert("借出成功");
		       }
			})
			
			$(":button[name='in']").click(function(){
				 var flag=confirm("确定入库吗?");
		      var bi_id=$(this).val();
		       if(flag == false){
		       	return false;
		       }
		       else{
		    var url="${pageContext.request.contextPath}/busiMana";
			var args={bi_id:bi_id,method:"in",time:new Date()};
$.get(url,args,function(data){
			})
			alert("入库成功");
		       } 
			})
		})
	</script>
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
				</form>
				</div>
			</div>
		</div>
	</header>
	<div class="page">
		<div class="width_240 pr floatl">
			<!--头像-->
			<div class="user_part user_meg box_shadow mar_bot">
    <div class="user_top"><a href="${pageContext.request.contextPath}/logout" class="logout" style="display: none;">退出</a>
            <img id="viewsHead" src="${sessionScope.user.pictureurl }" alt="">
    </div>
    <div class="user_cen">
    	<p class="author">${sessionScope.user.username }</p>
    </div>
</div>
<!---->
<div class="user_read box_shadow" style="top: 173px;">
    <ul>
        <li>
        	<a href="${pageContext.request.contextPath}/manager/insert.jsp">书籍添加</a></li>
        	<li><a href="${pageContext.request.contextPath}/manager/manaQuery.jsp">书籍查询</a></li>
        	<li><a href="${pageContext.request.contextPath}/manager/manaQuery.jsp">书籍信息修改</a></li>
        	<li><a href="${pageContext.request.contextPath}/manager/manaQuery.jsp">分卷内容添加</a></li>
        	<li><a href="${pageContext.request.contextPath}/userMana">会员管理</a></li>  
        	<li class="select"><a href="${pageContext.request.contextPath}/manager/busiMana.jsp">事务管理</a></li>  
    </ul>
</div>
		</div>
		<!--左边完毕-->
		<div class="width_880 floatr">
	<div class="manga_con box_shadow mar_bot">
               <div id="top" data-id="13502342" class="main-header td_lh clearfix">
               <p class="top_name">事务管理:</p>
               <br />
               <div class="search">
				<form  id="query" action="${pageContext.request.contextPath}/busiMana" method="post"> 
				<div class="ap_input clearfix">
                             <label class="ap_type fl" for="td_contact">请输入领书码：</label><input value="${param.code }" type="text" name="code" maxlength="32" class="input td_contact fl"  id="td_contact" size="35" required />
                      <button type="submit" class="btn_on">搜索</button>       
				</div>
				</form>
				</div>
			</div> 
			
           <div class="td_main minheight">
						<div class="applyTips"></div>
                        <div class="ap_form">
                                               	<br /><br /><br /><br />
       <c:choose>
                        	<c:when test="${requestScope.borrowinfo==null }">不存在该借阅信息或书籍已归还</c:when>
                        	<c:otherwise> 
<div class="book">
			<div class="bookPicture">
				<a href="bookInfo?id=${requestScope.book.id }" title="${requestScope.book.name }"><img src="${requestScope.book.pictureurl}" width="250" height="230"/></a>
			</div>
			<div class="bookName"><a href="bookInfo?id=${requestScope.book.id }" ><span class="bw1">书名:${requestScope.book.name }</span></a></div>
			<div class="bookType"><a href="search?type=${requestScope.book.id }" ><span class="bw1">类型:${requestScope.book.type }</span></a></div>
			<div class="bookAuthor"><a href="search?author=${requestScope.book.id }" ><span class="bw1">作者:${requestScope.book.author }</span></a></div>
			<div class="bookDescription"><span class="bw1"><%-- ${requestScope.book.description } --%></span></div>
			<div class="dangerDIV">	
			</div>
</div>
<!-- <div style="width: 100px;height: 1px;"></div> -->
用户:${requestScope.user.username }。邮箱:${requestScope.user.email }。
	操作时间：${requestScope.borrowinfo.createtime }。借出时间:
	<c:choose><c:when test="${requestScope.borrowinfo.result eq 0 }">
	<button class="btn_off">未借出。</button>
	</c:when>
	<c:otherwise>
	<button class="btn_off">${requestScope.borrowinfo.finishtime}。</button>
	</c:otherwise>
	</c:choose>
	领书码:${requestScope.borrowinfo.code}。
	<br /><br /><br />
	<c:choose>
	<c:when test="${requestScope.borrowinfo.result eq 0}">
			<button value="${requestScope.borrowinfo.id}" name="out" type="button" class="btn_on">借出</button>
			<button class="btn_off">在库内</button>
			</c:when>
			<c:otherwise>
			<button class="btn_off">已借出</button>
		<button value="${requestScope.borrowinfo.id}" name="in" type="button" class="btn_on">入库</button>
		</c:otherwise>
					</c:choose>
					</c:otherwise>
					</c:choose> 
					
                        		</div>
		</div>
		
		<!--中间完毕-->
	</div>
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
	</div>
</body>
</html>