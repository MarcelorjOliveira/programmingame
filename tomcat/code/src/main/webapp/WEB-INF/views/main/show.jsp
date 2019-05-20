<%

String timeline = "Bem vindo ao programmingGame . Onde você aprende programação brincando.";

%>

<jsp:include page='/WEB-INF/views/main/template.jsp'>
<jsp:param name="templateBody" value="<%=timeline%>"/>
</jsp:include>