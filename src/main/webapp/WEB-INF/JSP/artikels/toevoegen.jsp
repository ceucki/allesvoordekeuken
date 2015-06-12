<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>
<!doctype html>
<html lang='nl'>
<v:head title='Alles Voor De Keuken' />
<body>
	<v:menu />
	<h1>Artikel toevoegen</h1>
	<form method="post" id="toevoegform">
		<label>Naam: <span>${fouten.naam}</span> <input name="naam"
			value="${param.naam}" autofocus required></label> <label>Aankoopprijs:
			<span>${fouten.aankoopprijs}</span> <input name="aankoopprijs"
			value="${param.aankoopprijs}" autofocus required type="number"
			min='0' step='0.01'>
		</label> <label>Verkoopprijs: <span>${fouten.verkoopprijs}${fouten.verkoopprijsKleinerDanAankoopprijs}</span>
			<input name="verkoopprijs" value="${param.verkoopprijs}" autofocus
			required type="number" min='0' step='0.01'></label> <label><input
			type='radio' name="food" /> Food</label><br /> <label>Houdbaarheid:
			<span>${fouten.houdbaarheid }</span> <input name="houdbaarheid"
			value="${param.houdbaarheid}" />
		</label> <label><input type='radio' name="nonFood" /> Non-Food</label><br />
		<label>Garantie: <span>${fouten.garantie}</span><input
			name="garantie" value="${param.garantie}" />
		</label> <input type='submit' value='Toevoegen' id='toevoegknop'>
	</form>
	<script>
		document.getElementById('toevoegform').onsubmit = function() {
			document.getElementById('toevoegknop').disabled = true;
		};

		document.getElementById('food').onclick = enableDisableInputs;
		document.getElementById('nonfood').onclick = enableDisableInputs;
		enableDisableInputs();
		function enableDisableInputs() {
			document.getElementById('houdbaarheid').disabled = !document
					.getElementById('food').checked;
			document.getElementById('garantie').disabled = !document
					.getElementById('nonfood').checked;
		}
	</script>
</body>
</html>