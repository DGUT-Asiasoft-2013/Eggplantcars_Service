<%@ page language="java"%>

<%@ include file="common/head.jsp"%>
<html>
	<head>
		<title>Member Center</title>
		<link rel="stylesheet" href="/resources/jquery-ui/jquery-ui.css">
		<link rel="stylesheet" href="/resources/site/css/admin.css">
		<script src="/resources/jquery-ui/external/jquery/jquery.js"></script>
		<script src="/resources/jquery-ui/jquery-ui.js"></script>
	</head>

	<body>
		<div class="banner">
			<h1 class="title">Member Center</h1>
		</div>
		
		<input type="text" name="date" id="date">
		<script type="text/javascript">
			$('#date').datepicker();
		</script>
	</body>
</html>