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
<html>
	<head>
		<title>Exercícios Básicos</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		
		<% if (request.getParameter("mark") != null) { %>
		<script>alert('Nota : <%=request.getParameter("mark")%>');</script>
		<% } %>
		
		<!--[if lte IE 8]><script src="/resources/css/ie/html5shiv.js"></script><![endif]-->
		<script src="/resources/js/jquery.min.js"></script>
		<script src="/resources/js/jquery.scrolly.min.js"></script>
		<script src="/resources/js/jquery.scrollzer.min.js"></script>
		<script src="/resources/js/skel.min.js"></script>
		<script src="/resources/js/skel-layers.min.js"></script>
		<script src="/resources/js/init.js"></script>
                <script>
                    function putValue() {
                        var inputHidden = document.getElementById("resolution");
                        inputHidden.value = editor.getValue();
                        return 0;
                    }
                </script>
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
		<noscript>
			<link rel="stylesheet" href="/resources/css/skel.css" />
			<link rel="stylesheet" href="/resources/css/style.css" />
			<link rel="stylesheet" href="/resources/css/style-wide.css" />
		</noscript>
		<!--[if lte IE 9]><link rel="stylesheet" href="/resources/css/ie/v9.css" /><![endif]-->
		<!--[if lte IE 8]><link rel="stylesheet" href="/resources/css/ie/v8.css" /><![endif]-->
	</head>
	<body>

		<!-- Header -->
			<div id="header" class="skel-layers-fixed">

				<div class="top">

					<!-- Logo -->
						<div id="logo">
							<h1> HALYENL </h1>
    						</div>

					<!-- Nav -->
						<nav id="nav">
							<!--
							
								Prologue's nav expects links in one of two formats:
								
								1. Hash link (scrolls to a different section within the page)
								
								   <li><a href="#foobar" id="foobar-link" class="icon fa-whatever-icon-you-want skel-layers-ignoreHref"><span class="label">Foobar</span></a></li>

								2. Standard link (sends the user to another page/site)

								   <li><a href="http://foobar.tld" id="foobar-link" class="icon fa-whatever-icon-you-want"><span class="label">Foobar</span></a></li>
							
							-->
							<ul>
								<li><a href="<%= Routes.main %>" id="top-link" class="skel-layers-ignoreHref"><span class="icon fa-home">Home</span></a></li>
								<li><a href="<%= Routes.basicExercises %>" id="portfolio-link" class="skel-layers-ignoreHref"><span class="icon fa-th">Basic Exercises</span></a></li>
								<li><a href="" id="contact-link" class="skel-layers-ignoreHref"><span class="icon fa-envelope">Exit</span></a></li>
							</ul>
						</nav>
						
				</div>
				
				<div class="bottom">

					<!-- Social Icons -->
						<ul class="icons">
							<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
							<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
							<li><a href="#" class="icon fa-github"><span class="label">Github</span></a></li>
							<li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
							<li><a href="#" class="icon fa-envelope"><span class="label">Email</span></a></li>
						</ul>
				
				</div>
			
			</div>

		<!-- Main -->
			<div id="main">

				<!-- Intro -->
					<section id="top" class="one dark cover">
						<div class="container">
                                                    ${title}
						</div>
					</section>
					
				<!-- Portfolio -->
					<div id="portfolio">
                                            <form action="<%= Routes.basicExercisesAct%>" name="formBasicExercises" method="post" onsubmit="putValue()">
                <div name="resolution" id="editor">${resolutionParam}</div>
                <script src="/resources/acebuilds/ace.js">  </script>
                <script src="/resources/acebuilds/ext-statusbar.js"></script>
                <script>
                    var editor = ace.edit("editor");
                    editor.setTheme("ace/theme/eclipse");
                    editor.getSession().setMode("ace/mode/c_cpp");
                </script>
                <p id ="buttonSubmit">
                        <input type="hidden" name="resolution" id="resolution">
                        <input type="submit" value="Enviar"/>
                </p>
        </form>
                                          	</div>

				<!-- About Me -->
				<!-- Contact -->		
			</div>

		<!-- Footer -->
			<div id="footer">
				
				<!-- Copyright -->
					<ul class="copyright">
						<li>&copy; HALYENL</li>
					</ul>
				
			</div>

	</body>
</html>
