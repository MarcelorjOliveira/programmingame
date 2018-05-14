<%@page import="br.com.assessmentsystem.assessmentsystem.controller.Routes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML>
<!--
	Prologue by HTML5 UP
	html5up.net | @n33co
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<!DOCTYPE html>
<html><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <% if (request.getParameter("mark") != null) { %>
		<script>alert('Nota : <%=request.getParameter("mark")%>');</script>
	<% } %>
	<link rel="icon" type="image/jpg" href="/resources/images/icone.jpg"/>

    <title>Basic Exercises</title>
    
   	<style type="text/css" media="screen">
                    #editor { 
                        position: relative;
                        right: 0;   
                        bottom: 0;
                        left: 0;
                        width: 100%;
                        height: 30em;
                    }
    </style>

    <!-- Bootstrap core CSS -->
    <link href="/resources/js/dashboard/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/resources/js/dashboard/dashboard.css" rel="stylesheet">
  <style type="text/css">/* Chart.js */
@-webkit-keyframes chartjs-render-animation{from{opacity:0.99}to{opacity:1}}@keyframes chartjs-render-animation{from{opacity:0.99}to{opacity:1}}.chartjs-render-monitor{-webkit-animation:chartjs-render-animation 0.001s;animation:chartjs-render-animation 0.001s;}</style></head>

  <body>
    <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Programming Game</a>
      <input class="form-control form-control-dark w-100" placeholder="Search" aria-label="Search" type="text">
      <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
          <a class="nav-link" href="<%=Routes.logout%>">Deslogar</a>
        </li>
      </ul>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
          <div class="sidebar-sticky">
            <ul class="nav flex-column">
              <li class="nav-item">
                <a class="nav-link active" href="#">
                  <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-home"><path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path><polyline points="9 22 9 12 15 12 15 22"></polyline></svg>
                  Dashboard <span class="sr-only">(current)</span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="<%= Routes.basicExercises %>">
                  <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-file"><path d="M13 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V9z"></path><polyline points="13 2 13 9 20 9"></polyline></svg>
                 	Exercícios básicos com linguagem C 
                </a>
              </li>
               </ul>    
              </tbody>
            </table>
          </div>
        </main>
      </div>
    </div>
    <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
   			<div class="col-sm-auto">
   				${title}
   			</div>
           <form action="<%= Routes.basicExercisesAct%>" name="formBasicExercises" method="post" onsubmit="putValue()">
                <div name="resolution" id="editor">${resolutionParam}</div>
                <script src="/resources/acebuilds/ace.js"></script>
    			<script src="/resources/acebuilds/ext-statusbar.js"></script>
                <p id ="buttonSubmit">
                        <input type="hidden" name="resolution" id="resolution">
                        <input type="submit" value="Enviar"/>
                </p>
   </main>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="/resources/js/dashboard/jquery-3.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
  	<script>
                    function putValue() {
                        var inputHidden = document.getElementById("resolution");
                        inputHidden.value = editor.getValue();
                        return 0;
                    }
    </script>  
    <script>
    	var editor = ace.edit("editor");
        editor.setTheme("ace/theme/eclipse");
        editor.getSession().setMode("ace/mode/c_cpp");
	</script>
    <script src="/resources/js/dashboard/popper.js"></script>
    <script src="/resources/js/dashboard/bootstrap.js"></script>

    <!-- Icons -->
    <script src="/resources/js/dashboard/feather.js"></script>
    <script>
      feather.replace()
    </script>  

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="/resources/js/dashboard/jquery-3.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="/resources/js/dashboard/popper.js"></script>
    <script src="/resources/js/dashboard/bootstrap.js"></script>

    <!-- Icons -->
    <script src="/resources/js/dashboard/feather.js"></script>
    <script>
      feather.replace()
    </script>
    
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="/resources/js/dashboard/jquery-3.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="/resources/js/dashboard/popper.js"></script>
    <script src="/resources/js/dashboard/bootstrap.js"></script>

    <!-- Icons -->
    <script src="/resources/js/dashboard/feather.js"></script>
    <script>
      feather.replace()
    </script>


</body></html>					
