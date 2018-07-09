<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/buka/js/ie9.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/reset.css">
		<link href="${pageContext.request.contextPath}/buka/css/common2.css" rel="stylesheet" type="text/css" media="all">
		<link href="${pageContext.request.contextPath}/buka/css/list.css" rel="stylesheet" type="text/css" media="all">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/commonv=20170705.css">
				<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/index.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/works.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/buka/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/buka/js/identity.js"></script>
<title>书籍添加</title>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/Ajax/jquery-1.7.2.js"></script>		
	<script type="text/javascript">
			$(function(){
				var pictureMaxSize=1024*1024;
				var fileMaxSize=5*1024*1024;
				$(":text[name='bookName']").change(function(){
					 $(this).next("span").remove();
					var val=$(this).val();
					if(val == ""){
						$("<span class='messageError'>该项必选</span>").insertAfter($(this));
					}
					else{
			var url="${pageContext.request.contextPath}/insert";
			var args={bookName:val,time:new Date()};
			$.get(url,args,function(data){
				$(data).insertAfter($(":text[name='bookName']"));		
				})
			    
				}
						
					
					})
				$(":text[name='no']").change(function(){
					$(this).next("span").remove();
					var val=$(this).val();
					var isDigit=/^[0-9]*$/.test(val);
					if(val == ""){
						$("<span class='messageError'>该项必选</span>").insertAfter($(this));
					}
					else if(isDigit == false){
					$("<span class='messageError'>必须为纯数字！</span>").insertAfter($(this));	
					}
					else{
			var url="${pageContext.request.contextPath}/insert";
			
			var args={no:val,time:new Date()};
			$.get(url,args,function(data){
				$(data).insertAfter($(":text[name='no']"));
			})
				}
				
					
				})
			

				$(":text[name='number']").change(function(){
					$(this).next("span").remove();
					var val=$(this).val();
					var isDigit=/^[0-9]*$/.test(val);
					if(val == ""){
						$("<span class='messageError'>该项必选</span>").insertAfter($(this));
					}
					else if(isDigit == false){
					$("<span class='messageError'>必须为纯数字！</span>").insertAfter($(this));	
					}
				})
				
				$(":file[name='coverPicture']").change(function(){
					$(this).next("span").remove();
					var picture=$(this).get(0).files[0];
					var index=picture.name.lastIndexOf(".");
					var extensionName=picture.name.substr(index+1);
					var size=picture.size;
					if(extensionName != "jpg" && extensionName != "png" && extensionName != "jpeg"){
						$("<span class='messageError'>图片类型必须为(jpg,jpeg,png)</span>").insertAfter($(this));
					}
					else if(size > pictureMaxSize){
						$("<span class='messageError'>图片超过了1M！</span>").insertAfter($(this));
					}
				})
				
				  var contentNo=2;             
                $(":button[name='add']").click(function(){
                	$("<div class='ap_input clearfix'><button name='delete' type='button' class='btn_on'>删除</button><label class='ap_type fl' for='td_contact'>内容"+contentNo+"：</label><input type='file' name='content' maxlength='32' class='input td_contact fl' id='td_contact' size='35' required><input type='text' name='contentNo' readonly='readonly' class='input td_contact fl'  size='5' value="+contentNo+"></div>").insertAfter($(this).parent("div"));
                contentNo++;
                })

				$(document).on('click',":button[name='delete']",function(){
					$(this).parent("div").remove();
                		contentNo--;
				})
				
				$(document).on('change',":file[name='content']",function(){
					$(this).next("span").remove();
					var content=$(this).get(0).files[0];
					var index=content.name.lastIndexOf(".");
					var extensionName=content.name.substr(index+1);
					var size=content.size;
					if(extensionName !="txt"){
						$("<span class='messageError'>文件类型必须为(txt)</span>").insertAfter($(this));
					}
					else if(size > fileMaxSize){
						$("<span class='messageError'>文件超过了5M！</span>").insertAfter($(this));
					}
				})
				
				$("#submit").click(function(){
					var flag=true;
					$(":text[name='no']").each(function(){
						var val=$(this).val();
						var isDigit=/^[0-9]*$/.test(val);
						if(isDigit == false){
							alert("编号必须为纯数字！")
							flag=false;
							return false;
						}
					})
					
					$(":text[name='number']").each(function(){
						var val=$(this).val();
						var isDigit=/^[0-9]*$/.test(val);
						if(isDigit == false){
							flag=false;
							alert("数量必须为纯数字！")
							return false;
						}
					})
					
					$(":file[name='coverPicture']").each(function(){
					var picture=$(this).get(0).files[0];
					var index=picture.name.lastIndexOf(".");
					var extensionName=picture.name.substr(index+1);
					var size=picture.size;
					if(extensionName != "jpg" && extensionName != "png" && extensionName != "jpeg"){
						flag=false;
						alert("文件扩展名不合法！");
						return false;
					}
					else if(size > pictureMaxSize){
						flag=false;
						alert("文件大小溢出！");
						return false;
					}
				})
				
				$(":file[name='content']").each(function(){
					var content=$(this).get(0).files[0];
					var index=content.name.lastIndexOf(".");
					var extensionName=content.name.substr(index+1);
					var size=content.size;
					if(extensionName != "txt"){
						flag=false;
						alert("文件扩展名不合法！");
						return false;
					}
					else if(size > fileMaxSize){
						flag=false;
						alert("文件大小溢出！");
						return false;
					}	
						
					})
				
					if(flag == false){
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
        <li class="select">
        	<a href="${pageContext.request.contextPath}/manager/insert.jsp">书籍添加</a></li>
        	<li><a href="${pageContext.request.contextPath}/manager/manaQuery.jsp">书籍查询</a></li>
        	<li><a href="${pageContext.request.contextPath}/manager/manaQuery.jsp">书籍信息修改</a></li>
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
               <p class="top_name">书籍添加</p>
			</div> 
           <div class="td_main minheight">
						<div class="applyTips"></div>
                        <div class="ap_form">
                        	<!--表单位置-->
                        	<form action="${pageContext.request.contextPath}/insert" method="post" enctype="multipart/form-data">
                             <div class="ap_input clearfix">
                             <label class="ap_type fl" for="td_contact">书名：</label><input type="text" name="bookName" maxlength="32" class="input td_contact fl"  id="td_contact" size="35" required />
                             </div>
                             <div class="ap_input clearfix">
                                    <label class="ap_type fl" for="td_contact">编号：</label><input type="text" name="no" maxlength="32" class="input td_contact fl"  id="td_contact" size="35" required>
                             </div>
                              <div class="ap_input clearfix">
                                    <label class="ap_type fl" for="td_contact">数量：</label><input type="text" name="number" maxlength="32" class="input td_contact fl"  id="td_contact" size="35" required>
                                </div>
                                 <div class="ap_input clearfix">
                                    <label class="ap_type fl" for="td_contact">地址：</label><input type="text" name="location" maxlength="32" class="input td_contact fl"  id="td_contact" size="35" required>
                                </div>
                                 <div class="ap_input clearfix">
                                    <label class="ap_type fl" for="td_contact">作者：</label><input type="text" name="author" maxlength="32" class="input td_contact fl"  id="td_contact" size="35" required>
                                </div>
                                 <div class="ap_input clearfix">
                                    <label class="ap_type fl" for="td_contact">类型：</label><input type="text" name="type" maxlength="32" class="input td_contact fl"  id="td_contact" size="35" required>
                                </div>
                                 <div class="ap_input clearfix">
                                    <label class="ap_type fl" for="td_contact">描述：</label><textarea name="description" maxlength="500" class="input td_contact fl" id="td_contact" size="35" required></textarea>
                                </div>
                                 <div class="ap_input clearfix">
                                    <label class="ap_type fl" for="td_contact">封面：</label><input type="file" name="coverPicture" maxlength="32" class="input td_contact fl" id="td_contact" size="35" required>
                                </div>
                                <!--input结束-->
                                <div class="ap_input clearfix">
                                 	 
                                    <label class="ap_type fl" for="td_contact">内容：</label><input type="file" name="content" maxlength="32" class="input td_contact fl" id="td_contact" size="35" required>
                                <input type="text" name="contentNo" readonly="readonly" class="input td_contact fl"  size="5" value="1">
                                 <button name="add" type="button" class="btn_on">添加</button>
                                 </div>
                                 
                                <!--input content-->
                                <div class="ap_input ap_input_ptop clearfix">
                                	<label class="ap_type fl" for="td_contact"></label>
                                 <button id="submit" type="submit" class="btn_on">入库</button>
                              <div id="apLoading" class="login_loading hide fl"></div>
                                </div> 
                                <c:choose>
			<c:when test="${requestScope.message==0 }"><span class="messageError">录入失败！</span></c:when>
			<c:when test="${requestScope.message==1 }"><span class="messageRight">添加成功√</span></c:when>
			</c:choose>
                                </form>
                                </div>					</div>     
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