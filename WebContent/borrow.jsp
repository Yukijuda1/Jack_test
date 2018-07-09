<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>正在借阅...</title>
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
	</style>
	<script type="text/javascript">
		$(function(){
			/*加载后就验证*/
			var isExist=false;
			var name=$(":text[name='name']").val();
			var url="${pageContext.request.contextPath}/borrow";
			var args={name:name,method:"check",time:new Date()};/*请求的数据放到json里  */
			$.get(url,args,function(data){/*data为传过来的h5字符串*/
				if(data == "no"){
				$("<span class='messageError'>该书已借完！</span>").insertAfter($(":text[name='name']"));
			}
				else{
			$("<span class='messageRight'>仍有库存√</span>").insertAfter($(":text[name='name']"));
					isExist=true;
				}
				})
		
var wait=60; 
function time(o) { 
        if (wait == 0) { 
            o.removeAttribute("disabled");           
            o.innerText="发送验证码至邮箱"; 
            wait = 60; 
        } else { 
            o.setAttribute("disabled", true); 
            o.innerText=wait+"秒后可以重新发送"; 
            wait--; 
            setTimeout(function() { 
                time(o) 
            }, 
            1000) 
        } 
    }				
		$(":text[name='email']").change(function(){
              	$(this).next("span").remove();
              	var email=$(this).val();
              	var isEmail=/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(email);
              	if(isEmail == false){
              $("<span class='messageError'>非邮件格式！</span>").insertAfter($(":text[name='email']"));
              	}
              })
		/*发送验证信息*/
		var code="";
		$(":button[name='sendEmail']").click(function(){
			var username=$(":text[name='username']").val();
              	var email=$(":text[name='email']").val();
              		var isEmail=/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(email);
              	if(email == ""){
              		alert("请填写邮箱");
              	}
              	else if(isEmail == false){
              		alert("请输入正确格式邮件");
              		return false;
              	}
              	else{
             var url="${pageContext.request.contextPath}/borrow";
			/*发送Ajax请求给registerServlet*/
			var args={username:username,email:email,method:"email",time:new Date()};/*请求的数据放到json里  */
			$.get(url,args,function(data){/*data为传过来的h5字符串*/
				code=data;
			})
			
			time(this);
              	}
              })
		
		
		$(":button[name='borrow']").click(function(){
			/*var email=$(":text[name='email']").val();*/
			var code_put=$(":text[name='code']").val();
			if(code_put == ""){
				alert("请填写验证码(刷新页面后失效)");
			}
			else if(code_put != code){
				alert("验证码错误");
			}
			else if(isExist == false){
				alert("该书籍暂时被借完！");
			}
			else{
				var email=$(":text[name='email']").val();
			var name=$(":text[name='name']").val();
			var url="${pageContext.request.contextPath}/borrow";
			var args={name:name,email:email,time:new Date()};/*请求的数据放到json里  */
			$.post(url,args,function(data){/*data为传过来的h5字符串*/
				if(data =="yes"){
					alert("借阅成功！")
				window.location.href='${pageContext.request.contextPath}/success.jsp';	
				}
				else{
				$("<span class='messageError'>系统错误，借阅失败</span>").insertAfter($(":button[name='borrow']"));	
				}
			})
			}
		})
		})
	</script>
		<style type="text/css">
			.messageError{
				color: red;
			}
			.messageRight{
			color:green;
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
                    <a href="${pageContext.request.contextPath}/manager/managerIndex.jsp" title="管理" class="nav-btn">
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
<!--<div class="user_read box_shadow" style="top: 173px;">
    <ul>
        <li class="select">
        	<a href="${pageContext.request.contextPath}/manager/insert.jsp">书籍添加</a></li>
        	<li><a href="${pageContext.request.contextPath}/manager/alert.jsp">书籍信息修改</a></li>
        	<li><a href="${pageContext.request.contextPath}/manager/content.jsp">书籍内容管理</a></li>
        	<li><a href="#">查看借书信息</a></li> 
        	<li><a href="#">会员管理</a></li>  
    </ul>
</div>-->
		</div>
		<!--左边完毕-->
		<div class="width_880 floatr">
	<div class="manga_con box_shadow mar_bot">
               <div id="top" data-id="13502342" class="main-header td_lh clearfix">
               <p class="top_name">正在借阅</p>
			</div> 
           <div class="td_main minheight">
						<div class="applyTips"></div>
                        <div class="ap_form">
                        	<!--表单位置-->
                        	<!--<form action="${pageContext.request.contextPath}/borrow" method="post">-->
                             <div class="ap_input clearfix">
                                    <label class="ap_type fl" for="td_contact">用户：</label><input value="${sessionScope.user.username}" type="text" name="username" maxlength="32" class="input td_contact fl"  id="td_contact" size="35" readonly="readonly">
                             </div>
                            <div class="ap_input clearfix">
                             <label class="ap_type fl" for="td_contact">书名：</label><input value="${requestScope.bookInfo.name}" type="text" name="name" maxlength="32" class="input td_contact fl"  id="td_contact" size="35" readonly="readonly" />
                             </div>
                            <div class="ap_input clearfix">
                             <label class="ap_type fl" for="td_contact">编号：</label><input value="${requestScope.bookInfo.no}" type="text" name="no" maxlength="32" class="input td_contact fl"  id="td_contact" size="35" readonly="readonly" />
                             </div>
                              <div class="ap_input clearfix">
                                    <label class="ap_type fl" for="td_contact">邮箱：</label><input value="${sessionScope.user.email}" type="text" name="email" maxlength="32" class="input td_contact fl"  id="td_contact" size="35" readonly="readonly">
                                <button type="button" class="btn_on" name="sendEmail">发送验证码至邮箱</button>
                              </div>
                                 <div class="ap_input clearfix">
                                    <label class="ap_type fl" for="td_contact">验证码：</label><input type="text" name="code" maxlength="32" class="input td_contact fl"  id="td_contact" size="35" required>
                                </div>
                                <!--input基本结束-->
                                <div class="ap_input ap_input_ptop clearfix">
                                	<label class="ap_type fl" for="td_contact"></label>
                                 <button name="borrow" type="button" class="btn_on">借阅</button>
                              <div id="apLoading" class="login_loading hide fl"></div>
                                </div> <!--</form>--></div>					</div>     
		</div>
		</div>
		<!--中间完毕-->
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