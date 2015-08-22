<!DOCTYPE html>
<html ng-app="bs">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Bimal Sahay - A Software Engineer</title>

<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
	
<link rel="stylesheet" type="text/css" media="screen" href="/blog/resources/css/bootstrap-theme.css"/>	
<link rel="stylesheet" type="text/css" media="screen" href="/blog/resources/css/bootstrap.css"/>	
<link rel="stylesheet" type="text/css" media="screen" href="/blog/resources/css/modern-business.css" />

<script src="/blog/resources/js/jquery-1.11.3.js"></script>
<script src="/blog/resources/js/bootstrap.js"></script>
<script src="/blog/resources/js/angular.js"></script>
<script src="/blog/resources/js/angular-route.js"></script>
<script src="/blog/resources/js/blogs.js"></script>
<script src="/blog/resources/js/ng-infinite-scroll.js"></script>

    <script>
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    })
    </script>
<body>
	<tiles:insertAttribute name="masthead" />		
 	<tiles:insertAttribute name="main" />
	<tiles:insertAttribute name="footer" />
</body>
</html>