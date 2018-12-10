<%@page import="br.com.assessmentsystem.assessmentsystem.controller.Routes"%>
<%

String exerciseId = (String) request.getSession().getAttribute("exerciseId");
String scriptletExerciseBody = "";

String title = (String) session.getAttribute("title");

if (title == null){
	title = "";
}

session.removeAttribute("title");

String resolutionParam = (String) request.getSession().getAttribute("resolutionParam");

if (resolutionParam == null){
	resolutionParam = "";
}

session.removeAttribute("resolutionParam");

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
scriptletExerciseBody += title;
scriptletExerciseBody +=" </div> ";
scriptletExerciseBody +=" <form action='"+Routes.exercisesAct+"' name='formExercises' method='post' onsubmit='putValue()'>";
scriptletExerciseBody +="	<div name='resolutionDiv' id='editor'>"+resolutionParam+"</div>";
scriptletExerciseBody +="			<p id ='buttonSubmit'>";
scriptletExerciseBody +="				<input type='hidden' name='resolution' id='resolution'>";
scriptletExerciseBody +="				<input type='hidden' name='exerciseId' value='"+exerciseId+"'>";
scriptletExerciseBody +="               <input type='submit' value='Enviar'/> ";
scriptletExerciseBody +="           </p> ";
scriptletExerciseBody +=" </form>";
scriptletExerciseBody +=" <br> ";      		
scriptletExerciseBody +=" <script> ";
scriptletExerciseBody +=" 	function putValue() { ";
scriptletExerciseBody +="  		var inputHidden = document.getElementById('resolution');";
scriptletExerciseBody +="		inputHidden.value = editor.getValue(); ";
scriptletExerciseBody +="		return 0; ";
scriptletExerciseBody +="	} ";                  
scriptletExerciseBody +=" </script> ";
scriptletExerciseBody +="		<script src='/resources/acebuilds/ace.js'></script>";
scriptletExerciseBody +="		<script src='/resources/acebuilds/ext-statusbar.js'></script>";
scriptletExerciseBody +=" <script>";
scriptletExerciseBody +=" var editor = ace.edit('editor');";
scriptletExerciseBody +=" editor.setTheme('ace/theme/eclipse');";
scriptletExerciseBody +=" editor.getSession().setMode('ace/mode/c_cpp'); ";
scriptletExerciseBody +=" </script>";


%>

<jsp:include page='/WEB-INF/views/main/template.jsp'>
<jsp:param name="templateBody" value="<%=scriptletExerciseBody%>"/>
</jsp:include>