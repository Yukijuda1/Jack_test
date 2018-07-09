<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${requestScope.bookInfo.name} - 第${requestScope.nowNo}卷</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/reset.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/content.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/buka/css/read.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/buka/js/jquery-1.7.2.js"></script>
	<style type="text/css">
		.manga-c img{width:750px;}
        .retry{width:50%;margin:0 auto;color:#fff;text-align: center;font-size:1.5em;margin-top:300px;}
        .retry .retry-btn{display:inline-block;color:#fff !important;width:150px;padding:10px 0;border-radius: 30px;margin:150px 10px 0 10px;background:#F4B440;}
	</style>
	<script type="text/javascript">

                    function mbar(sobj) {
                    var docurl =sobj.options[sobj.selectedIndex].value;
                    if (docurl != "") {
                       open(docurl,'_blank');
                       sobj.selectedIndex=0;
                       sobj.blur();
                    }
                    }
 </script>
</head>
	<div class="head-top">
		<div class="head-nav" id="head-nav">
			<div class="head-nav-wrap">
				<!--logo位置，已删-->
				<div class="btns-wrap-out">
				<div class="manga-names">
					<a href="bookInfo?id=${requestScope.bookInfo.id}" class="manga-name">${requestScope.bookInfo.name}</a>
					<div class="manga-episodes">
						<select name="episode" id="gotoEpisode" onchange=mbar(this) name="select">
							        <option value="" disabled="" selected="">章节:</option>
							    <!--select添加了onchange，改变option时跳转至value页面-->
							    <!--循环-->
							    <c:forEach items="${requestScope.contentList }" var="content">
                            <option title="" value="content?id=${requestScope.bookInfo.id}&no=${content.no}">
                                        &nbsp;&nbsp;第${content.no}卷</option>
                                        </c:forEach>
                                  <!--循环-->      
						</select>
					</div>
				</div>
				<div class="right-btns">
					<div class="manga-btns-1">
					<c:choose>
					<c:when test="${requestScope.nowNo eq 1 }">
					<a title="${requestScope.bookInfo.name}:" class="btn-disabled">上一话</a>
					</c:when>
					<c:otherwise>
						<a title="${requestScope.bookInfo.name}:第${requestScope.nowNo-1 }卷" href="content?id=${requestScope.bookInfo.id }&no=${requestScope.nowNo-1}">上一话</a>
						</c:otherwise>
						</c:choose>
						
						<c:choose>
						<c:when test="${requestScope.nowNo eq requestScope.contentSize}">
						<a title="${requestScope.bookInfo.name}:" class="btn-disabled">下一话</a>
						</c:when>
						<c:otherwise>
						<a title="${requestScope.bookInfo.name}:第${requestScope.nowNo+1 }卷" href="content?id=${requestScope.bookInfo.id }&no=${requestScope.nowNo+1}">下一话</a>
					</c:otherwise>
					</c:choose>
					</div>
					<div class="manga-btns-2">
						<a href="${pageContext.request.contextPath}/index.jsp">首页</a>
						<a href="bookInfo?id=${requestScope.bookInfo.id}">目录</a>
					</div>
				</div>
			</div>
			</div>
		</div>
	</div>
	<div class="manga-imgs" id="manga-imgs">
		<div class="manga-num" id="p1" style="height:0px;margin-bottom:-4px;">
								</div>
		
		<div class="manga-c"  style="letter-spacing:6px;line-height:56px">
<!--content内容-->
${requestScope.mainContent }
			<div class="btn-wrap">
				<a href="javascript:;" class="preP prePos_l"></a>
				<a href="javascript:;" class="nextP prePos_r"></a>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/buka/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/buka/js/jquery.lazyload.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/buka/js/commonv20170602.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/buka/js/read-manga.js"></script>
</body>
</html>