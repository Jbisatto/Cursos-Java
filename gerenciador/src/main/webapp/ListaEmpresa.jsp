<%@page
	import="java.util.List,br.com.alura.gerenciador.servelet.Empresa"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de Empresas Cadastradas</title>
</head>
<body>
	<c:if test="${not empty empresa }">
	Empresa ${empresa} cadastrada com sucesso</c:if>
	<c:if test="${not empty empresa2 }">
	Empresa ${empresa2} alterada com sucesso</c:if>
	<br /> Lista de Empresas Cadastradas:
	<br>
	<ul>

		<c:forEach items="${empresas}" var="empresa">
			<fmt:formatDate value="${empresa.dataAbertura }" pattern="dd/MM/yyyy"
				var="dataFormatada" />
			<li>
			${empresa.nome}- ${dataFormatada}
			<a href="/gerenciador/mostraEmpresa?id=${empresa.id}" >editar</a>
			<a href="/gerenciador/removeEmpresa?id=${empresa.id}" >remove</a>
			
			</li>
		</c:forEach>

	</ul>

</body>
</html>