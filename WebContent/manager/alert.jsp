<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>书籍信息修改</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/buka/js/ie9.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/reset.css">
		<link href="${pageContext.request.contextPath}/buka/css/common2.css" rel="stylesheet" type="text/css" media="all">
		<link href="${pageContext.request.contextPath}/buka/css/list.css" rel="stylesheet" type="text/css" media="all">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/commonv=20170705.css">
				<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/index.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/works.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/buka/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/buka/js/identity.js"></script>
<style>
.queryInfo{
width:300px;
word-break: break-all;
}
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
			.queryInfo{
	    	float: left;
	    }
		</style>
<script type="text/javascript">
$(function(){
	var fileMaxSize=1024*1024*5;
			var pictureMaxSize=1024*1024;
				var nameRight=true;
				var noRight=true;
				$(":text[name='name']").change(function(){
					neverChange=false;
					$(this).next("span").remove();
					var $this=$(this);
					var val=$(this).val();
				if(val == ""){
			$("<span class='messageError'>书名不能为空！</span>").insertAfter($(this));		
				}
				else{
		var url="${pageContext.request.contextPath}/alert";
		var args={name:val,time:new Date()};
		$.get(url,args,function(data){
			if(data=="true"){
				$("<span class='messageRight'>√</span>").insertAfter($this);		
				nameRight=true;
			}
			else if(data=="false"){
				$("<span class='messageError'>存在书目信息！</span>").insertAfter($this);		
				nameRight=false;
			}
		})
				}
				})
				
				$(":text[name='no']").change(function(){
					$(this).next("span").remove();
					var $this=$(this);
					var val=$(this).val();
					var isDigit=/^[0-9]*$/.test(val);
				if(val == ""){
			$("<span class='messageError'>编号不能为空！</span>").insertAfter($(this));		
				}
				else if(isDigit == false){
			$("<span class='messageError'>数量应为纯数字整数！(>0)</span>").insertAfter($(this));		
				}
				else{
					var url="${pageContext.request.contextPath}/alert";
		var args={no:val,time:new Date()};
$.get(url,args,function(data){/*data为传过来的h5字符串*/
if(data == "true"){
	$("<span class='messageRight'>√</span>").insertAfter($this);	
	noRight=true;
}
else if(data == "false"){
	$("<span class='messageError'>存在该编号！</span>").insertAfter($this);
	noRight=false;
}
		})
				}
				})
				
				$(":text[name='number']").change(function(){
					$(this).next("span").remove();
					var val=$(this).val();
					var isDigit=/^[0-9]*$/.test(val);
				if(val == ""){
			$(this).val("0");		
				}
				else if(isDigit == false){
			$("<span class='messageError'>数量应为纯数字整数！(>0)！</span>").insertAfter($(this));		
				}
				})
				
				$(":text[name='location']").change(function(){
					$(this).next("span").remove();
					var val=$(this).val();
					var isDigit=/^[0-9]*$/.test(val);
				if(isDigit == false){
			$("<span class='messageError'>数量应为纯数字整数！(>0)</span>").insertAfter($(this));		
				}
				})
			
			$(":button[name='delete']").each(function(){
			$(this).click(function(){
				$this=$(this);
				var book_id=$(".queryInfo").find(":hidden[name='id']").val();
				var no=$(this).next("input").val();
				var flag=confirm("确认删除吗？");
				if(flag==true){
		var url="${pageContext.request.contextPath}/deleteContent";
		var args={book_id:book_id,no:no,time:new Date()};
$.get(url,args,function(data){/*data为传过来的h5字符串*/
if(data == "yes"){
	alert("删除成功");
	$this.parent(".contentFiled").remove();
}
else{
    alert("内部错误或文件受保护")
}
		})
}
				else{
					return false;
				}
			})
			})
	
	var pictureDo=true;
		$(":file[name='coverPicture']").change(function(){
			    $(this).next("span").remove();
				var picture=$(this).get(0).files[0];
				var index=picture.name.lastIndexOf(".");
				var extensionName=picture.name.substr(index+1);
				var size=picture.size;
				if(extensionName != "jpg" && extensionName != "png" && extensionName != "jpeg"){
					$("<span class='messageError'>图片类型必须为(jpg,jpeg,png)</span>").insertAfter($(this));
				pictureDo=false;
				}
				else if(size > pictureMaxSize){
					$("<span class='messageError'>图片超过1M！</span>").insertAfter($(this));
				pictureDo=false;
				}
				else{
					pictureDo=true;
				}
			})
			
			/* $(":file[name='content']").each(function(){
			$(this).change(function(){
				$(this).next("span").remove();
				var content=$(this).get(0).files[0];
				var index=content.name.lastIndexOf(".");
				var extensionName=content.name.substr(index+1);
				var size=content.size;
				if(extensionName != "txt"){
				$("<span class='messageError'>文件类型必须为(txt)</span>").insertAfter($(this));	
				fileDo=false;
				}
				else if(size > fileMaxSize){
			$("<span class='messageError'>文件超过了5M</span>").insertAfter($(this));
				fileDo=false;
				}
				else{
					fileDo=true;
				}
				
			})
			}) */
			
			$("#sb").click(function(){
				var name=$(this).parent(".queryInfo").find(":text[name='name']").val();
					var no=$(this).parent(".queryInfo").find(":text[name='no']").val();
					var type=$(this).parent(".queryInfo").find(":text[name='type']").val();
					var num=$(this).parent(".queryInfo").find(":text[name='number']").val();
					var loca=$(this).parent(".queryInfo").find(":text[name='location']").val();
					var author=$(this).parent(".queryInfo").find(":text[name='author']").val();
					var description=$(this).parent(".queryInfo").find("textarea[name='description']").val();
					var noIsDigit=/^[0-9]*$/.test(no);
					var numIsDigit=/^[0-9]*$/.test(num);
					var locaIsDigit=/^[0-9]*$/.test(loca);	
					if(name== ""){
						alert("书名不能为空！");
						return false;
					}
					else if(locaIsDigit == false){
						alert("位置应为纯数字！");
						return false;
					}
					else if(numIsDigit == false){
						alert("数量应为纯数字整数！");
						return false;
					}
					else if(no == ""){
						alert("编号不能为空！");
						return false;
					}
					else if(noIsDigit == false){
					alert("编号应为纯数字整数！");	
					return false;
					}
					else if(noRight == false){
						alert("编号已被使用！");
						return false;
					}
					else if(nameRight == false){
						alert("书目信息已存在！");
						return false;
					}
					else if(pictureDo == false){
						alert("图片有误");
						return false;
					}
					else{
						alert("保存成功！")
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
        	<li><a href="${pageContext.request.contextPath}/manager/manaQuery.jsp">书籍查询</a></li>
        	<li class="select"><a href="${pageContext.request.contextPath}/manaQuery?id=${param.id}">书籍信息修改</a></li>
        	<li><a href="${pageContext.request.contextPath}/addContent?id=${param.id}">分卷内容添加</a></li>
        	<li><a href="${pageContext.request.contextPath}/userMana">会员管理</a></li> 
        	<li><a href="${pageContext.request.contextPath}/manager/busiMana.jsp">事务管理</a></li>  
    </ul>
</div>
		</div>
		<!--左边完毕-->
		<div class="width_880 floatr">
	<div class="manga_con box_shadow mar_bot">
               <div id="top" data-id="13502342" class="main-header td_lh clearfix">
               <p class="top_name">书本信息修改</p>
			</div> 
			
           <div class="td_main minheight">
						<div class="applyTips"></div>
                        <div class="ap_form">
                        	<!--书本信息位置-->
      <div class="ap_input clearfix">
      <c:choose>
                        	<c:when test="${requestScope.bookInfo==null }">不存在这本书</c:when>
                        	<c:otherwise>
      	<form class="queryInfo" action="${pageContext.request.contextPath}/alert" method="post" enctype="multipart/form-data">
                       
                             <div class="ap_input clearfix">
                             <label class="ap_type fl" for="td_contact">书名：</label><input value="${requestScope.bookInfo.name}" type="text" name="name" maxlength="32" class="input td_contact fl"  id="td_contact" size="35" required />
                             </div>
                             <div class="ap_input clearfix">
                                    <label class="ap_type fl" for="td_contact">编号：</label><input  value="${requestScope.bookInfo.no}" type="text" name="no" maxlength="32" class="input td_contact fl"  id="td_contact" size="35" required>
                             </div>
                              <div class="ap_input clearfix">
                                    <label class="ap_type fl" for="td_contact">数量：</label><input value="${requestScope.bookInfo.number}" type="text" name="number" maxlength="32" class="input td_contact fl"  id="td_contact" size="35" required>
                                </div>
                                 <div class="ap_input clearfix">
                                    <label class="ap_type fl" for="td_contact">地址：</label><input value="${requestScope.bookInfo.location}" type="text" name="location" maxlength="32" class="input td_contact fl"  id="td_contact" size="35" required>
                                </div>
                                 <div class="ap_input clearfix">
                                    <label class="ap_type fl" for="td_contact">作者：</label><input value="${requestScope.bookInfo.author}" type="text" name="author" maxlength="32" class="input td_contact fl"  id="td_contact" size="35" required>
                                </div>
                                 <div class="ap_input clearfix">
                                    <label class="ap_type fl" for="td_contact">类型：</label><input value="${requestScope.bookInfo.type}" type="text" name="type" maxlength="32" class="input td_contact fl"  id="td_contact" size="35" required>
                                </div>
                                 <div class="ap_input clearfix">
                                    <label class="ap_type fl" for="td_contact">描述：</label><textarea name="description" maxlength="500" class="input td_contact fl" id="td_contact" size="100" style="width:500px;height:200px;" required>${requestScope.bookInfo.description}</textarea>
                                </div>
                                  <input type="hidden" name="id" value="${requestScope.bookInfo.id}" />
                                <div class="head-center">
				<img src="${requestScope.bookInfo.pictureurl}"><!--封面-->
			                    </div>
                                 <div class="ap_input clearfix">
                                    <label class="ap_type fl" for="td_contact">重新上传封面：</label><input type="file" name="coverPicture" maxlength="32" class="input td_contact fl" id="td_contact" size="35" >
                                </div>
                
                               <!-- <div class="ap_input ap_input_ptop clearfix">-->
                                	<label class="ap_type fl" for="td_contact"></label>
                                 <button id="sb" type="submit" class="btn_on">保存</button>
                              <div id="apLoading" class="login_loading hide fl"></div>
                               <!-- </div> -->
                               <!--这里写保存后给的信息  -->
                               <c:if test="${requestScope.message==1 }"><span class="messageRight">保存成功√</span></c:if>
                                </form>
                                
                                  <!--以下为内容分卷-->
                             
                             <!--循环-->
                             <c:forEach items="${requestScope.contentList }" var="content">
                             <div class="contentFiled">
                             第${content.no}卷.txt  <a class="btn_on" href="download?id=${requestScope.bookInfo.id}&no=${content.no}">下载</a> 
                             <button type="button" name="delete" class="btn_on">删除</button>
                              <input type="hidden" name="contentNo" value="${content.no}" />	
<button type="button" class="layui-btn" id="${content.no}"><i class="layui-icon"></i>重传第${content.no}卷</button>
                             </div>
                             </c:forEach>
                                <!--循环-->
                      <!--书本信息位置-->
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
	<script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
<script>
	layui.use('upload', function(){
  var $ = layui.jquery
  ,upload = layui.upload;
  upload.render({ //允许上传的文件后缀
	    elem: '#1'
	    ,url: '${pageContext.request.contextPath}/alertContent'
	    ,accept: 'file' //普通文件
	    ,exts: 'txt' //只允许上传压缩文件
	    ,data: {id:${requestScope.bookInfo.id},no:1} //可选项。额外的参数，如：{id: 123, abc: 'xxx'}
	    ,done: function(res){
	    	 if(res.code > 0){
	    	        return layer.msg('上传失败');
	    	      }
	    	      else{
	    	    	  return layer.msg('上传成功');  
	    	      } 
	    }
	  });
  
  upload.render({ //允许上传的文件后缀
	    elem: '#2'
	    ,url: '${pageContext.request.contextPath}/alertContent'
	    ,accept: 'file' //普通文件
	    ,exts: 'txt' //只允许上传压缩文件
	    ,data: {id:${requestScope.bookInfo.id},no:2} //可选项。额外的参数，如：{id: 123, abc: 'xxx'}
	    ,done: function(res){
	      console.log(res)
	    }
	  });
  upload.render({ //允许上传的文件后缀
	    elem: '#3'
	    ,url: '${pageContext.request.contextPath}/alertContent'
	    ,accept: 'file' //普通文件
	    ,exts: 'txt' //只允许上传压缩文件
	    ,data: {id:${requestScope.bookInfo.id},no:3} //可选项。额外的参数，如：{id: 123, abc: 'xxx'}
	    ,done: function(res){
	      console.log(res)
	    }
	  });
  upload.render({ //允许上传的文件后缀
	    elem: '#4'
	    ,url: '${pageContext.request.contextPath}/alertContent'
	    ,accept: 'file' //普通文件
	    ,exts: 'txt' //只允许上传压缩文件
	    ,data: {id:${requestScope.bookInfo.id},no:4} //可选项。额外的参数，如：{id: 123, abc: 'xxx'}
	    ,done: function(res){
	      console.log(res)
	    }
	  });
  upload.render({ //允许上传的文件后缀
	    elem: '#5'
	    ,url: '${pageContext.request.contextPath}/alertContent'
	    ,accept: 'file' //普通文件
	    ,exts: 'txt' //只允许上传压缩文件
	    ,data: {id:${requestScope.bookInfo.id},no:5} //可选项。额外的参数，如：{id: 123, abc: 'xxx'}
	    ,done: function(res){
	      console.log(res)
	    }
	  });
  upload.render({ //允许上传的文件后缀
	    elem: '#6'
	    ,url: '${pageContext.request.contextPath}/alertContent'
	    ,accept: 'file' //普通文件
	    ,exts: 'txt' //只允许上传压缩文件
	    ,data: {id:${requestScope.bookInfo.id},no:6} //可选项。额外的参数，如：{id: 123, abc: 'xxx'}
	    ,done: function(res){
	      console.log(res)
	    }
	  });
  upload.render({ //允许上传的文件后缀
	    elem: '#7'
	    ,url: '${pageContext.request.contextPath}/alertContent'
	    ,accept: 'file' //普通文件
	    ,exts: 'txt' //只允许上传压缩文件
	    ,data: {id:${requestScope.bookInfo.id},no:7} //可选项。额外的参数，如：{id: 123, abc: 'xxx'}
	    ,done: function(res){
	      console.log(res)
	    }
	  });
  upload.render({ //允许上传的文件后缀
	    elem: '#8'
	    ,url: '${pageContext.request.contextPath}/alertContent'
	    ,accept: 'file' //普通文件
	    ,exts: 'txt' //只允许上传压缩文件
	    ,data: {id:${requestScope.bookInfo.id},no:8} //可选项。额外的参数，如：{id: 123, abc: 'xxx'}
	    ,done: function(res){
	    	 if(res.code > 0){
	    	        return layer.msg('上传失败');
	    	      }
	    	      else{
	    	    	  return layer.msg('上传成功');  
	    	      } 
	    }
	  });
	})
</script>
</body>
</html>