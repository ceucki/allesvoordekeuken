<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>
<!doctype html>
<html lang='nl'>
<v:head title='Alles Voor De Keuken' />
<body>
	<v:menu />
	<h1>Artikel Zoeken</h1>
	<form>
		<label>Nummer:<span>${fouten.id}</span> <input name='id'
			value='${param.id}' required autofocus type='number' min='1'></label>
		<input type='submit' value='Zoeken'>
	</form>
	<c:if test='${not empty param and empty fouten and empty artikel}'>
Artikel niet gevonden
</c:if>
	<c:if test='${not empty artikel}'>
		<li>naam: ${artikel.naam}</li>
		<li>aankoopprijs: ${artikel.aankoopprijs}</li>
		<li>verkoopprijs: ${artikel.verkoopprijs}</li>
		<li>%winst: ${artikel.winstPercentage}</li>
	</c:if>
</body>
</html>