<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>
<!doctype html>
<html lang='nl'>
<v:head title='Alles Voor De Keuken' />
<body>
	<v:menu />
	<h1>Artikel per artikelgroep</h1>
	<form>
		<c:forEach var="artikelgroep" items="${artikelgroepen}">
			<li><c:url value="/artikels/perartikelgroep.htm"
					var="artikelgroepURL">
					<c:param name="id" value="${artikelgroep.id}" />
				</c:url><a href="${artikelgroepURL }">${artikelgroep.naam}</a></li>

		</c:forEach>

		<c:if test="${not empty artikelgroep}">
			<h2>${artikelgroep.naam}</h2>
			<table>
				<thead>
					<tr>
						<th>Nummer</th>
						<th>Naam</th>
						<th>Verkoopprijs</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${artikelgroep.artikelen}" var="artikel">
						<tr>
							<td>${artikel.id}</td>
							<td>${artikel.naam}</td>
							<td><fmt:formatNumber value="${artikel.verkoopprijs}"
									minFractionDigits="2" maxFractionDigits="2" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</form>
</body>
</html>