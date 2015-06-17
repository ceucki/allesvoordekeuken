<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>
<!doctype html>
<html lang='nl'>
<v:head title='Artikel Lijst' />
<body>

<c:forEach var='artikel' items="${ artikelsMetGroep}">
<li>${artikel.naam} (${artikel.artikelGroep.naam})</li>
</c:forEach>

</body>
</html>