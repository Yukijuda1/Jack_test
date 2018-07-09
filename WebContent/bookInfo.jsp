
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${requestScope.bookInfo.name }</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/buka/js/ie9.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/reset.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/commonv=20170705.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/dmzj/css/conmment_new-15.10.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/dmzj/css/float_code.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/dmzj/css/global.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/dmzj/css/mhStyle.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/dmzj/css/openWd.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/dmzj/css/scribe_layer.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/dmzj/css/ucenterland.css">
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
		<script src="${pageContext.request.contextPath}/Ajax/jquery-1.7.2.js" type="text/javascript"></script>
		<script type="text/javascript">
		$(function(){
			var clickcount=1;
			$(".icon_ding").click(function(){
				var parisecount=$(this).children("em").text();
				var comment_id=$(this).prev("input").val();
				if(clickcount%2==1){
					$(this).children("em").text(Number(parisecount)+1);
					clickcount++;
					var url="${pageContext.request.contextPath}/parise";
					var args={id:comment_id,method:"parise",time:new Date()};/*请求的数据放到json里  */
					$.get(url,args,function(data){/*data为传过来的h5字符串*/
					})
				}
				else if(clickcount%2==0){
					$(this).children("em").text(Number(parisecount)-1);
					clickcount++;
					var url="${pageContext.request.contextPath}/parise";
					var args={id:comment_id,method:"disparise",time:new Date()};/*请求的数据放到json里  */
					$.get(url,args,function(data){/*data为传过来的h5字符串*/
					})
				}
			})
			
	/* 		$(".SubmitBtn").click(function(){
				alert("评论成功");
			}) */
			
			$(".handl.good").click(function(){
				var flag=confirm("确定删除吗？")
				var $this=$(this);
				if(flag==true){
					var comment_id=$(this).next("input").val();
					var url="${pageContext.request.contextPath}/comment";
					var args={id:comment_id,time:new Date()};/*请求的数据放到json里  */
					$.get(url,args,function(data){/*data为传过来的h5字符串*/
					})
					alert("删除成功！")
					$this.parent(".btm_bar").parent(".content_r.autoHeight").parent(".comment_con_li.autoHeight").remove();
				}
				return false;
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
                    <a href="${pageContext.request.contextPath}/mySpace" title="个人中心" class="nav-btn  ">
                        个人中心
                    </a>
               </div>
			<form action="search" method="post"> 
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
	<!--导航条结束-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/detail.css">
	<section class="main">
		<!--左边-->
<aside class="left">
			<div class="manga-img boxshadow" style="background:#fff;">
								<img src="${requestScope.bookInfo.pictureurl }" title="" width="246" height="361" alt="">
			</div>
						<div class="contribute-author boxshadow">
				<div class="author-wrap">
					<a href="tencent://AddContact/?fromId=50&fromSubId=1&subcmd=all&uin=512748799" class="author-img">
				<img src="http://localhost/lib/imgs/1526285201514.jpg" width="64">
					<!--头像图片-->
					</a>
					<div class="author-names">
						<a href="tencent://AddContact/?fromId=50&fromSubId=1&subcmd=all&uin=512748799" class="author-name">
							管理员：刘萧然						</a>
						<span style="display: inline-block;width: 145px;">
                        上传了书籍：${requestScope.bookInfo.name }</span>
					</div>
				</div>
			</div>
			<!--推荐-->
			<div class="recommend boxshadow">
				<h3 class="re-title" style="font-weight: normal">
					同类作品推荐
				</h3>
				<!--循环-->
			<c:forEach items="${requestScope.sameBooks }" var="sameBook">
				<div class="re-lists">
				<div class="re-box">
			<a href="bookInfo?id=${sameBook.id }" title="${sameBook.name }" class="re-img">
							<img src="${sameBook.pictureurl }" title="${sameBook.name }" width="84" height="126">
						</a>
					<div class="re-desc">
					<a href="bookInfo?id=${sameBook.id}" title="查看:${sameBook.name }" class="new-icon">${sameBook.name }</a>
					<div class="re-names">
					<a href="search?author=${sameBook.id}" title="搜索:${sameBookauthor }">${sameBook.author }</a>
					<a href="search?type=${sameBook.id}" title="搜索:${sameBook.type }">${sameBook.type }</a>
				<span class="rate-out">
					<span class="rate-inner" style="width:95%">这是一本很棒的书~</span>
					</span>
							</div>
						</div>
					</div>
								</div>
			</c:forEach>
			<!--循环-->
			</div>
			<!--推荐end-->
		</aside>
		<!--左边借书-->
		<!--右边开始-->
		<div class="right box_shadow">
			<div class="manga-title">
				<div class="wrap">
					<h1 class="title-font">
						${bookInfo.name }		</h1>
				</div>
				<!--添加标签  -->
				<a href="borrowInfo?id=${bookInfo.id }" id="startRead" class="btn-primary pull-right">借阅</a>
			</div>
			<!--标题+两个链接-->
			<div class="manga-desc">
				<div class="manga-author">
					作者<a href="search?author=${bookInfo.id }" title="搜索作者:${bookInfo.author }" class="author">
                       ${bookInfo.author }</a><!--更新<span class="time">2018-03-23</span>-->
				</div>
				 <!--作者-->
				<p class="manga-desc-font">
					
					${bookInfo.description }
					
				</p>
				<!--介绍-->
				<div class="manga-btns">
					<!--分享-->
					<div class="page-share">
						<span class="share-title">
							分享给好友
						</span>
						<!--分享-->
						<style>
							.bdsharebuttonbox,.share-btns{margin-top:8px;}
							.bdsharebuttonbox{overflow: hidden;}
							.bdsharebuttonbox>a{width:25px !important;float:left;margin:0 5px;cursor: pointer;}
						</style>
						<div class="share-btns">
							<div class="bdsharebuttonbox">
								<a title="分享到QQ空间" cmd="qzone" class="share-qzone qzonebtn"></a>
								<a title="分享到QQ好友" cmd="qim" class="share-qqim sqqbtn"></a>
								<a title="分享到新浪微博" cmd="sina" class="share-sina sinabtn"></a>
							</div>
						</div>
						<!--分享end-->
					</div>
				</div>
				
				<div class="manga-episodes">
					<span class="manga-episodes_line"></span>
					<span class="manga-episodes_text">内容分卷:</span>
				</div>
				<!--标题-->
				
				<div class="manga-episodes" id="episodes">
					<div class="epsbox">
					<c:forEach items="${requestScope.contents }" var="content">
					<a class="epsbox-eplink " title="${bookInfo.name }:第${content.no }卷 " target="_blank" href="content?id=${bookInfo.id }&no=${content.no}">
						第${content.no }卷</a>
						<a href="download?id=${bookInfo.id }&no=${content.no}" id="keepingRead" class="btn-primary pull-right">下载</a>
						<br />
						</c:forEach>
					</div>
					<div class="clear"></div>
					<div class="pageing" id="pageing"></div>
				</div>
		</div>

	</section>
	<!--中间结束-->
	
	<!-- 评论区 -->
	<div class="wrap autoHeight">
	  <div class="wrap_intro_1 widthEigLeft con_left">
	       <div class="comment" id="Comment" style="margin-top:-50px;margin-left:50px">
	<iframe id="upload_target" name="upload_target" src="about:blank" style="display: none"></iframe>
	<input type="hidden" id="hiddenInput" value="">
	<!--评论文本域  -->
	<div class="commentBox">
	<!--标题  -->
	<h2><span class="comment_num"><em class="total"> ${requestScope.commentSize }</em>条评论</span><span class="t_c">网友</span>评论</h2>
	<!--填写评论  -->
	<div class=textareawrap>
	<form action="${pageContext.request.contextPath}/comment" method="post">
	<div class="textareaDiv">
	<textarea name="textfield" class="textare" onkeyup="text_s(this,0,0,0)"></textarea>
	</div>
	<div class="func_area">
	<div class="ficon_swtop" style="display: none"></div>
	<div class="ficon_topic" style="display: none"></div>
	<div class="loginName">
	<c:choose>
	<c:when test="${sessionScope.user!=null }"><a href="${pageContext.request.contextPath}/mySpace" class="name">${sessionScope.user.username }</a>|<a href="${pageContext.request.contextPath}/logout"  class="out">注销</a></c:when>
	<c:otherwise><a href="${pageContext.request.contextPath}/login.jsp" class="name">登陆</a>|<a href="${pageContext.request.contextPath}/register.jsp"  class="name">快去注册吧~</a>
	</c:otherwise>
	</c:choose></div>
	<input name="book_id" type="hidden"  value="${requestScope.bookInfo.id }">
	<button type="submit" class="SubmitBtn">发表评论</button>
	<p class="num_S_txt">您还可以输入<span>1000</span>字</p>
	</div>
	</form>
	</div>
	<!-- 评论区 -->
	<div class="comment_con">
	<div class="comment_con_tab con_select">
	<div id="commentAll">
	<c:choose>
	<c:when test="${requestScope.comments==null }">目前该书没有评论，快去写一段吧~</c:when>
	<c:otherwise>
	<!--循环评论  -->
	<c:forEach items="${requestScope.comments }" var="comment">
	<div class="comment_con_li autoHeight"><!--删除这个快  -->
	<a name="new_box_4728465"></a>
	<div class="photo "><img src="${comment.pictureurl }" width="58" height="58" alt="" >
	</div>
	<div class="content_r autoHeight"><!--第二父亲  -->
	<div class="info_bar"><span>${comment.username }</span></div>
	<div class="reply_content" id="new_reply_4728465"></div>
	<p class="text"><span class="textCon">${comment.content }</span></p>
	<ul class="upload_big_img"></ul><ul class="uploadImg autoHeight" id="upload_4728465"></ul>
	<div class="btm_bar"><!--第三父亲  -->
	<span class="time">${comment.createtime }</span>
	<c:choose>
	<c:when test="${sessionScope.auth_name eq 'manager' }"><a class="handl good" >删除</a></c:when>
	<c:when test="${comment.username eq sessionScope.user.username }"><a class="handl good" >删除</a></c:when>
	</c:choose>
	<input type="hidden" value="${comment.id }">
	<a class="icon_ding" >点赞(<em>${comment.praisecount }</em>)</a></div></div></div>
	</c:forEach>
	<!--循环结束  -->
	</c:otherwise>
	</c:choose>
	</div>
	</div>
	</div>
	</div>
	
	     </div>
	   </div>
	</div>
	
	<!-- 评论区结束 -->
	<script>
        var chapters = {"n":[{"cid":"65556","idx":"20","type":"0","title":"\u711a\u5fc3\u672f3","size":"2277","ressupport":"7","mtype":"0"},{"cid":"65555","idx":"19","type":"0","title":"\u711a\u5fc3\u672f2","size":"2313","ressupport":"7","mtype":"0"},{"cid":"65554","idx":"18","type":"0","title":"\u711a\u5fc3\u672f1","size":"2660","ressupport":"7","mtype":"0"},{"cid":"65553","idx":"17","type":"0","title":"\u4e0e\u86c7\u4e664","size":"2444","ressupport":"7","mtype":"0"},{"cid":"65552","idx":"16","type":"0","title":"\u4e0e\u86c7\u4e663","size":"2701","ressupport":"7","mtype":"0"},{"cid":"65551","idx":"15","type":"0","title":"\u4e0e\u86c7\u4e662","size":"2620","ressupport":"7","mtype":"0"},{"cid":"65550","idx":"14","type":"0","title":"\u4e0e\u86c7\u4e661","size":"2517","ressupport":"7","mtype":"0"},{"cid":"65549","idx":"13","type":"0","title":"\u6674\u65f6\u96e83","size":"2552","ressupport":"7","mtype":"0"},{"cid":"65548","idx":"12","type":"0","title":"\u6674\u65f6\u96e82","size":"2473","ressupport":"7","mtype":"0"},{"cid":"65547","idx":"11","type":"0","title":"\u6674\u65f6\u96e81","size":"2517","ressupport":"7","mtype":"0"},{"cid":"65546","idx":"10","type":"0","title":"\u7e41\u82b1\u5c3d5","size":"2305","ressupport":"7","mtype":"0"},{"cid":"65545","idx":"9","type":"0","title":"\u7e41\u82b1\u5c3d4","size":"2184","ressupport":"7","mtype":"0"},{"cid":"65544","idx":"8","type":"0","title":"\u7e41\u82b1\u5c3d3","size":"2258","ressupport":"7","mtype":"0"},{"cid":"65543","idx":"7","type":"0","title":"\u7e41\u534e\u5c3d2","size":"2277","ressupport":"7","mtype":"0"},{"cid":"65542","idx":"6","type":"0","title":"\u7e41\u82b1\u5c3d1","size":"2265","ressupport":"7","mtype":"0"},{"cid":"65541","idx":"5","type":"0","title":"\u5927\u68a6\u5f524","size":"3436","ressupport":"7","mtype":"0"},{"cid":"65540","idx":"4","type":"0","title":"\u5927\u68a6\u5f523","size":"2229","ressupport":"7","mtype":"0"},{"cid":"65539","idx":"3","type":"0","title":"\u5927\u68a6\u5f522","size":"2360","ressupport":"7","mtype":"0"},{"cid":"65538","idx":"2","type":"0","title":"\u5927\u68a6\u5f521","size":"2316","ressupport":"7","mtype":"0"},{"cid":"65537","idx":"1","type":"0","title":"\u591c\u6cb3\u706f","size":"3135","ressupport":"7","mtype":"0"}],"vol":[],"sp":[{"cid":"196610","idx":"2","type":"2","title":"\u6625\u8282\u756a\u59162","size":"3064","ressupport":"7","mtype":"0"},{"cid":"196609","idx":"1","type":"2","title":"\u6625\u8282\u756a\u59161","size":"2882","ressupport":"7","mtype":"0"}]};
    </script>
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
<script>
    $(function(){
        isSubscribe(comic_id,0);
        isFocus(author_id,author_name);
    })
</script>
<script type="text/javascript"   async  src="${pageContext.request.contextPath}/dmzj/js/dmzjComment-16.02.js" charset="UTF-8" id="comment"></script>
	<!-- dmzj js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/buka/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/buka/js/jquery.md5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/buka/js/commonv20170602.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/buka/js/template.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/buka/js/buka_share.js"></script>
<script type="text/javascript">
		//分享参数
		share.config = {
			url:location.href,
			title:'${bookInfo.name}', /*书名*/
			desc:'',
			summary:'${bookInfo.description}\"', /*简介  */
			appkey:{
				sina:'3571673933'
			},
			site:'',
			pic:'${bookInfo.pictureurl}'
		}
	</script>
</body>
</html>