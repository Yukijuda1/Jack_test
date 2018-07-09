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
	<script type="text/javascript">
	$(function(){
		
		$(":button[name='delete']").click(function(){
			var $this=$(this);
			var id=$(this).val();
			var flag=confirm("确定删除吗?");
			if(flag == false){
				return false;
			}
			else{
				var url="${pageContext.request.contextPath}/userMana";
				var args={id:id,method:"delete",time:new Date()};
				$.post(url,args,function(data){	
					})	
		$this.parent("div").remove();
			}
		})
		
	})
	</script>
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
				<input type="submit" title="搜索" class="tougao" value="搜索"></input>								<div class="actionbtns" id="actionbtns">
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
        <li>
        	<a href="${pageContext.request.contextPath}/mySpace">会员基础信息</a></li>
        	<li class="select"><a href="${pageContext.request.contextPath}/business">操作信息</a></li>
        	<li><a href="${pageContext.request.contextPath}/history">历史记录</a></li> 
    </ul>
</div>
		</div>
		<!--左边完毕-->
		<div class="width_880 floatr">
	<div class="manga_con box_shadow mar_bot">
               <div id="top" data-id="13502342" class="main-header td_lh clearfix">
               <p class="top_name">操作信息</p>
			</div> 
           <div class="td_main minheight">
						<div class="applyTips"></div>
                 <!--table位置-->
                 <legend>借书信息:</legend>
                 <c:choose>
                 <c:when test="${requestScope. borrowBookList==null}">暂无借书信息~</c:when>
                 <c:otherwise>
<table border="1" id="dataTable"  style=" border-collapse:collapse;border-spacing:0; border:1px  solid  #FFFFFF;" >
            <tr  style="background:#50aca2;color: #fff;border-bottom-width: 0;  text-align: center; height: 2.2rem;">
                <th>书名</th>
                <th>地址</th>
                 <th>领取码 </th>
                 <th>操作时间</th>
                  <th>领取时间</th>
            </tr>
  <c:forEach items="${requestScope.borrowBookList }" var="bb">
            <tr   style="background: #e5efeb; text-align: center;height: 2.2rem;">
                <td>${bb.name }</td>
                <td>${bb.location }</td>
                <td>&nbsp; ${bb.code }</td>
                <td>&nbsp; ${bb.createtime }</td>
                <c:choose>
                <c:when test="${bb.result eq 0 }">
                <td>&nbsp;未领取</td>
                </c:when>
                <c:otherwise>
                 <td>&nbsp;${bb.finishtime }</td>
                </c:otherwise>
                </c:choose>
            </tr>
</c:forEach>
 </table>
 </c:otherwise>
 </c:choose>
 <!--借书table-->
 <hr />
 <legend>还书信息:</legend>
 <c:choose>
 <c:when test="${requestScope.returnBookList==null}">暂无还书信息~</c:when>
 <c:otherwise>
  <table border="1" id="dataTable"  style=" border-collapse:collapse;border-spacing:0; border:1px  solid  #FFFFFF;" >
            <tr  style="background:#50aca2;color: #fff;border-bottom-width: 0;  text-align: center; height: 2.2rem;">
                <th>书名</th>
                 <th>操作时间</th>
            </tr>
  <c:forEach items="${requestScope.returnBookList }" var="rb">
            <tr   style="background: #e5efeb; text-align: center;height: 2.2rem;">
                <td>${rb.name }</td>
                <td>${rb.createtime }</td>
                <td><a href="borrowInfo?id=${rb.book_id }"><button class="layui-btn layui-btn-primary">再次借阅</button></a></td>
            </tr>
</c:forEach>
 </table>
 </c:otherwise>
 </c:choose>
 <!--还书table-->
                 <!--table位置-->
   <hr />
 <legend>消息管理:</legend>
 <c:choose>
 <c:when test="${requestScope.messageList==null }">暂无消息记录~</c:when>
 <c:when test="${sessionScope.auth_name=='manager' }">管理员请去管理员界面查看~</c:when>
 <c:otherwise>
   <c:forEach items="${requestScope.messageList }" var="message">
   向管理员:${message.receive_id }发送的成为管理员请求。操作时间:${message.createtime }。
   <c:choose>
   <c:when test="${message.isread eq 0 }">
   <button disabled="disabled" class="btn_off">正在处理...</button>
   <button value="${message.id}" type="button" name="delete" class="btn_on">取消</button>
   </c:when>
   <c:otherwise>
   <c:choose>
   <c:when test="${message.result eq 0}">
   <button disabled="disabled" class="btn_off">管理员已拒绝</button>
   </c:when>
   <c:otherwise>
   <button disabled="disabled" class="btn_off">管理员已同意，您已成为管理员</button>
   </c:otherwise>
   </c:choose>
   <button value="${message.id}" type="button" name="delete" class="btn_on">删除</button>
   </c:otherwise>
   </c:choose>
   <br />
   </c:forEach>
   </c:otherwise>
   </c:choose>   
      <hr />
 <legend>评论信息:</legend>
 <c:choose>
 <c:when test="${requestScope.comments==null }">您还没发表过评论~</c:when>
 <c:otherwise>
 <c:forEach items="${requestScope.comments }" var="comment">
 <a href="bookInfo?id=${comment.book_id }" style="text-decoration:underline;"> 您于 ${comment.createtime } 发表评论：${comment.content }。${comment.praisecount }人点赞了你的评论~</a>
 <br/>
 </c:forEach>
 </c:otherwise>
 </c:choose>
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
</body>
</html>