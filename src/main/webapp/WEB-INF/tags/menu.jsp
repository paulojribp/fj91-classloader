<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" 
				data-target="#menu" aria-expanded="false">
				<span class="sr-only">Menu</span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/">Class Loader</a>
		</div>

		<div class="collapse navbar-collapse" id="menu">
			<ul class="nav navbar-nav">
				<sec:authorize access="!isAuthenticated()">
					<li>
						<a href="<c:url value='/login' />">Login</a>
					</li>
				</sec:authorize>
				
				<sec:authorize access="isAuthenticated()">
					<li>
						<a href="<c:url value='/usuarios' />">Usuários</a>
					</li>
				</sec:authorize>
			</ul>
			
			<sec:authorize access="isAuthenticated()">
				<ul class="nav navbar-nav navbar-right">
					<li>
						<form method="post" action="<c:url value='/logout' />">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							
							<button class="btn-link btn-logout">Logout</button>
						</form>
					</li>
				</ul>
			</sec:authorize>
		</div>
	</div>
</nav>
