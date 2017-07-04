<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" isELIgnored="false" session="false" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>shiro</title>
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
  integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"
>
</head>
<body>
  <shiro:user>欢迎<shiro:principal />登录，<a href=${pageContext.request.contextPath}/logout>退出</a>
    <br />
  </shiro:user>
  <shiro:hasRole name="admin"> 用户[<shiro:principal />]拥有角色 admin<br />
  </shiro:hasRole>
  <shiro:hasRole name="test"> 用户[<shiro:principal />]拥有角色 test<br />
  </shiro:hasRole>
  <shiro:hasPermission name="user:*"> 用户[<shiro:principal />]拥有权限 user:*<br />
  </shiro:hasPermission>
  <shiro:hasPermission name="organization:*"> 用户[<shiro:principal />]拥有权限 organization:*<br />
  </shiro:hasPermission>
  
  <a href=></a>
  <button id="submit" type="submit" class="btn btn-default">Submit</button>
  
  <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
    integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"
  ></script>
  <script type="text/javascript">
			$("#submit").click(function() {
				$.ajax({
					type : "GET",
					url : "/shiro/test",
					dataType : "json",
					success : function(data) {
						console.log(data)
					},
					error : function(xhr, t, s) {
						if(xhr.status == 401){
							window.location.href = "/shiro/login"
						}
						console.log(xhr)
						console.log(xhr.status)
						console.log(s)
					}
				})
			})
		</script>
</body>
</html