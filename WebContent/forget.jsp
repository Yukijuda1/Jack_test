<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/buka/css/common.css" />
<style type="text/css">
                .user_bg,.close_reg{display: none;}
                          p{
           color:red
           }
			.messageError{
				color: red;
			}
			.messageRight{
				color: green;
			}
			.btn_on{ display: inline-block; padding: 0 15px; height: 28px;color: #ffffff;border: 1px solid #fd9517; background: #fd9517; line-height: 28px;text-align: center;margin-left: 18px; cursor: pointer;}
            </style>
          <script src="${pageContext.request.contextPath}/buka/js/jquery.js" type="text/javascript"></script>
            <script src="${pageContext.request.contextPath}/buka/js/jquery.md5.js"></script>
            <script src="${pageContext.request.contextPath}/buka/js/userInfo.js"></script>
</head>
            <script type="text/javascript">
            	$(function(){
            		var wait=60; 
function time(o) { 
        if (wait == 0) { 
            o.removeAttribute("disabled");           
            o.innerText="发送至绑定邮箱"; 
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
     $(":text[name='username']").focus(function(){
            		$(this).prev("span").remove();
            })
      $(":text[name='username']").focusout(function(){
            	if($(this).val()==""){
            	$("<span class='user_tex' id='pwd_tex' style='display: block;'>帐号</span>").insertBefore($(this));	
            	}
            })
       $(":text[name='email']").focus(function(){
            		$(this).prev("span").remove();
            })
            $(":text[name='email']").focusout(function(){
            	if($(this).val()==""){
            	$("<span class='user_tex' id='pwd_tex' style='display: block;'>邮箱</span>").insertBefore($(this));	
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
            
            $(":password[name='passwordRep']").focus(function(){
            		$(this).prev("span").remove();
            })
            $(":password[name='passwordRep']").focusout(function(){
            	if($(this).val()==""){
            	$("<span class='user_tex' id='pwd_tex' style='display: block;'>重复密码</span>").insertBefore($(this));	
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
            
            var isExist=false;
            $(":text[name='username']").change(function(){
            	$(this).next("span").remove();
				var val=$(this).val();
	           var hasIllegalWord=/[!#\$%\^&\*()（）！￥……]+/g.test(val);
	           var isStartWithWord=/^\d+/.test(val);
				var length=val.length;
				if($(this).val()==""){
					$("<span id='pwd_tex' class='messageError'>请输入用户名！</span>").insertAfter($(":text[name='username']"));
				}
				else if(isStartWithWord == true){
					$("<span id='pwd_tex' class='messageError'>用户名不能以数字开头</span>").insertAfter($(":text[name='username']"));
				}
				else if(hasIllegalWord == true){
					$("<span class='messageError'>用户名中有非法字符！</span>").insertAfter($(":text[name='username']"));
				}
				else if(length<6){
					$("<span class='messageError'>用户名长度不能小于6位！</span>").insertAfter($(":text[name='username']"));
				}
				else if(length>20){
					$("<span class='messageError'>用户名长度不能超过20位！</span>").insertAfter($(":text[name='username']"));
				}
					else{
		var url="${pageContext.request.contextPath}/forget";
		/*发送Ajax请求给registerServlet*/
		var args={username:val,method:"check",time:new Date()};/*请求的数据放到json里  */
		$.get(url,args,function(data){/*data为传过来的h5字符串*/
			if(data == "yes"){
			$("<span class='messageRight'>用户名存在</span>").insertAfter($(":text[name='username']"));
			isExist=true;
			}
			else{
			$("<span class='messageError'>用户名不存在</span>").insertAfter($(":text[name='username']"));	
			isExist=false;
			}
		})
	}  	
            })
            
            $(":password[name='password']").change(function(){
		   	$(this).next("span").remove();
		   	var val=$(this).val();
		   	var hasIllegalWord=/[!#\$%\^&\*()（）！￥……]+/g.test(val);
		    var length=val.length;
		    if(val == ""){
		    $("<span class='messageError'>密码栏不能为空！</span>").insertAfter($(":password[name='password']"));	
		    }
		    else if(hasIllegalWord == true){
		    	$("<span class='messageError'>密码中含有非法字符！</span>").insertAfter($(":password[name='password']"));
		    }
		    else if(length<6 || length >12){
		    	$("<span class='messageError'>密码必须为6位及以上或12位及以下</span>").insertAfter($(":password[name='password']"));
		    }
		   })

       $(":password[name='passwordRep']").change(function(){
       	/*$(".messageError").remove();
       	$(".messageRight").remove();*/
       	$(this).next("span").remove();
       	var passwordRep=$(this).val();
       	var passw=$(":password[name='password']").val();
       	if(passwordRep != passw){
       		$("<span class='messageError'>两次输入的密码不相同！</span>").insertAfter($(":password[name='passwordRep']"));
       	}
       	else{
       		$("<span class='messageRight'>两次输入的密码相同√</span>").insertAfter($(":password[name='passwordRep']"));
       	}
       })
            
            var emailCode="";
            $(":button[name='sendEmail']").click(function(){
            	var username=$(":text[name='username']").val();
              	if(username== ""){
              		alert("请输入用户名");
              		return false;
              	}
              	else if(isExist == false){
              		alert("该用户不存在");
              		return false;
              	}
              	else{
             var url="${pageContext.request.contextPath}/forget";
			/*发送Ajax请求给registerServlet*/
			var args={username:username,method:"email",time:new Date()};/*请求的数据放到json里  */
			$.get(url,args,function(data){/*data为传过来的h5字符串*/
				emailCode=data;
			})
			time(this);
  }
            })
            
              $(":submit").click(function(){
       	var $username=$(":text[name='username']");
       	var $password=$(":password[name='password']");
       	var $passwordRep=$(":password[name='passwordRep']");
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
       		alert("用户名长度不符合要求");
       		return false;
       	}
       	else if($password.val().length<6 || $password.val().length>12){
       		alert("密码长度不符合要求");
       		return false;
       	}
       	else if($password.val() != $passwordRep.val()){
       		alert("两次输入的密码不相同！");
       		return false
       	}
       	else if($code.val()== ""){
       		alert("请填写验证码");
       		return false
       	}
       	else if($code.val()!=emailCode){
       		alert("验证码错误！");
       		return false;
       	}
       	else if(isExist==false){
       		alert("用户不存在！");
       		return false;
       	}
       	else{
       		alert("修改成功！");
       	}
       })
            
            	})
            	
            </script>
<body>
<div class="user_bg" style="height:0px"></div>
		<div class="register_model box_shadow">
		<div class="tit_60">
			<dl class="common_tit_dl">
				<dt class="floatl user_r_top" id="user_r_top">修改密码</dt>
				<dd class="floatr close_reg"><a href="javascript:void(0)"><img src="img/icon-delete.jpg"></a></dd> 
			</dl>
		</div>
		<form action="forget" method="post">
		<div class="reg_cen">
			<ul class="reg_inp_list">
				<li id="pwd_inp"><span class="user_tex" id="pwd_tex" style="display: block;">用户名</span><input name="username" type="text" class="u_inp" id="rpassword"></li>
			<li id="pwd_inp"><span class="user_tex" id="pwd_tex" style="display: block;">新密码</span><input name="password" type="password" class="u_inp" id="rpassword"></li>
			<li id="pwd_inp"><span class="user_tex" id="pwd_tex" style="display: block;">再次输入</span><input name="passwordRep" type="password" class="u_inp" id="rpassword"></li>
			</ul>
			
			<dl class="reg_dl_vcode">
				<dt class="floatl"><span class="user_tex" id="vcode_tex" style="display: block;">验证码</span><input name="code" type="text" class="u_inp" id="rcode_char"></dt>
			 <button name="sendEmail" type="button" class="btn_on" >发送至绑定邮箱</button>
			<!--验证码	--> 
			</dl>
			<div class="clear"></div>
			<div class="sub_userinfo"><input id="login_p_btn" type="submit" value="修改密码"></div>
			<div class="reg_con">
				<a id="regModel" href="${pageContext.request.contextPath}/login.jsp">返回登录</a></div>
		
		</div><!--reg_cen-->
		</form>
		<div class="give_up"><p>确定放弃编辑吗？</p><dl class="give_btn"><dt class="floatl" id="tureClose">确定</dt><dd class="floatr" id="goBack">取消</dd></dl><div class="clear"></div></div>
		</div>
</body>
</html>