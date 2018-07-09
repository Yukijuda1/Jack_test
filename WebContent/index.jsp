<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/buka/js/ie9.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/reset.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/commonv=20170705.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/category.css">
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
					<a href="${pageContext.request.contextPath}/index.jsp" title="首页" class="nav-btn active">
							首页
					</a>
                    <a href="${pageContext.request.contextPath}/manager/managerIndex.jsp" title="管理" class="nav-btn">
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
	<!--导航条-->
	<div class="main">
			<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/index2.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/animate.min2.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/imgturn.css">
	<style>
        .link-img{height:auto !important;}
        .link-box{overflow: hidden;}
        .partner-link{display:block;float:left;margin-bottom:10px;margin-right:7px !important;}
        .title-font{padding-left:0 !important;font-weight: bold;margin-left:17px !important;text-indent: 0px !important;}
    </style>
    <script type="text/html" id="userpancel">
		<div class="user-pancel boxshadow" id="userinfopancel" >
		<div class="user-edit">
			<a href="javascript:loginout()" id="loginout" style="color:#999;">退出</a>
			</div>
			<div class="user-center">
				<img class="user-avatar" src="{{avatar}}" alt="" style="margin-top:10px;" />
				<a href="javascript:;" style="margin-top:20px;" class="user-name" id="editnickname" >{{nickname}}</a>
				<a href="/pay/index"  id="pay" class="user-contribute" style="background:#F8B62D;color:#fff;border:none;margin-top:20px;">充值</a>
			</div>
		</div>
	</script>
	<script type="text/html" id="editNicknameTmp">
			<div id="regsiterwrap">
			<div id="modal-overlay">
			</div>
			<div class="modal-wrap" id="modal-content">
			<div class="modal-data boxshadow" style="height:100px;">	
				<a href="javascript:;" id="closeBoxShadow" >X</a>	
				<p class="title" id="regtitle" style="margin-left:20px;position:absolute;top:20px;">完善个人信息</p>
				<div class="regform">
					<div class="fromgroup" style="width:160px">
						<style>
							.editnickname{
								padding-left:40px !important;
							}
						</style>
						<span class="nicknametag">昵称:</span>
						<input type="text" class="form-controller editnickname" id="nickname" name="nickname" placeholder="请输入新昵称（2-16个字符）" value="{{nickname}}"  />
					</div>
					<div class="fromgroup" >
						<a href="javascript:;" class="regbtn" id="editnicknamebtn">完 成</a>
					</div>
				</div>
			</div>
			</div>
			</div>
	</script>
    <section class="main"  style="overflow: hidden">
    <aside class="left">
	<div class="imgtrun boxshadow" id="imgtrun">
				<ul class="img-trun-lists">
										<li style="display: list-item;">
						<a href="bookInfo?id=92" target="_blank">
							<img src="http://localhost/lib/imgs/lunbo4.jpg" alt="" width="1130" height="220">
						</a>
					</li>
						<!--2  -->
										<li style="display: none;">
						<a href="#" target="_blank">
							<img src="http://localhost/lib/imgs/lunbo1.jpg" alt="" width="1130" height="220">
						</a>
					</li>
						<!--3  -->
					<li style="display: none;">
						<a href="#" target="_blank">
							<img src="http://localhost/lib/imgs/lunbo3.png" alt="" width="1130" height="220">
						</a>
					</li>
							<!--4 -->
					<li style="display: none;">
						<a href="#" target="_blank">
							<img src="http://localhost/lib/imgs/lunbo2.jpg" alt="" width="1130" height="220">
						</a>
					</li>

					
									</ul>
				<div class="imgtrun-foot">
					<ul class="img-trun-icon">
					<li class="icon_1 select"></li>
													<li class="icon_2"></li>
													<li class="icon_3 "></li>
													<li class="icon_4"></li>
													
											</ul>
				</div>
			</div>
			</aside>
			</section>
			<!--轮播图结束 -->
	<!--第一行  -->
		<!--左边书架-->
		<div class="category-box boxshadow ">
			<div class="category-title">
				<h5 style="font-weight: normal;" class="title-font">军事</h5>
								<a href="${pageContext.request.contextPath}/search?type=35" target="_blank" class="more-btn">更多</a>
			</div>
			<div class="category-manga">
				<ul>
										<li>
						<a href="bookInfo?id=35" title="军事方法学" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526266924142.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=35" title="军事方法学" target="_blank" class="manga-name">
								军事方法学							</a>
							<div class="manga-author">
								<a href="search?author=35" target="_blank" title="搜索作者:梁必骎">
									梁必骎								</a>
								<a href="search?type=35" title="搜索类型:军事" target="_blank">
									军事						</a>
							</div>
						</div>
					</li>
										<li class="li-center">
						<a href="bookInfo?id=36" title="论军事潜力" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526267203359.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=36" title="论军事潜力" target="_blank" class="manga-name">
								论军事潜力							</a>
							<div class="manga-author">
								<a href="search?author=36" target="_blank" title="搜索作者:刘建军">
								刘建军							</a>
								<a href="search?type=36" title="搜索类型:军事" target="_blank">
									军事								</a>
							</div>
						</div>
					</li>
										<li>
						<a href="bookInfo?id=42" title="军事装备史" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526268994943.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=42" title="军事装备史" target="_blank" class="manga-name">
								军事装备史						</a>
							<div class="manga-author">
								<a href="search?author=42" target="_blank" title="搜索作者:郭世贞/裴美成">
									郭世贞/裴美成								</a>
								<a href="search?type=42" title="搜索类型:军事" target="_blank">
									军事								</a>
							</div>
						</div>
					</li>
									</ul>
			</div>
		</div>
		<!--中间架-->
			<div class="category-box boxshadow category-center">
			<div class="category-title">
				<h5 style="font-weight: normal;" class="title-font">教育</h5>
								<a href="${pageContext.request.contextPath}/search?type=44" target="_blank" class="more-btn">更多</a>
			</div>
			<div class="category-manga">
				<ul>
										<li>
						<a href="bookInfo?id=43" title="观点" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526274688159.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=代表书籍id" title="观点" target="_blank" class="manga-name">
								观点						</a>
							<div class="manga-author">
								<a href="search?author=43" target="_blank" title="搜索作者:林清华">
									林清华								</a>
								<a href="search?type=代表书籍id" title="搜索类型:教育" target="_blank">
									教育/科学					</a>
							</div>
						</div>
					</li>
										<li class="li-center">
						<a href="bookInfo?id=44" title="性教育" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526274906413.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=44" title="性教育" target="_blank" class="manga-name">
								性教育							</a>
							<div class="manga-author">
								<a href="search?author=44" target="_blank" title="搜索作者:江汉声/晏涵文">
									江汉声/晏涵文								</a>
								<a href="search?type=44" title="搜索漫画作者:" target="_blank">
								   教育						</a>
							</div>
						</div>
					</li>
										<li>
						<a href="bookInfo?id=45" title="狼" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526275076771.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=45" title="狼" target="_blank" class="manga-name">
								狼						</a>
							<div class="manga-author">
								<a href="serach?author=45" target="_blank" title="搜索作者:童世军">
									童世军								</a>
								<a href="serach?type=45" title="搜索类型:教育" target="_blank">
									教育								</a>
							</div>
						</div>
					</li>
									</ul>
			</div>
		</div>
		<!--右侧书架 -->
				<div class="category-box boxshadow ">
			<div class="category-title">
				<h5 style="font-weight: normal;" class="title-font">经济</h5>
								<a href="${pageContext.request.contextPath}/search?type=50" target="_blank" class="more-btn">更多</a>
			</div>
			<div class="category-manga">
				<ul>
										<li>
						<a href="bookInfo?id=49" title="教育经济学" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526276290399.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=49" title="教育经济学" target="_blank" class="manga-name">
								教育经济学							</a>
							<div class="manga-author">
								<a href="serach?author=49" target="_blank" title="搜索作者:刘宝超">
									刘宝超								</a>
								<a href="serach?type=49" title="搜索类型:教育/经济" target="_blank">
									教育/经济								</a>
							</div>
						</div>
					</li>
										<li class="li-center">
						<a href="bookInfo?id=50" title="两岸经济合作" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526276467251.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=50" title="两岸经济合作" target="_blank" class="manga-name">
								两岸经济合作						</a>
							<div class="manga-author">
								<a href="serach?author=50" target="_blank" title="搜索作者:石正方">
									石正方								</a>
								<a href="serach?type=50" title="搜索类型:经济" target="_blank">
							经济							</a>
							</div>
						</div>
					</li>
										<li>
						<a href="bookInfo?id=51" title="每天经济学" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526276610311.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=51" title="每天经济学" target="_blank" class="manga-name">
								每天经济学						</a>
							<div class="manga-author">
								<a href="serach?author=51" target="_blank" title="搜索作者:刘晓宁">
									刘晓宁							</a>
								<a href="serach?type=51" title="搜索类型:经济/研究" target="_blank">
								经济/研究							</a>
							</div>
						</div>
					</li>
									</ul>
			</div>
		</div>
		<!--右侧书架  -->
		<!--第一行结束-->
		
		<!-- 第二行开始 -->
			<div class="category-box boxshadow ">
			<div class="category-title">
				<h5 style="font-weight: normal;" class="title-font">文学</h5>
								<a href="${pageContext.request.contextPath}/search?type=55" target="_blank" class="more-btn">更多</a>
			</div>
			<div class="category-manga">
				<ul>
										<li>
						<a href="bookInfo?id=55" title="文学论" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526278211919.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=55" title="文学论" target="_blank" class="manga-name">
								军事方法学							</a>
							<div class="manga-author">
								<a href="search?author=55" target="_blank" title="搜索作者:(日)夏目漱石">
									(日)夏目漱石							</a>
								<a href="search?type=55" title="搜索类型:文学" target="_blank">
									文学					</a>
							</div>
						</div>
					</li>
										<li class="li-center">
						<a href="bookInfo?id=53" title="汉唐文学" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526277718233.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=53" title="汉唐文学" target="_blank" class="manga-name">
								汉唐文学							</a>
							<div class="manga-author">
								<a href="search?author=53" target="_blank" title="搜索作者:黎活仁/方环海">
								黎活仁/方环海						</a>
								<a href="search?type=53" title="搜索类型:文学/经济/研究" target="_blank">
									文学/经济/研究							</a>
							</div>
						</div>
					</li>
										<li>
						<a href="bookInfo?id=54" title="惊世之书" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526277866540.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=54" title="惊世之书" target="_blank" class="manga-name">
								惊世之书						</a>
							<div class="manga-author">
								<a href="search?author=54" target="_blank" title="搜索作者:周伟主">
									周伟主								</a>
								<a href="search?type=54" title="搜索类型:军事" target="_blank">
									文学							</a>
							</div>
						</div>
					</li>
									</ul>
			</div>
		</div>
		<!--中间架-->
			<div class="category-box boxshadow category-center">
			<div class="category-title">
				<h5 style="font-weight: normal;" class="title-font">艺术</h5>
								<a href="${pageContext.request.contextPath}/search?type=57" target="_blank" class="more-btn">更多</a>
			</div>
			<div class="category-manga">
				<ul>
										<li>
						<a href="bookInfo?id=56" title="当代室内艺术" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526278600436.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=56" title="当代室内艺术" target="_blank" class="manga-name">
								当代室内艺术						</a>
							<div class="manga-author">
								<a href="search?author=56" target="_blank" title="搜索作者:杨迅佳">
									杨迅佳								</a>
								<a href="search?type=56" title="搜索类型:经济/艺术" target="_blank">
									经济/艺术					</a>
							</div>
						</div>
					</li>
										<li class="li-center">
						<a href="bookInfo?id=57" title="艺术概论" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526278727073.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=57" title="艺术概论" target="_blank" class="manga-name">
								艺术概论							</a>
							<div class="manga-author">
								<a href="search?author=57" target="_blank" title="搜索作者:傅小英">
									傅小英								</a>
								<a href="search?type=57" title="搜索类型:艺术" target="_blank">
								   艺术						</a>
							</div>
						</div>
					</li>
										<li>
						<a href="bookInfo?id=58" title="艺术学导论" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526278815683.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=58" title="艺术学导论" target="_blank" class="manga-name">
								艺术学导论						</a>
							<div class="manga-author">
								<a href="serach?author=58" target="_blank" title="搜索作者:田川流/刘家亮">
									田川流/刘家亮								</a>
								<a href="serach?type=58" title="搜索类型:艺术	" target="_blank">
									艺术								</a>
							</div>
						</div>
					</li>
									</ul>
			</div>
		</div>
		<!--右侧书架 -->
				<div class="category-box boxshadow ">
			<div class="category-title">
				<h5 style="font-weight: normal;" class="title-font">历史</h5>
								<a href="${pageContext.request.contextPath}/search?type=60" target="_blank" class="more-btn">更多</a>
			</div>
			<div class="category-manga">
				<ul>
										<li>
						<a href="bookInfo?id=59" title="烩历史" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526278947588.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=59" title="烩历史" target="_blank" class="manga-name">
								烩历史							</a>
							<div class="manga-author">
								<a href="serach?author=59" target="_blank" title="搜索作者:杜宏娟">
									杜宏娟								</a>
								<a href="serach?type=59" title="搜索类型:历史/文学" target="_blank">
									历史/文学							</a>
							</div>
						</div>
					</li>
										<li class="li-center">
						<a href="bookInfo?id=60" title="Q版历史" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526279044927.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=60" title="Q版历史" target="_blank" class="manga-name">
								Q版历史						</a>
							<div class="manga-author">
								<a href="serach?author=60" target="_blank" title="搜索作者:陆明">
									陆明								</a>
								<a href="serach?type=60" title="搜索类型:历史" target="_blank">
							历史							</a>
							</div>
						</div>
					</li>
										<li>
						<a href="bookInfo?id=61" title="历史的选择" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526279165049.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=61" title="历史的选择" target="_blank" class="manga-name">
								历史的选择						</a>
							<div class="manga-author">
								<a href="serach?author=61" target="_blank" title="搜索作者:刘统">
									刘统							</a>
								<a href="serach?type=61" title="搜索类型:历史/军事" target="_blank">
								历史/军事						</a>
							</div>
						</div>
					</li>
									</ul>
			</div>
		</div>
<!--第二行结束  -->

<!-- 第三行开始 -->
	<div class="category-box boxshadow ">
			<div class="category-title">
				<h5 style="font-weight: normal;" class="title-font">政治</h5>
								<a href="${pageContext.request.contextPath}/search?type=62" target="_blank" class="more-btn">更多</a>
			</div>
			<div class="category-manga">
				<ul>
										<li>
						<a href="bookInfo?id=62" title="政治与人" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526280665864.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=62" title="政治与人" target="_blank" class="manga-name">
								政治与人							</a>
							<div class="manga-author">
								<a href="search?author=62" target="_blank" title="搜索作者:(日)加藤节">
									(日)加藤节							</a>
								<a href="search?type=62" title="搜索类型:政治" target="_blank">
									政治				</a>
							</div>
						</div>
					</li>
										<li class="li-center">
						<a href="bookInfo?id=63" title="政治学概论" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526282674111.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=63" title="政治学概论" target="_blank" class="manga-name">
								政治学概论							</a>
							<div class="manga-author">
								<a href="search?author=63" target="_blank" title="搜索作者:臧乃康/韩裕庆">
								臧乃康/韩裕庆						</a>
								<a href="search?type=63" title="搜索类型:政治" target="_blank">
									政治							</a>
							</div>
						</div>
					</li>
										<li>
						<a href="bookInfo?id=64" title="性与政治" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526288638923.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=64" title="性与政治" target="_blank" class="manga-name">
								性与政治						</a>
							<div class="manga-author">
								<a href="search?author=64" target="_blank" title="搜索作者:周伟主">
									周伟主								</a>
								<a href="search?type=64" title="搜索类型:政治/经济/性学" target="_blank">
									政治/经济/性学							</a>
							</div>
						</div>
					</li>
									</ul>
			</div>
		</div>
		<!--中间架-->
			<div class="category-box boxshadow category-center">
			<div class="category-title">
				<h5 style="font-weight: normal;" class="title-font">科学</h5>
								<a href="${pageContext.request.contextPath}/search?type=66" target="_blank" class="more-btn">更多</a>
			</div>
			<div class="category-manga">
				<ul>
										<li>
						<a href="bookInfo?id=66" title="看！科学主义" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526288988926.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=66" title="看！科学主义" target="_blank" class="manga-name">
								看！科学主义						</a>
							<div class="manga-author">
								<a href="search?author=66" target="_blank" title="搜索作者:江晓原">
									江晓原								</a>
								<a href="search?type=66" title="搜索类型:科学" target="_blank">
									科学				</a>
							</div>
						</div>
					</li>
										<li class="li-center">
						<a href="bookInfo?id=67" title="科学意象" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526289100815.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=67" title="科学意象" target="_blank" class="manga-name">
								科学意象							</a>
							<div class="manga-author">
								<a href="search?author=67" target="_blank" title="搜索作者:李继宏/杨建邺">
									李继宏/杨建邺								</a>
								<a href="search?type=67" title="搜索类型:艺术" target="_blank">
								   科学/社会						</a>
							</div>
						</div>
					</li>
										<li>
						<a href="bookInfo?id=58" title="科学的价值" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526289310817.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=58" title="科学的价值" target="_blank" class="manga-name">
								科学的价值						</a>
							<div class="manga-author">
								<a href="serach?author=58" target="_blank" title="搜索作者:(法)昂利·彭加勒">
									(法)昂利·彭加勒							</a>
								<a href="serach?type=58" title="搜索类型:科学/价值论	" target="_blank">
									科学/价值论								</a>
							</div>
						</div>
					</li>
									</ul>
			</div>
		</div>
		<!--右侧书架 -->
				<div class="category-box boxshadow ">
			<div class="category-title">
				<h5 style="font-weight: normal;" class="title-font">法律</h5>
								<a href="${pageContext.request.contextPath}/search?type=70" target="_blank" class="more-btn">更多</a>
			</div>
			<div class="category-manga">
				<ul>
										<li>
						<a href="bookInfo?id=69" title="法律基础" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526289529998.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=69" title="法律基础" target="_blank" class="manga-name">
								法律基础							</a>
							<div class="manga-author">
								<a href="serach?author=69" target="_blank" title="搜索作者:王月霞/杨平">
									王月霞/杨平								</a>
								<a href="serach?type=69" title="搜索类型:法律/社会" target="_blank">
									法律/社会						</a>
							</div>
						</div>
					</li>
										<li class="li-center">
						<a href="bookInfo?id=70" title="法律审美" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526289813471.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=70" title="法律审美" target="_blank" class="manga-name">
								法律审美						</a>
							<div class="manga-author">
								<a href="serach?author=70" target="_blank" title="搜索作者:娄耀雄">
									娄耀雄							</a>
								<a href="serach?type=70" title="搜索类型:法律/美学" target="_blank">
							法律/美学							</a>
							</div>
						</div>
					</li>
										<li>
						<a href="bookInfo?id=71" title="法律哲学" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526289904378.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=71" title="法律哲学" target="_blank" class="manga-name">
								法律哲学						</a>
							<div class="manga-author">
								<a href="serach?author=71" target="_blank" title="搜索作者:(德)阿图尔·考夫曼">
									(德)阿图尔·考夫曼							</a>
								<a href="serach?type=71" title="搜索类型:法律/哲学" target="_blank">
								法律/哲学					</a>
							</div>
						</div>
					</li>
									</ul>
			</div>
		</div>

<!--第三行结束  -->

<!--第四行开始  -->
	<div class="category-box boxshadow ">
			<div class="category-title">
				<h5 style="font-weight: normal;" class="title-font">哲学</h5>
								<a href="${pageContext.request.contextPath}/search?type=72" target="_blank" class="more-btn">更多</a>
			</div>
			<div class="category-manga">
				<ul>
										<li>
						<a href="bookInfo?id=72" title="哲学家" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526290162269.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=72" title="哲学家" target="_blank" class="manga-name">
								哲学家						</a>
							<div class="manga-author">
								<a href="search?author=72" target="_blank" title="搜索作者:冯俊">
									冯俊						</a>
								<a href="search?type=72" title="搜索类型:哲学" target="_blank">
									哲学					</a>
							</div>
						</div>
					</li>
										<li class="li-center">
						<a href="bookInfo?id=73" title="大众哲学" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526290253844.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=73" title="大众哲学" target="_blank" class="manga-name">
								大众哲学							</a>
							<div class="manga-author">
								<a href="search?author=73" target="_blank" title="搜索作者:艾思奇">
								艾思奇						</a>
								<a href="search?type=73" title="搜索类型:哲学/社会" target="_blank">
									哲学/社会							</a>
							</div>
						</div>
					</li>
										<li>
						<a href="bookInfo?id=74" title="黑格尔哲学" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526290374680.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=74" title="黑格尔哲学" target="_blank" class="manga-name">
								黑格尔哲学						</a>
							<div class="manga-author">
								<a href="search?author=74" target="_blank" title="搜索作者:刘永佶">
									刘永佶								</a>
								<a href="search?type=74" title="搜索类型:哲学/社会" target="_blank">
									哲学/社会						</a>
							</div>
						</div>
					</li>
									</ul>
			</div>
		</div>
		<!--中间架-->
			<div class="category-box boxshadow category-center">
			<div class="category-title">
				<h5 style="font-weight: normal;" class="title-font">地理</h5>
								<a href="${pageContext.request.contextPath}/search?type=78" target="_blank" class="more-btn">更多</a>
			</div>
			<div class="category-manga">
				<ul>
										<li>
						<a href="bookInfo?id=78" title="敦煌文书" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526388021130.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=78" title="敦煌文书" target="_blank" class="manga-name">
								敦煌文书						</a>
							<div class="manga-author">
								<a href="search?author=78" target="_blank" title="搜索作者:林聪明">
									林聪明								</a>
								<a href="search?type=78" title="搜索类型:地理/历史/文学" target="_blank">
									地理/历史/文学				</a>
							</div>
						</div>
					</li>
										<li class="li-center">
						<a href="bookInfo?id=79" title="唐人年寿研究" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526388174212.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=79" title="唐人年寿研究" target="_blank" class="manga-name">
								唐人年寿研究							</a>
							<div class="manga-author">
								<a href="search?author=79" target="_blank" title="搜索作者:李燕捷">
									李燕捷								</a>
								<a href="search?type=79" title="搜索类型:地理/历史/文学" target="_blank">
								   地理/历史/文学						</a>
							</div>
						</div>
					</li>
										<li>
						<a href="bookInfo?id=80" title="背包" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526388279023.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=80" title="背包" target="_blank" class="manga-name">
								背包						</a>
							<div class="manga-author">
								<a href="serach?author=80" target="_blank" title="搜索作者:金维一">
									金维一								</a>
								<a href="serach?type=80" title="搜索类型:地理/文学	" target="_blank">
									地理/文学								</a>
							</div>
						</div>
					</li>
									</ul>
			</div>
		</div>
		<!--右侧书架 -->
				<div class="category-box boxshadow ">
			<div class="category-title">
				<h5 style="font-weight: normal;" class="title-font">体育</h5>
								<a href="${pageContext.request.contextPath}/search?type=75" target="_blank" class="more-btn">更多</a>
			</div>
			<div class="category-manga">
				<ul>
										<li>
						<a href="bookInfo?id=75" title="体育魂" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526348872284.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=75" title="体育魂" target="_blank" class="manga-name">
								体育魂							</a>
							<div class="manga-author">
								<a href="serach?author=75" target="_blank" title="搜索作者:陈立基">
									陈立基								</a>
								<a href="serach?type=75" title="搜索类型:体育/社会" target="_blank">
									体育/社会							</a>
							</div>
						</div>
					</li>
										<li class="li-center">
						<a href="bookInfo?id=76" title="体育管理" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526387424425.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=76" title="体育管理" target="_blank" class="manga-name">
								体育管理						</a>
							<div class="manga-author">
								<a href="serach?author=76" target="_blank" title="搜索作者:高晓光">
									高晓光								</a>
								<a href="serach?type=76" title="搜索类型:体育/科学" target="_blank">
							体育/科学							</a>
							</div>
						</div>
					</li>
										<li>
						<a href="bookInfo?id=77" title="运动与营销" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526279165049.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=77" title="运动与营销" target="_blank" class="manga-name">
								运动与营销						</a>
							<div class="manga-author">
								<a href="serach?author=77" target="_blank" title="搜索作者:伞洪光">
									伞洪光							</a>
								<a href="serach?type=77" title="搜索类型:体育/经济" target="_blank">
								体育/经济						</a>
							</div>
						</div>
					</li>
									</ul>
			</div>
		</div>
<!--第四行结束  -->

<!--第五行开始  -->
	<div class="category-box boxshadow ">
			<div class="category-title">
				<h5 style="font-weight: normal;" class="title-font">社会</h5>
								<a href="${pageContext.request.contextPath}/search?type=81" target="_blank" class="more-btn">更多</a>
			</div>
			<div class="category-manga">
				<ul>
										<li>
						<a href="bookInfo?id=81" title="婆婆是家人" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526390189322.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=81" title="婆婆是家人" target="_blank" class="manga-name">
								婆婆是家人							</a>
							<div class="manga-author">
								<a href="search?author=81" target="_blank" title="搜索作者:黄越绥">
									黄越绥						</a>
								<a href="search?type=81" title="搜索类型:社会" target="_blank">
									社会					</a>
							</div>
						</div>
					</li>
										<li class="li-center">
						<a href="bookInfo?id=82" title="罗洪先集补编" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526390318839.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=82" title="罗洪先集补编" target="_blank" class="manga-name">
								罗洪先集补编							</a>
							<div class="manga-author">
								<a href="search?author=82" target="_blank" title="搜索作者:罗洪先">
								罗洪先						</a>
								<a href="search?type=82" title="搜索类型:社会/文学" target="_blank">
									社会/文学							</a>
							</div>
						</div>
					</li>
										<li>
						<a href="bookInfo?id=83" title="政治社会学" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526390417168.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=83" title="政治社会学" target="_blank" class="manga-name">
								政治社会学						</a>
							<div class="manga-author">
								<a href="search?author=83" target="_blank" title="搜索作者:李毅">
									李毅								</a>
								<a href="search?type=83" title="搜索类型:社会/政治" target="_blank">
									社会/政治							</a>
							</div>
						</div>
					</li>
									</ul>
			</div>
		</div>
		<!--中间架-->
			<div class="category-box boxshadow category-center">
			<div class="category-title">
				<h5 style="font-weight: normal;" class="title-font">医学</h5>
								<a href="${pageContext.request.contextPath}/search?type=86" target="_blank" class="more-btn">更多</a>
			</div>
			<div class="category-manga">
				<ul>
										<li>
						<a href="bookInfo?id=84" title="道医学" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526390545410.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=84" title="道医学" target="_blank" class="manga-name">
								道医学						</a>
							<div class="manga-author">
								<a href="search?author=84" target="_blank" title="搜索作者:熊春锦">
									熊春锦								</a>
								<a href="search?type=84" title="搜索类型:医学/宗教" target="_blank">
									医学/宗教					</a>
							</div>
						</div>
					</li>
										<li class="li-center">
						<a href="bookInfo?id=85" title="1+X医学" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526278727073.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=85" title="1+X医学" target="_blank" class="manga-name">
								1+X医学							</a>
							<div class="manga-author">
								<a href="search?author=85" target="_blank" title="搜索作者:张相玉">
									张相玉							</a>
								<a href="search?type=85" title="搜索类型:医学/科学/研究" target="_blank">
								   医学/科学/研究						</a>
							</div>
						</div>
					</li>
										<li>
						<a href="bookInfo?id=86" title="癫狂的医学" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526390740154.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=86" title="癫狂的医学" target="_blank" class="manga-name">
								癫狂的医学						</a>
							<div class="manga-author">
								<a href="serach?author=86" target="_blank" title="搜索作者:刘将">
									刘将								</a>
								<a href="serach?type=86" title="搜索类型:医学	" target="_blank">
									医学								</a>
							</div>
						</div>
					</li>
									</ul>
			</div>
		</div>
		<!--右侧书架 -->
				<div class="category-box boxshadow ">
			<div class="category-title">
				<h5 style="font-weight: normal;" class="title-font">宗教</h5>
								<a href="${pageContext.request.contextPath}/search?type=91" target="_blank" class="more-btn">更多</a>
			</div>
			<div class="category-manga">
				<ul>
										<li>
						<a href="bookInfo?id=88" title="禅与精神医学" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526391247320.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=88" title="禅与精神医学" target="_blank" class="manga-name">
								禅与精神医学							</a>
							<div class="manga-author">
								<a href="serach?author=88" target="_blank" title="搜索作者:(日)平井富雄">
									(日)平井富雄								</a>
								<a href="serach?type=88" title="搜索类型:宗教/医生" target="_blank">
									宗教/医生							</a>
							</div>
						</div>
					</li>
										<li class="li-center">
						<a href="bookInfo?id=90" title="道教医学" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526392031817.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=90" title="道教医学" target="_blank" class="manga-name">
								道教医学						</a>
							<div class="manga-author">
								<a href="serach?author=90" target="_blank" title="搜索作者:盖建民">
									盖建民								</a>
								<a href="serach?type=90" title="搜索类型:宗教/医学" target="_blank">
							宗教/医学							</a>
							</div>
						</div>
					</li>
										<li>
						<a href="bookInfo?id=91" title="生命的圣火" target="_blank" class="manga-img">
							<img src="http://localhost/lib/imgs/1526392130466.jpg">
							<span class="img-opcity"></span>
						</a>
						<div class="manga-desc">
							<a href="bookInfo?id=91" title="生命的圣火" target="_blank" class="manga-name">
								生命的圣火						</a>
							<div class="manga-author">
								<a href="serach?author=91" target="_blank" title="搜索作者:郑怀林/甘利仁">
									郑怀林/甘利仁							</a>
								<a href="serach?type=91" title="搜索类型:宗教" target="_blank">
								宗教						</a>
							</div>
						</div>
					</li>
									</ul>
			</div>
		</div>
<!--第五行结束  -->



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
	<script type="text/javascript" src="${pageContext.request.contextPath}/buka/js/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/buka/js/jquery.md5.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/buka/js/commonv20170602.js"></script>
				<script type="text/javascript" src="${pageContext.request.contextPath}/buka/js/template.js"></script>
				<script type="text/javascript">
	imgOnError('#manga-list,.category-box,.manga-panel','small');
</script>
<script type="text/javascript">
		imgOnError('.manga-img','small');
			</script>
</body>
</html>