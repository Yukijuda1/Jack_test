<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
         
    <script type="text/javascript" src="jquery-1.7.2.js	"></script>
    <script type="text/javascript">
    	$(function(){
    	alert($("#message").text());
    	})
    	
    </script>
    <body>
    	<a href="index.html">click me</a>
    	<div id="message">22</div>
 	</body>
</html>