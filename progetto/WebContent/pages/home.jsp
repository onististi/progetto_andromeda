<!DOCTYPE html>
<html lang="it">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link rel="stylesheet" href="../assets/css/home.css">
	   <link rel="icon" type="image/png" sizes="32x32" href="../assets/img/flogo32.png">
   <link rel="icon" type="image/png" sizes="16x16" href="../assets/img/flogo16.png">
	<title>Home</title>

</head>

<style>s
input[type=time] {
  width: 90px;
}
</style>

<body>

<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>


<sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
         url = "jdbc:mysql://localhost/androme"
         user = "aro"  password = "cavolo22"/>

 <sql:query dataSource = "${snapshot}" var = "result">
            SELECT DISTINCT comune FROM fermata ORDER BY comune ;
</sql:query>

	<header id="navbar_header">
	</header>

	<script>
		$("#navbar_header").load('../components/navbar-home.jsp');
		
		var url_string = window.location.href; //window.location.href
		var url = new URL(url_string);
		var c = url.searchParams.get("e");
		if(c==1)
			alert("i due comuni inseriti non hanno tratte in comune");
	</script>
	<div class="background">
		<form action="../ticket" method="post">
			<div class="form-box">
				
				<input class="search-field text-box" placeholder="Comune Partenza" type="text" list="comuni" name="partenza" required ></input>
				<input class="search-field text-box" placeholder="Comune Arrivo" type="text" list="comuni" name="arrivo" required></input>
				<!--  <input type="date" class="search-field date-time-box"></input> -->
				<input type="time" class="search-field hour-time-box"></input>
				
				<button class="search-field search-btn" type="submit"><img src="../assets/img/search.png" alt="Search" width="30px"></button>
			</div>
		</form>
	</div>
	<center>
		<div>
			<h2 class="title">Compagnie supportate</h2>
			<div class="carriers-logos-container">
				<div class="carriers-row">
					<img src="../assets/img/logo_vcotrasporti.png" class="brand">
					<img src="../assets/img/logo_comazzi.png" class="brand">
					<img src="../assets/img/logo-saf.png" class="brand">
				</div>
			</div>
		</div>
	</center>

	<footer id="footer">
	</footer>

	<script>
		$("#footer").load('../components/footer.html');
	</script>
</body>

<datalist id="comuni">
	<c:forEach var = "row" items = "${result.rows}">
	 	<option><c:out value = "${row.comune}"/></option>
    </c:forEach>
</datalist>

</html>