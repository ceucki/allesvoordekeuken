<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>
<!doctype html>
<html lang='nl'>
<v:head title='Alles Voor De Keuken' />
<body>
	<v:menu />
	<h1>Prijs verhoging</h1>
	<form method="post">
		<label>Tik een percentage in:<span>${fouten.percentage}</span> <input name='percentage'
			value='${param.id}' required autofocus type='number' min='0' step='0.01'></label>
		<input type='submit' value='Voer Uit'>
	</form>
</body>
</html>