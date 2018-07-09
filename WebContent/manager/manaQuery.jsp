<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>书籍查询</title>
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
			.messageError{
				color: red;
			}
			.messageRight{
			color:green;
			}
			/*以下为table*/
			table.dataintable {
  margin-top:15px;
  border-collapse:collapse;
  border:1px solid #aaa;
  width:100%;
}
table.dataintable th {
  vertical-align:baseline;
  padding:5px 15px 5px 6px;
  background-color:#3F3F3F;
  border:1px solid #3F3F3F;
  text-align:left;
  color:#fff;
}
table.dataintable td {
  vertical-align:text-top;
  padding:6px 15px 6px 6px;
  border:1px solid #aaa;
}
table.dataintable tr:nth-child(odd) {
  background-color:#F5F5F5;
  height: 20px;
}
table.dataintable tr:nth-child(even) {
  background-color:#fff;
  height: 20px;
}
.overFloat{
	width: 300px;
	overflow:hidden ;
}
	</style>
	<script type="text/javascript">
		$(function(){
			$(":button[name='delete']").click(function(){
		    var $this=$(this);
			var id=$(this).parent("td").siblings(":hidden[name='id']").val();
			var flag=confirm("确定要删除该书目信息吗？")
			if(flag == true){
		   var url="${pageContext.request.contextPath}/delete";
			var args={id:id,time:new Date()};
			$.get(url,args,function(data){
				if(data=="yes"){
					alert("删除成功");
					$this.parent("td").parent("tr").remove();
				}
				else{
					alert("删除失败，该书信息有外借或者受保护")
				}
			})	
					
		}
			else{
				return false;
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
        	<li class="select"><a href="${pageContext.request.contextPath}/manager/manaQuery.jsp">书籍查询</a></li>
            <li><a href="${pageContext.request.contextPath}/manager/manaQuery.jsp">书本信息修改</a></li>
        	<li><a href="${pageContext.request.contextPath}/manager/manaQuery.jsp">分卷内容添加</a></li>
        	<li><a href="${pageContext.request.contextPath}/userMana">会员管理</a></li>  
        	<li><a href="${pageContext.request.contextPath}/manager/busiMana.jsp">事务管理</a></li>  
    </ul>
</div>
		</div>
		<!--左边完毕-->
		<div class="width_880 floatr">
	<div class="manga_con box_shadow mar_bot">
               <div id="top" data-id="13502342" class="main-header td_lh clearfix">
               <p class="top_name">书籍查询</p>
               <div class="search">
				<form  id="query" action="${pageContext.request.contextPath}/manaQuery" method="post"> 
					<!-- <a href="#" id="search_btn" class="search_btn"></a> -->
					<input type="text" id="search" class="search-input" placeholder="请输入搜索条件" value="${param.search }" name="search">
				     <input type="submit" class="search_btn" value="搜索"/>   
				</form>
				</div>
			</div> 
			
           <div class="td_main minheight">
						<div class="applyTips"></div>
                        <div class="ap_form">
                        	<!--数据表格位置-->
                    <table class="dataintable">
<tr>
  <th>书名</th>
  <th>编号</th>
  <th>类型</th>
  <th>数量</th>
  <th>地址</th>
  <th>作者</th>
  <th>描述</th>
</tr>
<!--循环-->
<c:choose>
<c:when test="${requestScope.bookList==null }">不存在索引条件的书</c:when>
<c:otherwise>
<c:forEach items="${requestScope.bookList }" var="book">
<tr>
<%-- 	<form action="${pageContext.request.contextPath}/manaQuery" method="get"> --%>
<%--   <input type="hidden" name="id" value="${book.id }" /> --%>
  <td>${book.name }</td>
  <td>${book.no }</td>
  <td>${book.type }</td>
  <td>${book.number }</td>
  <td>${book.location }</td>
  <td>${book.author}</td>
  <td><div class="overFloat">${book.description }</div></td>
<td><a href="${pageContext.request.contextPath}/manaQuery?id=${book.id }" class="btn_on">修改</a></td>
<td><a href="${pageContext.request.contextPath}/addContent?id=${book.id }" class="btn_on">添加</a></td>
<td><button name="delete" type="button" class="btn_on">删除</button></td>
<!--   </form> -->
</tr>
</c:forEach>
</c:otherwise>
</c:choose>
<!--循环-->
</table>
                      <!--数据表格位置-->
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