<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<custom:template title="Home">
	<jsp:attribute name="extraStyles"></jsp:attribute>
	<jsp:attribute name="extraScripts"></jsp:attribute>

	<jsp:body>
		<div class="jumbotron">
			<h1 class="text-center">FJ-91</h1>
			<p>Projeto com problemas relacionados a Class Loader</p>
		</div>
	</jsp:body>
</custom:template>
