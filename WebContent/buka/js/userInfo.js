var userInfo =  function (){};
userInfo.prototype.init = function(baseUrl)
{
    this.baseUrl = baseUrl;
};
userInfo.prototype.getRegView = function()
{
	var h = $('body').height();
	var html = '<div class="user_bg" style="height:'+h+'px"></div>'+
				'<div class="register_model box_shadow">'+
			    	'<div class="tit_60">'+
			            '<dl class="common_tit_dl">'+
			                '<dt class="floatl user_r_top" id="user_r_top">注册</dt>'+
			                '<dd class="floatr close_reg"><a href="javascript:void(0)"><img src="res/images/icon-delete.jpg" /></a></dd>'+
			           ' </dl>'+
			        '</div>'+
			        '<div class="reg_cen">'+
			        	'<ul class="reg_inp_list">'+
			                '<li>'+
			                	'<span class="user_tex" id="email_tex">邮箱</span>'+
			                    '<input type="text" class="u_inp" id = "runame" />'+
			                    '<img src="res/images/close.jpg" class="rclose" />'+
			                '</li>'+
			                '<li id="pwd_inp">'+
			                   '<span class="user_tex" id="pwd_tex">密码（6-16位）</span>'+
			                   ' <input type="password" class="u_inp" id = "rpassword" />'+
			               ' </li>'+
			                '<li id="pwd2_inp">'+
			                	'<span class="user_tex" id="pwd2_tex">确认密码</span>'+
			                    '<input type="password" class="u_inp" id = "rpassword2" />'+
			                '</li>'+

			            '</ul>'+
			            '<dl class="reg_dl_vcode">'+
			            	'<dt class="floatl"><span class="user_tex" id="vcode_tex">验证码</span><input type="text" class="u_inp" id = "rcode_char" /></dt>'+
			                '<dd class="floatl"><img id="getVcode" src="'+baseUrl+'/views/res/code/captcha.php" /></dd>'+
			            '</dl>'+
			            '<div class="clear"></div>'+
						'<div class="protocol"><input type="checkbox" name="prot" value=1 id="prot" onclick="javascript:onProtClick()" />我已经阅读并同意<a class="nl" href="http://td.sosobook.cn/protocol.html" target="_blank">《布卡漫画投稿协议》</a></div>' +
			            '<div class="sub_userinfo">'+
			            	'<input id="next_btn" type="button" value="确认" disabled="disabled" class="disabled" />'+
			            '</div>'+
                                    '<div class="reg_con">'+
			            	'<a id="logModel" href="javascript:void(0)">登录</a>'+
			            '</div>'+
			        '</div>'+userInfo.giveUp()+

			    '</div>';


	return html;
}
userInfo.prototype.getRepView = function()
{
	var h = $('body').height();
	var html = '<div class="user_bg" style="height:'+h+'px"></div>'+
				'<div class="register_model box_shadow">'+
			    	'<div class="tit_60">'+
			            '<dl class="common_tit_dl">'+
			                '<dt class="floatl user_r_top" id="user_r_top">评论</dt>'+
			                '<dd class="floatr close_reg"><a href="javascript:void(0)"><img src="res/images/icon-delete.jpg" /></a></dd>'+
			           ' </dl>'+
			        '</div>'+
			        '<div class="reg_cen rep_cen">'+
						'<div class="reviews"><span class="user_tex">说点什么吧 至少五字</span><textarea class="rep_textarea" name="" cols="" rows=""></textarea></div>'+
						'<div class="subBtnRep"><input type="button" id="subBtnRep" value="发布" /></div>'+
			        '</div>'+userInfo.giveUp()+

			    '</div>';


	return html;
}

userInfo.prototype.getFileView = function()
{
	var h = $('body').height();
	var html = '<div class="user_bg" style="height:'+h+'px"></div>'+
				'<div class="register_model box_shadow">'+
			    	'<div class="tit_60">'+
			            '<dl class="common_tit_dl">'+
			                '<dt class="floatl user_r_top" id="user_r_top">完善个人信息</dt>'+
			                '<dd class="floatr close_reg"><a href="javascript:void(0)"><img src="res/images/icon-delete.jpg" /></a></dd>'+
			           ' </dl>'+
			        '</div>'+
			        '<div class="reg_cen">'+userInfo.getUploadView(baseUrl)+
			        '</div>'+userInfo.giveUp()+

			    '</div>';


	return html;
}

userInfo.prototype.getUploadView = function (baseUrl)
{
    var avatar_html = '<form enctype="multipart/form-data" method="POST" id="fileSub" target="upload" action="' + baseUrl + '/user/uploadAvatar/" > <input type="file" name="myfile" id="myfile" class="myfile" /><img id="viewImg" src="res/images/uolode_logo.jpg" /></form><iframe id="fileIframe" name="upload" style="display:none" ></iframe> </div></div>' +
            '<p class="upload_tex">上传头像</p>';
    if (typeof (rename) != "undefined" && rename == "1")
        avatar_html = '';

    var html = '<div class="full_user">' +
            '<div class="up_logo_con"><div class="up_logo_con">' +
            avatar_html +
            '<div class="nick"><span class="vcode_tex" id="nick_tex">昵称：</span><input type="text" id = "nickname" /><input type="hidden" id="tempdir" /></div>' +
            '<dl class="full_btn">' +
            '<dt class="floatl"><a href="javascript:void(0)" id = "skip">取消</a></dt>' +
            '<dd class="floatr"><input type="button" id="finish" value="完成" /></dd>' +
            '</dl>' +
            '<div class="clear"></div>' +
            '</div>';


    return html;
}
userInfo.prototype.giveUp = function()
{
var html = ' <div class="give_up">'+
            	'<p>确定放弃编辑吗？</p>'+
                '<dl class="give_btn">'+
                	'<dt class="floatl" id="tureClose">确定</dt>'+
                    '<dd class="floatr" id="goBack">取消</dd>'+
                '</dl>'+
                '<div class="clear"></div>'+
            '</div>';


	return html;
}

userInfo.prototype.getLogView = function()
{
	var h = $('body').height();
	var html = '<div class="user_bg" style="height:'+h+'px"></div>'+
				'<div class="register_model box_shadow">'+
			    	'<div class="tit_60">'+
			            '<dl class="common_tit_dl">'+
			                '<dt class="floatl user_r_top" id="user_r_top">登录</dt>'+
			                '<dd class="floatr close_reg"><a href="javascript:void(0)"><img src="res/images/icon-delete.jpg" /></a></dd>'+
			           ' </dl>'+
			        '</div>'+
			        '<div class="reg_cen">'+
			        	'<ul class="reg_inp_list">'+
			                '<li>'+
			                	'<span class="user_tex" id="email_tex">邮箱</span>'+
			                    '<input type="text" class="u_inp" id = "runame" />'+
			                    '<img src="res/images/close.jpg" class="rclose" />'+
			                '</li>'+
			                '<li id="pwd_inp">'+
			                   '<span class="user_tex" id="pwd_tex">密码（6-16位）</span>'+
			                   ' <input type="password" class="u_inp" id = "rpassword" />'+
			               ' </li>'+
			            '</ul>'+
			            '<dl class="reg_dl_vcode">'+
			            	'<dt class="floatl"><span class="user_tex" id="vcode_tex">验证码</span><input type="text" class="u_inp" id = "rcode_char" /></dt>'+
			                '<dd class="floatl"><img id="getVcode" src="'+baseUrl+'/views/res/code/captcha.php" /></dd>'+
			            '</dl>'+
			            '<div class="clear"></div>'+
			            '<div class="sub_userinfo">'+
			            	'<input id="login_p_btn" type="button" value="登录" />'+
			            '</div>'+
			            '<div class="reg_con">'+
			            	'<a id="regModel" href="javascript:void(0)">注册</a>'+
			            '</div>'+
			        '</div>'+userInfo.giveUp()+

			    '</div>';


	return html;
}

userInfo.prototype.events = function(baseUrl,mid)
{
	//判断字段是否为空
	$(".u_inp").live('click',function(){
		$(this).addClass('isnull')
		userInfo.isNull();

	});
	$(".u_inp").live('blur',function(){
		$('.u_inp').filter(function(index) {
		 return $(this).val() =='';
		}).prev().show();

	});
	//评论
	$("#subBtnRep").live('click',function(){
		if($(".rep_textarea").val() == '')
		{
			$("#user_r_top").html('<span>评论内容不能为空！</span>');
		}else{
			userInfo.RepSub(baseUrl,mid)
		}

	})

	//上传头像
	$("#myfile").live('change',function(){
		$("#fileSub").submit();

		iframe = document.getElementById("fileIframe");


	if (iframe.attachEvent) {

        iframe.attachEvent("onload", function() {
                    //IE浏览器
            var fileText = $('#fileIframe').contents().find("body").text();
			if(fileText)
			{
			var dataInfo = eval('(' + fileText  + ')');

           		switch (dataInfo.ret)
           		{
                            case 0:
                            $("#user_r_top").html('完善个人信息');
		            $("#viewImg").attr('src',dataInfo.avatar);
		            $("#tempdir").val(dataInfo.tempdir);
		            $(".upload_tex").text('修改头像');
		            break;
		            case 1200:
					$("#user_r_top").html('<span>头像不存在！</span>');
					break;
					case 1201:
					$("#user_r_top").html('<span>头像生成错误！</span>');
					break;
					case 1202:
					$("#user_r_top").html('<span>移动头像目录失败!</span>');
					break;
					default:
					$("#user_r_top").html('<span>头像上传失败!</span>');
					break;

           		}
           }else{
           		$("#user_r_top").html('<span>头像上传失败!</span>');
           }
        });
    } else {
        iframe.onload = function() {
                    //其他浏览器
			var fileText = $('#fileIframe').contents().find("body").text();
           if(fileText)
			{
			var dataInfo = eval('(' + fileText  + ')');

           		switch (dataInfo.ret)
           		{
					case 0:
					$("#user_r_top").html('完善个人信息');
		            $("#viewImg").attr('src',dataInfo.avatar);
		            $("#tempdir").val(dataInfo.tempdir);
		            $(".upload_tex").text('修改头像');
		            break;
		            case 1200:
					$("#user_r_top").html('<span>头像不存在！</span>');
					break;
					case 1201:
					$("#user_r_top").html('<span>头像生成错误！</span>');
					break;
					case 1202:
					$("#user_r_top").html('<span>移动头像目录失败!</span>');
					break;
					default:
					$("#user_r_top").html('<span>头像上传失败!</span>');
					break;

           		}
           }else{
           		$("#user_r_top").html('<span>头像上传失败!</span>');
           }


        };
    }

	});
	//完成
	$("#finish").live('click',function(){
		if($("#nickname").val() ==''){
		$("#user_r_top").html("<span>昵称不能为空</span>");
		}else{
		userInfo.editUserInfo(baseUrl);
		}
	})
	//跳过
	$("#skip").live('click',function(){
		$('.user_bg,.register_model').remove();
	});
	//无改动关闭
	$(".close_reg").live('click',function(){
		if( $(".user_tex:first").css('display')=='block'){

			$('.user_bg,.register_model').remove();
			$("#getcode_char").attr("src",baseUrl+'/views/res/code/captcha.php?' + Math.random());
		}else{
			$("#user_r_top").html("提示");
			$(".give_up").show();
			$(".close_reg,.reg_cen").hide();
		}
	});
	//确定关闭
	$("#tureClose").live('click',function(){
		$('.user_bg,.register_model').remove();
		$("#getcode_char").attr("src",baseUrl+'/views/res/code/captcha.php?' + Math.random());
	});
	//返回
	$("#goBack").live('click',function(){
		if($("#login_p_btn").val()){
			$("#user_r_top").html("登录");
		}
		if($("#next_btn").val()){
			$("#user_r_top").html("注册");
		}
		if($("#finish").val()){
			$("#user_r_top").html("完善个人信息");
		}
			$(".give_up").hide();
			$(".close_reg,.reg_cen").show();
	});
	//登录
	$("#login_p_btn").live('click',function(){


			var	val = $("#runame").val();
			if(val ==''){
				$("#user_r_top").html("<span>邮箱不能为空</span>");

			}else{
				userInfo.login(baseUrl)
			}

	});
        $("#logModel").live('click',function(){
		$(".register_model,.user_bg").remove();
		$("body").append(userInfo.getLogView(baseUrl));
	});
	$("#regModel").live('click',function(){
		$(".register_model,.user_bg").remove();
		$("body").append(userInfo.getRegView(baseUrl));
	});
	//注册下一步
	$("#next_btn").live('click',function(){

			var	val = $("#runame").val();
			if(val ==''){
				$("#user_r_top").html("<span>邮箱不能为空</span>");

			}else{
				userInfo.register(baseUrl)
			}

	});
	//清空用户名
	$(".rclose").live('click',function(){
		$(this).hide();
		$("#runame").css("color","#999999");
		$('#runame').val('');
	});

	//清除提示信息
	$(".u_inp").live('focus',function(){
		$(this).addClass('isnull')
		userInfo.isNull();
	});
	$(".rep_textarea").live('focus',function(){
		$("#user_r_top").html("评论");
		if($(this).val() == ''){
			$('.user_tex').hide();
		}

	});

	$(".rep_textarea").live('blur',function(){
		if($(this).val() == ''){
			$('.user_tex').show();
		}
	});
	$(".u_inp").live('keydown',function(e){
		userInfo.keyDown(baseUrl,e);
	})
	//刷新验证码
	$("#getVcode").live('click',function(){
		$(this).attr("src", baseUrl+'/views/res/code/captcha.php?' + Math.random());
	});



}
//键盘回车登录或注册
userInfo.prototype.keyDown = function(baseUrl,e)
{
if(e.keyCode == 13){
	if($("#login_p_btn").val())
	{
		var	val = $("#runame").val();
			if(val ==''){
				$("#user_r_top").html("<span>邮箱不能为空</span>");

			}else{
				userInfo.login(baseUrl)
			}
	}
	if($("#next_btn").val())
	{
		var	val = $("#runame").val();
			if(val ==''){
				$("#user_r_top").html("<span>邮箱不能为空</span>");
			}else{
				userInfo.register(baseUrl)
			}
	}
}

}

userInfo.prototype.isNull = function()
{
	$('.u_inp').filter(function(index) {
		 return $(this).val() =='';
	}).prev().show();
	$(".isnull").prev().hide();
	$(".isnull").removeClass('isnull');
	if($("#login_p_btn").val()){
			$("#user_r_top").html("登录");
		}else{
			$("#user_r_top").html("注册");
	}
	$("#runame").css("color","#999");
	$(".rclose").hide();

}

userInfo.prototype.register = function(url){
	var data = {};
	var url;
			data['uname']=$("#runame").val();
			data['password'] = $.md5($("#rpassword").val());
			data['password2'] = $.md5($("#rpassword2").val());
			data['vcode']  = $("#rcode_char").val();
			data['reg']=1;
			$("#next_btn").attr("disabled","disabled");
			$("#next_btn").css({'color':'#999999','background':'#f2f2f2','border':'1px solid #eaeaea','height':'38px'})
			 $.ajax
				({
					url:url+'/user/register',
					type: "post",
					data:data,
					cache: false,
					timeout: 10000,
					dataType: 'json',
					success: function(obj)
					{
						$("#next_btn").removeAttr("disabled");
						$("#next_btn").css({'color':'#fff','background':'#fe960e','height':'40px'})
						switch (obj.ret)
						{
							case 0:
//							$("#user_r_top").html("完善个人信息")
//							$(".reg_cen").html(userInfo.getUploadView(baseUrl));
//							$("#nickname").val(obj.nickname)

							window.location.reload();
                                                        break;
							case 1:
							alert("发现未知错误，请刷新重试！");
							break;
							case 1100:
							$(".rclose").show();
							$("#runame").css("color","#dd052a");
							$("#user_r_top").html("<span>这个不是邮箱呀 >_< </span>");
							break;
							case 1101:
							$("#user_r_top").html("<span>密码格式错误 </span>");
							break;
							case 1102:
							alert("用户名不存在！");
							break;
							case 1103:
							$(".rclose").show();
							$("#runame").css("color","#dd052a");
							$("#user_r_top").html("<span>邮箱已注册哦 </span>");
							break;
							case 1104:
							$("#user_r_top").html("<span>密码不一致 </span>");
							break;
							case 1105:
							$("#user_r_top").html("<span>验证码错误 </span>");
							$("#getVcode").attr("src",baseUrl+'/views/res/code/captcha.php?' + Math.random());
							break;
							case 1110:
							$("#user_r_top").html("<span>用户名最大长度为16位 </span>");
							break;
							default:
							$("#user_r_top").html("<span>注册失败，请刷新页面重试 </span>");

						}


					},
					error: function()
					{
						$("#user_r_top").html("<span>连接错误！</span>");
						$("#next_btn").removeAttr("disabled");
						$("#next_btn").css({'color':'#fff','background':'#fe960e','height':'40px'})
					}
				});

}
//评论
userInfo.prototype.RepSub = function(url,mid){
	var data = {};
	var url;
			data['mid']= mid;
			data['msg'] = $(".rep_textarea").val();

			$("#subBtnRep").attr("disabled","disabled");
			$("#subBtnRep").css({'color':'#999999','background':'#f2f2f2','border':'1px solid #eaeaea','height':'38px'})
			 $.ajax
				({
					url:url+'/api/discuss?type=3',
					type: "post",
					data:data,
					cache: false,
					timeout: 10000,
					dataType: 'json',
					success: function(obj)
					{
					$("#subBtnRep").removeAttr("disabled");
						$("#subBtnRep").css({'color':'#fff','background':'#fe960e','height':'40px'})
						switch (obj.ret)
						{
							case 0:
							location.reload();
							break;
							default:
							$("#user_r_top").html("<span>评论失败，请刷新页面重试 </span>");

						}


					},
					error: function()
					{
						$("#user_r_top").html("<span>连接错误！</span>");
						$("#subBtnRep").removeAttr("disabled");
						$("#subBtnRep").css({'color':'#fff','background':'#fe960e','height':'40px'})
					}
				});

}

userInfo.prototype.register = function(url){
	var data = {};
	var url;
			data['uname']=$("#runame").val();
			data['password'] = $.md5($("#rpassword").val());
			data['password2'] = $.md5($("#rpassword2").val());
			data['vcode']  = $("#rcode_char").val();
			data['reg']=1;
			$("#next_btn").attr("disabled","disabled");
			$("#next_btn").css({'color':'#999999','background':'#f2f2f2','border':'1px solid #eaeaea','height':'38px'})
			 $.ajax
				({
					url:url+'/user/register',
					type: "post",
					data:data,
					cache: false,
					timeout: 10000,
					dataType: 'json',
					success: function(obj)
					{
						$("#next_btn").removeAttr("disabled");
						$("#next_btn").css({'color':'#fff','background':'#fe960e','height':'40px'})
                                                
						switch (obj.ret)
						{
							case 0:
//							$("#user_r_top").html("完善个人信息")
//							$(".reg_cen").html(userInfo.getUploadView(baseUrl));
//							$("#nickname").val(obj.nickname)
                                                        window.location.reload();
                                                        break;
							case 1:
							alert("发现未知错误，请刷新重试！");
							break;
							case 1100:
							$(".rclose").show();
							$("#runame").css("color","#dd052a");
							$("#user_r_top").html("<span>这个不是邮箱呀 >_< </span>");
							break;
							case 1101:
							$("#user_r_top").html("<span>密码格式错误 </span>");
							break;
							case 1102:
							alert("用户名不存在！");
							break;
							case 1103:
							$(".rclose").show();
							$("#runame").css("color","#dd052a");
							$("#user_r_top").html("<span>邮箱已注册哦 </span>");
							break;
							case 1104:
							$("#user_r_top").html("<span>密码不一致 </span>");
							break;
							case 1105:
							$("#user_r_top").html("<span>验证码错误 </span>");
							$("#getVcode").attr("src",baseUrl+'/views/res/code/captcha.php?' + Math.random());
							break;
							case 1110:
							$("#user_r_top").html("<span>用户名最大长度为16位 </span>");
							break;
							default:
							$("#user_r_top").html("<span>注册失败，请刷新页面重试 </span>");

						}


					},
					error: function()
					{
						$("#user_r_top").html("<span>连接错误！</span>");
						$("#next_btn").removeAttr("disabled");
						$("#next_btn").css({'color':'#fff','background':'#fe960e','height':'40px'})
					}
				});

}

userInfo.prototype.login = function(url){
	var data = {};
			data['uname']=$("#runame").val();
			data['password'] = $.md5($("#rpassword").val());
			data['vcode']  = $("#rcode_char").val();
			data['login']=1;
			$("#login_p_btn").attr("disabled","disabled");
			$("#login_p_btn").css({'color':'#999999','background':'#f2f2f2','border':'#eaeaea','height':'38px'})
			 $.ajax
				({
					url:url+'/user/login',
					type: "post",
					data:data,
					cache: false,
					timeout: 10000,
					dataType: 'json',
					success: function(obj)
					{
						$("#login_p_btn").removeAttr("disabled");
						$("#login_p_btn").css({'color':'#fff','background':'#fe960e','height':'40px'})
						switch (obj.ret)
						{
							case 0:

							location.reload();
							break;
							case 1:
							alert("发现未知错误，请刷新重试！");
							break;
							case 1100:
							$(".rclose").show();
							$("#runame").css("color","#dd052a");
							$("#user_r_top").html("<span>这个不是邮箱呀 >_< </span>");
							break;
							case 1101:
							$("#user_r_top").html("<span>密码格式错误 </span>");
							break;
							case 1102:
							$(".rclose").show();
							$("#runame").css("color","#dd052a");
							$("#user_r_top").html("<span>邮箱未注册哦 </span>");
							break;
							case 1103:
							$(".rclose").show();
							$("#runame").css("color","#dd052a");
							$("#user_r_top").html("<span>邮箱已注册哦 </span>");
							break;
							case 1104:
							$("#user_r_top").html("<span>密码不一致 </span>");
							break;
							case 1105:
							$("#user_r_top").html("<span>验证码错误 </span>");
							$("#getVcode").attr("src",baseUrl+'/views/res/code/captcha.php?' + Math.random());
							break;
							case 1113:
							$("#user_r_top").html("<span>邮箱或密码错误 </span>");
							$("#getVcode").attr("src",baseUrl+'/views/res/code/captcha.php?' + Math.random());
							break;
							default:
							$("#user_r_top").html("<span>注册失败，请刷新页面重试 </span>");

						}


					},
					error: function()
					{
						$("#user_r_top").html("<span>连接错误！</span>");
						$("#next_btn").removeAttr("disabled");
						$("#next_btn").css({'color':'#fff','background':'#fe960e','height':'40px'})
					}
				});

}


userInfo.prototype.editUserInfo = function(url){
	var data = {};
	var url;
			data['nickname']=$("#nickname").val();
			data['tempdir'] = $("#tempdir").val();
			$("#finish").attr("disabled","disabled");
			$("#finish").css({'color':'#999999','background':'#f2f2f2','border':'1px solid #eaeaea','height':'38px'});
                        
			 $.ajax
				({
//                                        url:url+'/user/set_nickname',
//                                        url:url+'/user/set_avatar',
                                        url:url+'/user/set_userinfo',
					type: "post",
					data:data,
					cache: false,
					timeout: 10000,
					dataType: 'json',
					success: function(obj)
					{
						$("#finish").removeAttr("disabled");
						$("#finish").css({'color':'#fff','background':'#fe960e','height':'40px'})

						switch (obj.ret)
						{
							case 0:
							location.reload();
							break;
							case 1108:
							$("#user_r_top").html("<span>昵称已存在！</span>");
							break;
							case 1107:
                                                        $("#user_r_top").html("<span>未作任何更改！</span>");
//							location.reload();
							break;
							case 1109:
							$("#user_r_top").html("<span>昵称最大长度为16位!</span>");
							break;
                                                        case 102:
                                                            if(obj['msg']){
                                                                $("#user_r_top").html("<span>"+obj.msg+"</span>");
                                                            }else{
                                                                if($("#nickname").val() == nickname){
                                                                    $("#user_r_top").html("<span>昵称未更改！</span>");
                                                                }else{
                                                                    $("#user_r_top").html("<span>昵称已存在！</span>");
                                                                }
                                                            }
							break;
							default:
							$("#user_r_top").html("<span>ERRCODE: "+obj.ret+"</span>");
							break;
						}

						//alert(obj)

					},
					error: function()
					{
						$("#user_r_top").html("<span>连接错误！</span>");
						$("#finish").removeAttr("disabled");
						$("#finish").css({'color':'#fff','background':'#fe960e','height':'40px'})
					}
				});

}


