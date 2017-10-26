<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<custom:template title="Home">
	<jsp:attribute name="extraStyles"></jsp:attribute>
	<jsp:attribute name="extraScripts"></jsp:attribute>

	<jsp:body>
		<c:if test="${param.error ne null}">
			<div class="alert alert-danger">
				<p>Dados inválidos!</p>
			</div>
		</c:if>
	
		<h1 class="text-center">Login</h1>
		
		<form method="post" action="<c:url value='/login' />">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			
			<div class="form-group">
				<label for="login">Login</label>
				<input id="login" name="username" class="form-control" required="required">
			</div>
			
			<div class="form-group">
				<label for="senha">Senha</label>
				<input id="senha" name="password" type="password" class="form-control" required="required">
			</div>
			
			<input type="submit" value="Logar" class="btn btn-primary">
		</form>
	</jsp:body>
</custom:template>
