<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<spring:url var="images" value="/resources/images" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="resources/css/mycss.css">
<script src="resources/js/myjs.js">
	
</script>

<title>Blush Boutique - ${title}</title>
<style>
html {
	height: 100%;
}

body {
	padding-top: 70px;
	height: 100%;
}

.carousel-inner img {
	width: 100%;
	height: 100%;
}

.wrapper {
	min-height: 100%;
	position: relative;
}

.content {
	padding-bottom: 100px; /*height of the footer*/
}

.navbar-custom {
	background-color: #EE897F;
	color: #ffffff;
	border-radius: 0;
	min-height: 50px
}

.navbar-custom .navbar-nav>li>a {
	color: #fff;
	padding-left: 20px;
	padding-right: 20px;
}

.navbar-custom .navbar-nav>.active>a, .navbar-nav>.active>a:hover,
	.navbar-nav>.active>a:focus {
	color: #ffffff;
	background-color: transparent;
}

.navbar-custom .navbar-nav>li>a:hover, .nav>li>a:focus {
	text-decoration: none;
	background-color: #D11141;
}

.navbar-custom .navbar-brand {
	padding-top: 0;
	color: #eeeeee;
}

.navbar-custom .navbar-toggle {
	background-color: #eeeeee;
}

.carousel-caption {
	color: #D11141;
}

.footer {
	position: absolute;
	left: 0;
	bottom: 0;
	width: 100%;
	background-color: #EE897F;
	color: white;
	text-align: center;
}
</style>
</head>


<nav class="navbar navbar-inverse navbar-custom navbar-fixed-top">
<div class="container-fluid">
	<button type="button" class="navbar-toggle collapsed"
		data-toggle="collapse" data-target="#collapse-example"
		aria-expanded="false">
		<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
		<span class="icon-bar"></span> <span class="icon-bar"></span>
	</button>
	<div class="navbar-header">
		<span class="navbar-brand"><img src="${images}/logo.jpg"
			class="img-fluid" height="50px" width="80px"></span>
	</div>
	<div class="collapse navbar-collapse" id="collapse-example">
		<ul class="nav navbar-nav">
			<li><a href="${contextRoot}/home">Home</a></li>
			<li><a href="${contextRoot}/about">About</a></li>
			<li><a href="${contextRoot}/contact">Contact</a></li>
			<security:authorize access="hasRole('ROLE_ADMIN')">
			<li><a href="${contextRoot}/ADMIN/Category">Category</a></li>
			<li><a href="${contextRoot}/ADMIN/products">Manage Products</a></li>
			</security:authorize>
			<li><a href="${contextRoot}/productgrid">Products</a></li>

			<li class="dropdown"><a href="" class="dropdown-toggle"
				data-toggle="dropdown">Categories<span class="caret"></span></a>
				<ul class="dropdown-menu">
					<c:forEach items ="catlist" var= "c">
					<li><a href="">$(c.categoryName)</a></li>
					</c:forEach>
					
				</ul></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
		<c:if test="${pageContext.request.userPrincipal.name==null}">
			<li><a href="${contextRoot}/login"><span
					class="glyphicon glyphicon-log-in"></span> Sign In</a></li>
					</c:if>
					<c:if test="${pageContext.request.userPrincipal.name!=null}">
					<li><a>Welcome${pageContext.request.userPrincipal.name}</a></li>
					</c:if>
		</ul>
	</div>
</div>
</nav>
</html>
