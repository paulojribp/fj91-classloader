<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags"%>

<custom:template title="Usuários">
	<jsp:attribute name="extraStyles"></jsp:attribute>
	<jsp:attribute name="extraScripts"></jsp:attribute>

	<jsp:body>
		<form method="post" action="<c:url value='/usuarios' />">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			
			<div class="form-group">
				<label for="nome">Nome</label>
				<input id="nome" name="nome" class="form-control" required="required">
			</div>
			
			<div class="form-group">
				<label for="login">Login</label>
				<input id="login" name="login" class="form-control" required="required">
			</div>
			
			<div class="form-group">
				<label for="senha">Senha</label>
				<input id="senha" name="senha" type="password" class="form-control" required="required">
			</div>
			
			<input type="submit" value="Adicionar" class="btn btn-primary">
		</form>
		
		<div class="table-responsive">
			<table class="table table-stripped table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>NOME</th>
						<th>LOGIN</th>
						<th>STATUS</th>
						<th>REMOVER</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${usuarios}" var="usuario">
						<tr>
							<td>
								<c:out value="${usuario.id}" />
							</td>
							
							<td>
								<c:out value="${usuario.nome}" />
							</td>
							
							<td>
								<c:out value="${usuario.login}" />
							</td>
							
							<td>
								<c:set var="logado" value="${usuariosLogados.usuarioEstaLogado(usuario.id)}"></c:set>
								
								<c:if test="${logado}">
									<img src="<c:url value='/assets/img/online.png' />" alt="Online" title="Online">
								</c:if>
								
								<c:if test="${not logado}">
									<img src="<c:url value='/assets/img/offline.png' />" alt="Offline" title="Offline">
								</c:if>
							</td>
							
							<td>
								<form method="post" action="<c:url value='/usuarios/${usuario.id}' />">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<input type="hidden" name="_method" value="DELETE">
									<input type="hidden" name="id" value="${usuario.id}">
									
									<button type="submit" class="btn btn-danger">
										<span class="glyphicon glyphicon-trash"></span> Remover
									</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</jsp:body>
</custom:template>
