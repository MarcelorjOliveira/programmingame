<%@page import="br.com.assessmentsystem.assessmentsystem.controller.Routes"%>
<%
String scriptletExerciseBody = "";

if (request.getParameter("mark") != null) { 
	scriptletExerciseBody +="<script>alert('Nota : "+request.getParameter("mark")+"');";
	scriptletExerciseBody +=" var solutions = "+request.getParameter("corrections")+"; ";
	scriptletExerciseBody +=" for(var c=0; c < solutions.descriptions.length; c++){ ";
	scriptletExerciseBody +=" var description = solutions.descriptions[c]; "; 			
	scriptletExerciseBody +=" if(description){ ";
	scriptletExerciseBody +=" alert(description); ";			
	scriptletExerciseBody +=" } ";			
	scriptletExerciseBody +=" } ";
	scriptletExerciseBody +=" </script> ";
}

scriptletExerciseBody +="  	<style type='text/css' media='screen'> ";
scriptletExerciseBody +="	#editor { "; 
scriptletExerciseBody +="		position: relative; ";
scriptletExerciseBody +="		right: 0; ";   
scriptletExerciseBody +="		bottom: 0; ";
scriptletExerciseBody +="		left: 0; ";
scriptletExerciseBody +="		width: 100%; ";
scriptletExerciseBody +="		height: 30em; ";
scriptletExerciseBody +="	} ";
scriptletExerciseBody +="   </style> ";
    
scriptletExerciseBody +=" <div class='col-sm-auto'>";
scriptletExerciseBody += request.getParameter("title"); 
scriptletExerciseBody +=" </div> ";
scriptletExerciseBody +=" <form action='"+Routes.basicExercisesAct+"' name='formBasicExercises' method='post' onsubmit='putValue()'>";
scriptletExerciseBody +="	<div name='resolution' id='editor'>"+request.getParameter("resolutionParam")+"</div>";
scriptletExerciseBody +="		<script src='/resources/acebuilds/ace.js'></script>";
scriptletExerciseBody +="		<script src='/resources/acebuilds/ext-statusbar.js'></script>";
scriptletExerciseBody +="			<p id ='buttonSubmit'>";
scriptletExerciseBody +="				<input type='hidden' name'resolution' id='resolution'>";
scriptletExerciseBody +="               <input type='submit' value='Enviar'/> ";
scriptletExerciseBody +="           </p> ";
scriptletExerciseBody +="      		</form>";
scriptletExerciseBody +=" <br> ";      		
scriptletExerciseBody +=" <script> ";
scriptletExerciseBody +=" 	function putValue() { ";
scriptletExerciseBody +="  		var inputHidden = document.getElementById('resolution');";
scriptletExerciseBody +="		inputHidden.value = editor.getValue(); ";
scriptletExerciseBody +="		return 0; ";
scriptletExerciseBody +="	} ";                  
scriptletExerciseBody +=" </script> ";   

%>

<jsp:include page='/WEB-INF/views/main/template.jsp'>
<jsp:param name="body" value="<%=scriptletExerciseBody%>"/>
</jsp:include>
haha