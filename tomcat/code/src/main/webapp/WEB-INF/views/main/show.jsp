<%

String timeline = "Bem vindo ao programmingGame . Onde voc� aprende programa��o brincando.";

%>

<jsp:include page='/WEB-INF/views/main/template.jsp'>
<jsp:param name="templateBody" value="<%=timeline%>"/>
</jsp:include>