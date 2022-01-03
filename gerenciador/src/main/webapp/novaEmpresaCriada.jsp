<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<body>

	<c:if test="${not empty empresa }">
	Empresa ${empresa} cadastrada com sucesso</c:if>
	<br /> Lista de Empresas Cadastradas:
	<br>
	<ul>

		<c:forEach items="${empresas}" var="empresa">
			<fmt:formatDate value="${empresa.dataAbertura }" pattern="dd/MM/yyyy"
				var="dataFormatada" />
			<li>${empresa.nome}-${dataFormatada}</li>
		</c:forEach>

	</ul>
</body>
</html>