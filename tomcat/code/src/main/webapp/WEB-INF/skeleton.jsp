<%@page import="br.com.assessmentsystem.assessmentsystem.controller.Routes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<!--
	Prologue by HTML5 UP
	html5up.net | @n33co
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>Sistema</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<!--[if lte IE 8]><script src="/resources/css/ie/html5shiv.js"></script><![endif]-->
		<script src="/resources/js/jquery.min.js"></script>
		<script src="/resources/js/jquery.scrolly.min.js"></script>
		<script src="/resources/js/jquery.scrollzer.min.js"></script>
		<script src="/resources/js/skel.min.js"></script>
		<script src="/resources/js/skel-layers.min.js"></script>
		<script src="/resources/js/init.js"></script>
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
							funcionando
							</div>
					</section>
					
				<!-- Portfolio -->
					<section id="portfolio">
					</section>

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
