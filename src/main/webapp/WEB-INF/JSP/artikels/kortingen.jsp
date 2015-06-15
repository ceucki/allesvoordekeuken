<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>
<!doctype html>
<html lang='nl'>
<v:head title='Kortingen' />
<body>
	<v:menu />
	<h1>Kortingen</h1>
	<form method="post">
		<c:forEach var="artikel" items="${artikels}">
			<li><c:url value="/artikels/kortingen.htm" var='kortingenURL'>
					<c:param name="id" value="${artikel.id}"></c:param>
				</c:url><a href="${kortingenURL}">${artikel.naam}</a></li>
		</c:forEach>
	</form>

	<c:if test="${not empty artikel}">
		<h2>Artikel</h2>
		<c:out value="${artikel.naam}"></c:out>
			<c:forEach var='korting' items='${artikel.kortingen}'>
			<li>Vanaf aantal: ${korting.vanafAantal }</li>
			<li>Kortingspercentage: <fmt:formatNumber value='${korting.kortingsPercentage}'/> %</c:forEach>
	</c:if>

</body>
</html>