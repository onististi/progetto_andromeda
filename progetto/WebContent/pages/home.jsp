<!DOCTYPE html>
<html lang="it">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link rel="stylesheet" href="assets/css/home.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="icon" type="image/png" sizes="32x32" href="assets/img/flogo32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="assets/img/flogo16.png">
	<link rel="icon" type="image/png" sizes="32x32" href="assets/js/home.js">
	<title>Home</title>

</head>

<style>s
input[type=time] {
  width: 90px;
}

.tratte_preferite {
border-radius:5px;
text-align: center !important;
  transform: translate(-50%, 95%) !important;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 160px;
  overflow: auto;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 999999999;
}

.invisibile{
  display: none;
}

.dropdown {
  position: relative;
  display: inline-block;
}

.fermata{
	cursor: pointer;
	font-size:16px;
	color:#070D1F;
	margin-top:235px;
}

</style>

<body>

<div class="notify"><span id="notifyType" class=""></span></div>

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
	
	function notifica(t){
		var frase=""	
		if(t == 0)
			frase="i due comuni inseriti non hanno tratte in comune"
		else
			 frase="devi effettuare il login per poter salvare tratte"
		
		  $(".notify").addClass("notificaAttiva");
		  $("#notifyType").addClass("failure");
		  $(".notifichina").append("<p style='color:#ff7979'>"+frase+"</p");
		  
		  setTimeout(function(){
		    $(".notify").removeClass("notificaAttiva");
		    $("#notifyType").removeClass("failure");
		  },2000);
		};
	
		$("#navbar_header").load('components/navbar-ticket.jsp');
		
		var url_string = window.location.href; //window.location.href
		var url = new URL(url_string);
		if(url.searchParams.get("e")==1)
			setTimeout(function(){ notifica(0)},1000);
	</script>
	<div class="background">
		<form action="ticket" method="post">
			<div class="form-box">
					
				<div class="dropdown">
				<% 
				if(session.getAttribute("id_utente")!= null){%>
					<a><img src="assets/img/favourite-star.png" class="dropbtn" id="img_tratte_preferite" style="width:27px; height:27px;"/></a>
				<%}else{%>
					<a><img src="assets/img/not-favourite.png" id="non-favorite" style="width:27px; height:27px;"/></a>
				<%}%>
					<div id="myDropdown" class="tratte_preferite invisibile"></div>
				
				<input class="search-field text-box" placeholder="Comune Partenza" type="text" list="comuni" name="partenza" required id="partenza" ></input>
				<button type="button" class="switch" id="switch">&rlarr;</button>
				<input id="arrivo" class="search-field text-box" placeholder="Comune Arrivo" type="text" list="comuni" name="arrivo" required></input>
				<!--  <input type="date" class="search-field date-time-box"></input> -->
				<input type="time" name="ora" class="search-field hour-time-box" style="width:100px"></input>
				
				<button class="search-field search-btn" type="submit"><img src="assets/img/search.png" alt="Search" width="30px"></button>
			</div>
		</form>
	</div>
	<center>
		<div>
			<h2 class="title">Compagnie supportate</h2>
			<div class="carriers-logos-container">
				<div class="carriers-row">
					<img src="assets/img/logo_vcotrasporti.png" class="brand">
					<img src="assets/img/logo_comazzi.png" class="brand">
					<img src="assets/img/logo-saf.png" class="brand">
				</div>
			</div>
		</div>
	</center>

<div class="notify"><span id="notifyType" class="notifichina"></span></div>

	<footer id="footer">
	</footer>

	<script>
	var i =0;
		$("#footer").load('components/footerT.html');
		
		$('#img_tratte_preferite').click(function(e){
			 $('#myDropdown').html("")
			let url = "tratte_preferite";
			 let u = '<i class="fa fa-trash"></i>'
			$.get(url,
			 function(responseJson){

		           for(i =0;i< Object.keys(responseJson).length;++i){
		        	   let t = responseJson[i];
		        	   
		        	   $('#myDropdown').append("<br><div class='row'><a class='fermata' id="+i+" onclick='io("+i+")';event.preventDefault();'>&nbsp;"+t[0]+"&nbsp;&nbsp-&nbsp;&nbsp;"+t[1]+"&nbsp;<button type='button' class='cestino' onclick='elimina("+i+")'></a> "+u+"</button>&nbsp</div><br>")
		        	   
		           }
		        });
			
			document.getElementById("myDropdown").classList.toggle("invisibile");
			document.getElementById("myDropdown").classList.toggle("compari");
		});
		
		$('#switch').click(function (e){
				let partenza_t = document.getElementById("partenza").value;
				//console.log(document.getElementById("arrivo").value);
				document.getElementById("partenza").value = document.getElementById("arrivo").value;
				document.getElementById("arrivo").value = partenza_t;			
		});

		
		function io(index){
			let row = document.getElementById(index).text
			row = row.substring(1,row.length-1 )

			prima = row.split("-")[0]
			seconda = row.split("-")[1]

			prima = prima.substring(0,prima.length-2)
			seconda = seconda.substring(2,seconda.length)
			
			document.getElementById("partenza").value = prima
			document.getElementById("arrivo").value = seconda 
		}
		
		$("#non-favorite").click(function (e){
			setTimeout(function(){ notifica(1)},100);
		});
		
		
		function elimina(index){
			
			let text = document.getElementsByClassName("fermata")[index].text
			text = text.substring(1,text.length-1)
			par = text.split("-")[0]
			arr= text.split("-")[1]

			par=par.substring(0,par.length-2)
			arr=arr.substring(2,arr.length)
			console.log(par+arr+"t")
			var url ="tratte_preferite"
			$.post(url,{
				 partenza:par,
				 arrivo:arr,
				 message:'elimina'
			     });
		}
		
window.onclick = function(event) {
	  if (!event.target.matches('#img_tratte_preferite')) {
	    var dropdowns = document.getElementsByClassName("tratte_preferite");
	    var ip;
	    for (ip = 0; ip < dropdowns.length; ip++) {
	      var openDropdown = dropdowns[ip];
	      if (openDropdown.classList.contains('compari')) {
	        openDropdown.classList.remove('compari');
	        openDropdown.classList.add('invisibile');
	      }
	    }
	  }
	}
	</script>
</body>

<datalist id="comuni">
	<c:forEach var = "row" items = "${result.rows}">
	 	<option><c:out value = "${row.comune}"/></option>
    </c:forEach>
</datalist>

</html>