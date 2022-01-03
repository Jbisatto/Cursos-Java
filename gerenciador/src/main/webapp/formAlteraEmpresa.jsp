<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:url value="/alteraEmpresa" var="enderecoURL" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastramento da Empresa</title>
</head>
<body>

	<form action="${enderecoURL}" method="post">
		<fmt:formatDate value="${empresa.dataAbertura}" pattern="dd/MM/yyyy"
				var="dataFormatada" />
		Nome:<input type="text" name="nome" value="${empresa.nome}" />

		Data Abertura:<input type="text" name="data" value="${dataFormatada}" />
		<input  type="hidden" name="id" value="${empresa.id}">
		 <input type="submit" />

	</form>

</body>
</html>