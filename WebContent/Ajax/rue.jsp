<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="jquery-1.7.2.js	"></script>
		<script type="text/javascript">
			$(function(){
				$("a:eq(0)").click(function(){
					/*使用load*/
					var url=this.href+" img"/* 只能拿文字了 */
					/*图片可以拿过来  */
					var args={time:new Date()};
                    alert(url);
					$("#box").load(url,args);
					return false;
				})
				
				$("a:eq(1)").click(function(){
					alert(1);
					var url=this.href;
					alert(url);
					var args={time:new Date()};
					$.get(url,args,function(data){/* 只能xml的 */
						alert($(data));
						var name=$(data).find("name").text();/* xml的 */
						alert(name);
						$("#box").append("<span>"+name+"</span>");
						alert(1);
						
					});
				  
					return false;
				})
				
				
				$("a:eq(2)").click(function(){
					$.get
					
					
				})
			})
		</script>
</head>
<body>
<a href="note.jsp">click me</a>
<a href="note.xml">click me</a>
<a href="note.json">click me</a>
<div id="box"></div>

</body>
</html>