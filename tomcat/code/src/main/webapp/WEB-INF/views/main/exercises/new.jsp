<%@page import="br.com.assessmentsystem.assessmentsystem.controller.Routes"%>
<%

String exerciseId = (String) request.getSession().getAttribute("exerciseId");
String exerciseBody = "";

String title = (String) session.getAttribute("title");

if (title == null){
	title = "";
}

session.removeAttribute("title");

String exerciseName = (String) session.getAttribute("exerciseName");

String resolutionParam = (String) request.getSession().getAttribute("resolutionParam");

if (resolutionParam == null){
	resolutionParam = "";
}

session.removeAttribute("resolutionParam");

if (request.getParameter("mark") != null) { 
	exerciseBody +="<script>alert('Nota : "+request.getParameter("mark")+"');";
	exerciseBody +=" var solutions = "+request.getParameter("corrections")+"; ";
	exerciseBody +=" for(var c=0; c < solutions.descriptions.length; c++){ ";
	exerciseBody +=" var description = solutions.descriptions[c]; "; 			
	exerciseBody +=" if(description){ ";
	exerciseBody +=" alert(description); ";			
	exerciseBody +=" } ";			
	exerciseBody +=" } ";
	exerciseBody +=" </script> ";
}

exerciseBody +="  	<style type='text/css' media='screen'> ";
exerciseBody +="	#editor { "; 
exerciseBody +="		position: relative; ";
exerciseBody +="		right: 0; ";   
exerciseBody +="		bottom: 0; ";
exerciseBody +="		left: 0; ";
exerciseBody +="		width: 100%; ";
exerciseBody +="		height: 30em; ";
exerciseBody +="	} ";
exerciseBody +="   </style> ";
    
exerciseBody +=" <div class='d-flex col-sm-auto' width='100%'>";
exerciseBody += title;
exerciseBody +=" </div> ";

String useDirectoryTree = (String) request.getSession().getAttribute("useDirectoryTree");

if (useDirectoryTree.equals("1") ) {

	exerciseBody += "<link rel='stylesheet' href='/resources/vendor/jstree/dist/themes/default/style.min.css' />";
	exerciseBody += "<div id='divTree'>";
	//exerciseBody += "<ul>";
	//exerciseBody += "<li>root</li>";
	//exerciseBody += "</ul>";
	exerciseBody += "</div>";
	exerciseBody += "<div id='create'>";
	exerciseBody += "<a data-toggle='modal' href='#directoryModal'><span class ='ti-plus'></span></a>";
	exerciseBody += "</div>";
	exerciseBody += "<div id='fileNameTemplate'>";
	exerciseBody += "</div>";
	exerciseBody += "<div class='modal fade' id='directoryModal' role='dialog'>";
	exerciseBody += "<div class='modal-dialog'>";
	exerciseBody += "  <!-- Modal content-->";
	exerciseBody += "  <div class='modal-content'>";
	exerciseBody += "   <div class='modal-header'>";
	exerciseBody += "   Create Directory";
	exerciseBody += "      <button type='button' class='close' data-dismiss='modal'>&times;</button>";
	exerciseBody += "    </div>";
	exerciseBody += "    <div class='modal-body'>";
	exerciseBody += "<form role='form' name='createDirectory' >";
	exerciseBody += "<div class='form-group'>";
	exerciseBody += "              <input type='text' class='form-control' name='directory' id='directory' placeholder='E.g. /src/home or /src/user/cad.java'>";
	exerciseBody += "            <button type='button' onclick='directoryAjax(\""+Routes.exercisesCreateDirectory+"\")' class='btn btn-default btn-success btn-block'><span class='glyphicon glyphicon-off'></span>Create</button>";
	exerciseBody += "            </div>";
	exerciseBody += "          </form>";
	exerciseBody += "        </div>";
	exerciseBody += "      </div>";
	exerciseBody += "  </div>";
	exerciseBody += "</div>";

}

exerciseBody +=" <form action='"+Routes.exercisesAct+"' name='formExercises' method='post' onsubmit='putValue()'>";
exerciseBody +="	<div name='resolutionDiv' id='editor'>"+resolutionParam+"</div>";
exerciseBody +="			<p id ='buttonSubmit'>";
exerciseBody +="				<input type='hidden' name='resolution' id='resolution'>";
exerciseBody +="				<input type='hidden' name='exerciseId' value='"+exerciseId+"'>";
exerciseBody +="               <input type='submit' value='Enviar'/> ";
exerciseBody +="           </p> ";
exerciseBody +=" </form>";
exerciseBody +=" <br> ";      		
exerciseBody +=" <script> ";
exerciseBody +=" 	function putValue() { ";
exerciseBody +="  		var inputHidden = document.getElementById('resolution');";
exerciseBody +="		inputHidden.value = editor.getValue(); ";
exerciseBody +="		return 0; ";
exerciseBody +="	} ";                  
exerciseBody +=" </script> ";
exerciseBody +="		<script src='/resources/acebuildsMinNoconflict/ace.js'></script>";
exerciseBody +="		<script src='/resources/acebuildsMinNoconflict/ext-statusbar.js'></script>";
//exerciseBody +=" <script>";
//exerciseBody +=" var editor = ace.edit('editor');";
//exerciseBody +=" editor.setTheme('ace/theme/eclipse');";
//exerciseBody +=" editor.getSession().setMode('ace/mode/java'); ";
//exerciseBody +=" sessionStorage.setItem('aceEditor', editor); ";
//exerciseBody +=" </script>";

%>

<jsp:include page='/WEB-INF/views/main/template.jsp'>
<jsp:param name="templateBody" value="<%=exerciseBody%>"/>
</jsp:include>
