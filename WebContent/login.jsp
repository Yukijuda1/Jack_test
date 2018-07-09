<%@page import="javax.websocket.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/Ajax/jquery-1.7.2.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/buka/css/common.css" />
<style type="text/css">
			.messageError{
				color: red;
			}
			p{
			color:red;
			}
		</style>
		<script type="text/javascript">
			$(function(){
				/* $(document).on('click','#ValidateColor',function(){
        		$("#ValidateColor").remove();
        		$("<img id='ValidateColor' src='ValidateColor'/>").appendTo("#code_DD"); 
        		}); */
				
				$(":text[name='username']").focus(function(){
               		$(this).prev("span").remove();
               })
               $(":text[name='username']").focusout(function(){
               	if($(this).val()==""){
               	$("<span class='user_tex' id='pwd_tex' style='display: block;'>帐号</span>").insertBefore($(this));	
               	}
               })
               
               $(":password[name='password']").focus(function(){
               		$(this).prev("span").remove();
               })
               $(":password[name='password']").focusout(function(){
               	if($(this).val()==""){
               	$("<span class='user_tex' id='pwd_tex' style='display: block;'>密码</span>").insertBefore($(this));	
               	}
               })
               $(":text[name='code']").focus(function(){
               		$(this).prev("span").remove();
               })
               $(":text[name='code']").focusout(function(){
               	if($(this).val()==""){
               	$("<span class='user_tex' id='pwd_tex' style='display: block;'>验证码</span>").insertBefore($(this));	
               	}
               })
				
				$(":text[name='username']").change(function(){
		           $(this).next(".messageError").remove();
		           var val=$(this).val();
		           var hasIllegalWord=/[!#\$%\^&\*()（）！￥……]+/g.test(val);
		            var isStartWithWord=/^\d+/.test(val);
					var length=val.length;
					if(val ==""){
						$("<span class='messageError'>请输入用户名！</span>").insertAfter($(":text[name='username']"));
					}
					else if(hasIllegalWord == true){
						$("<span class='messageError'>用户名中有非法字符！</span>").insertAfter($(":text[name='username']"));
					}
					else if(isStartWithWord == true){
						$("<span class='messageError'>用户名不能以数字开头</span>").insertAfter($(":text[name='username']"));
					}
					else if(length<6 || length >20){
						$("<span class='messageError'>用户名长度非法！</span>").insertAfter($(":text[name='username']"));
					}
				})
				
				
				$(":password[name='password']").change(function(){
					 $(this).next(".messageError").remove();
					var val=$(this).val();
					var length=val.length;
					var hasIllegalWord=/[!#\$%\^&\*()（）！￥……]+/g.test(val);
					if(val == ""){
					$("<span class='messageError'>请输入密码！</span>").insertAfter($(":password[name='password']"));
					}
					else if(hasIllegalWord == true){
					$("<span class='messageError'>密码含有非法字符！</span>").insertAfter($(":password[name='password']"));	
					}
					else if(length < 6 || length >12){
						$("<span class='messageError'>密码长度非法！(6~12)</span>").insertAfter($(":password[name='password']"));
					}
				})
				
				$(":text[name='code']").change(function(){
					 $(this).next(".messageError").remove();
					var val=$(this).val();
					var hasIllegalWord=/[!@#\$%\^&\*()（）！￥……]+/g.test(val);
					if(val == ""){
					$("<span class='messageError'>请填写验证码！</span>").insertAfter($(":text[name='code']"));	
					}
					else if(hasIllegalWord == true){
					$("<span class='messageError'>验证码有非法字符！</span>").insertAfter($(":text[name='code']"));		
					}
				})
				
				$(":submit").click(function(){
					var $username=$(":text[name='username']");
					var $password=$(":password[name='password']");
					var $code=$(":text[name='code']");
			    var isStartWithWord=/^\d+/.test($username.val());
                var usernameHasIllegalWord=/[!#\$%\^&\*()（）！￥……]+/g.test($username.val());
                var passwordHasIllegalWord=/[!#\$%\^&\*()（）！￥……]+/g.test($password.val());
              	if($username.val() == "" || $password.val() == ""){
              		alert("请填写用户或密码信息！");
              		return false;
              	}
              	else if(usernameHasIllegalWord == true){
              		alert("用户名含有非法字符！");
              		return false;
              	}
              	else if(passwordHasIllegalWord == true){
              		alert("密码含有非法字符！");
              		return false;
              	}
              	else if(isStartWithWord == true){
              		alert("用户名不能以数字开头！");
              		return false;
              	}
              	else if($username.val().length<6 || $username.val().length>20){
              		alert("用户名长度不符合要求！");
              		return false;
              	}
              	else if($password.val().length<6 || $password.val().length>12){
              		alert("密码长度不符合要求！");
              		return false;
              	}
              	else if($code.val() == ""){
              		alert("请填写验证码！")
              		return false;
              	}
              	else{
              		
              	}
					
				})
			})
			
		</script>
</head>
<body>
<div class="user_bg" style="height:0px"></div>
		<div class="register_model box_shadow">
		<div class="tit_60">
			<dl class="common_tit_dl">
				<dt class="floatl user_r_top" id="user_r_top">登录</dt>
				<dd class="floatr close_reg"><a href="javascript:void(0)"><img src="img/icon-delete.jpg"></a></dd> 
			</dl>
		</div>
		<form action="login" method="post">
		<div class="reg_cen">
			<ul class="reg_inp_list">
				<li id="pwd_inp"><span class="user_tex" id="pwd_tex" style="display: block;">帐号</span><input value="${param.username }" name="username" type="text" class="u_inp" id="rpassword"> </li>
				<li id="pwd_inp"><span class="user_tex" id="pwd_tex" style="display: block;">密码</span><input name="password" type="password" class="u_inp" id="rpassword"> </li>
			</ul>
			<dl class="reg_dl_vcode">
				<dt class="floatl"><span class="user_tex" id="vcode_tex" style="display: block;">验证码</span><input name="code" type="text" class="u_inp" id="rcode_char"></dt>
			   <dd id="code_DD" class="floatl"><img id="ValidateColor" src="ValidateColor"/></dd>
			<!--验证码	--> 
			</dl>
			<div class="clear"></div>
			<div class="sub_userinfo"><input id="login_p_btn" type="submit" value="登录"></div>
		<c:choose>
		<c:when test="${requestScope.info==2 }"><p>帐号或密码错误</p></c:when>
		<c:when test="${requestScope.info==3 }"><p>验证码错误</p></c:when>
		</c:choose>
			<div class="reg_con">
			<a id="regModel" href="${pageContext.request.contextPath}/forget.jsp">忘记密码</a>&nbsp;&nbsp;
			<a id="regModel" href="${pageContext.request.contextPath}/register.jsp">注册</a></div>
		</div><!--reg_cen-->
		</form>
		<div class="give_up"><p>确定放弃编辑吗？</p><dl class="give_btn"><dt class="floatl" id="tureClose">确定</dt><dd class="floatr" id="goBack">取消</dd></dl><div class="clear"></div></div>
		</div>
</body>
</html>