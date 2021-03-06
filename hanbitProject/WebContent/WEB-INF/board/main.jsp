<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>MAIN</title>

<link rel="stylesheet" href="../include/css/example.css">
<link rel="stylesheet" href="../include/css/font-awesome.min.css">

<style>
/* body {
	-webkit-font-smoothing: antialiased;
	font: normal 15px/1.5 "Helvetica Neue", Helvetica, Arial, sans-serif;
	color: #232525;
	padding-top: 70px;
} */

#slides {
	display: none;
}

#slides .slidesjs-navigation {
	margin-top: 5px;
}

a.slidesjs-next, a.slidesjs-previous, a.slidesjs-play, a.slidesjs-stop {
	background-image: url('../image/btns-next-prev.png');
	background-repeat: no-repeat;
	display: block;
	width: 10px;
	height: 18px;
	overflow: hidden;
	text-indent: -9999px;
	float: left;
	margin-right: 5px;
}

a.slidesjs-next {
	margin-right: 10px;
	background-position: -12px 0;
}

a:hover.slidesjs-next {
	background-position: -12px -18px;
}

a.slidesjs-previous {
	background-position: 0 0;
}

a:hover.slidesjs-previous {
	background-position: 0 -18px;
}

a.slidesjs-play {
	width: 15px;
	background-position: -25px 0;
}

a:hover.slidesjs-play {
	background-position: -25px -18px;
}

a.slidesjs-stop {
	width: 18px;
	background-position: -41px 0;
}

a:hover.slidesjs-stop {
	background-position: -41px -18px;
}

.slidesjs-pagination {
	margin: 7px 0 0;
	float: right;
	margin-right: 100px;
	list-style: none;
}

.slidesjs-pagination li {
	float: left;
	margin: 0 1px;
}

.slidesjs-pagination li a {
	display: block;
	width: 13px;
	height: 0;
	padding-top: 13px;
	background-image: url('../image/pagination.png');
	background-position: 0 0;
	float: left;
	overflow: hidden;
}

.slidesjs-pagination li a.active, .slidesjs-pagination li a:hover.active
	{
	background-position: 0 -13px
}

.slidesjs-pagination li a:hover {
	background-position: 0 -26px
}

#slides a:link, #slides a:visited {
	color: #353535
}

#slides a:hover, #slides a:active {
	color: #353535
}

.navbar {
	overflow: hidden
}
</style>

<style>
#slides {
	display: none;
}

.container {
	margin: 0 auto;
		
}

</style>

</head>

<body>
	<div class="script_background">
		<br />
		<div class="container">
			<div id="slides">
				<img src="<%= request.getContextPath() %>/image/script_slide1.jpg"
					alt="Photo by: Missy S Link: http://www.flickr.com/photos/listenmissy/5087404401/">
				<img src="<%= request.getContextPath() %>/image/script_slide2.jpg"
					alt="Photo by: Daniel Parks Link: http://www.flickr.com/photos/parksdh/5227623068/">
				<img src="<%= request.getContextPath() %>/image/script_slide3.jpg"
					alt="Photo by: Mike Ranweiler Link: http://www.flickr.com/photos/27874907@N04/4833059991/">
				<img src="<%= request.getContextPath() %>/image/script_slide4.jpg"
					alt="Photo by: Stuart SeegerLink: http://www.flickr.com/photos/stuseeger/97577796/">
			</div>
		</div>

		<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
		<script src="/Project/jQuery/jquery.slides.min.js"></script>

		<script>
			$(function() {
				$('#slides').slidesjs({
					width : 500,
					height : 250,
					play : {
						active : true,
						auto : true,
						interval : 4000,
						swap : true
					}
				});
			});
		</script>
		<br /> <br />
</div>
</body>
</html>