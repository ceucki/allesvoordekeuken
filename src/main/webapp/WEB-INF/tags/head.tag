<%@ tag description='head onderdeel van pagina' pageEncoding='UTF-8'%>
<%@ attribute name='title' required='true' type='java.lang.String'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<title>${title}</title>
<link rel='shortcut icon' href='<c:url value="allesvoordekeuken2/src/main/webapp/images/favicon.ico"/>' type='image/x-icon'/>
<meta name='viewport' content='width=device-width,initial-scale=1'>
<link rel='stylesheet' href='<c:url value="/styles/default.css"/>'>
