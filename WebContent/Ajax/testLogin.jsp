<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="jquery-1.7.2.js	"></script>
        <script type="text/javascript">
        	$(function(){
        		$(":text[name='username']").change(function(){
        			var val=$(this).val();
        			val=$.trim(val);
        			if(val!=""){
        				/*发送Ajax请求！*/
        				var url="${pageContext.request.contextPath}/valiateUserName";
        				var args={username:val,time:new Date()};/*请求的数据放到json里  */
        				$.post(url,args,function(data){
        					$("#message").html(data);/* servlet中response.getWritter.print(xx) ,xx传给data*/
        				})
        			}
        			
        			
        		})
        		
        	})
        	
        </script>
        
    </head>
    
    <body>
    	<form action="" method="post">
    		<input type="text" name="username" />
    		<div id="message"></div>
    		<button type="submit"></button>
    	</form>
    	
 	</body>
</html>