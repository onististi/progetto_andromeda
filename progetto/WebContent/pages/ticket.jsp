<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="com.google.gson.*"%>
<%@page import="beans.TicketBean"%>
<%@page import="classi.TicketSalvatiClass"%>
<%@page import="javax.servlet.http.Cookie"%>
<html lang="it" dir="ltr">
<head>
   <meta charset="utf-8">
   <title>Ticket</title>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js" charset="utf-8"></script>
   <link rel="stylesheet" href="https://unpkg.com/leaflet@1.6.0/dist/leaflet.css" integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ==" crossorigin=""/>
   <script src="https://unpkg.com/leaflet@1.6.0/dist/leaflet.js" integrity="sha512-gZwIG9x3wUXg2hdXF6+rVkLF/0Vi9U8D2Ntg4Ga5I5BZpVkVxlJWbSQtXPSiUTtC0TjtGOmxa1AJPuV0CPthew==" crossorigin=""></script>
   <link rel="icon" type="image/png" sizes="32x32" href="assets/img/flogo32.png">
   <link rel="icon" type="image/png" sizes="16x16" href="assets/img/flogo16.png">
   <link rel="stylesheet" href="assets/css/ticket.css" >
   <link rel="stylesheet" href="assets/css/navbar.css" >
   <script src="assets/js/ticket.js"></script>
   <style>
   li ul {
    display: none;
    border-radius: 10px;
   }

   li.open > ul, li.open > div {
      display: block;
      border-radius: 10px;
   }
   </style>
</head>
<body>

<%!public int calcolaDiff(String t1, String t2) {
	
	String[] t1Arr = t1.split(":");
	int time1 = Integer.parseInt(t1Arr[0]);
	time1 *= 60;
	int time1_2 = Integer.parseInt(t1Arr[1]);
	time1 += time1_2;
	
	String[] t2Arr = t2.split(":");
	int time2 = Integer.parseInt(t2Arr[0]);
	time2 *= 60;
	int time2_2 = Integer.parseInt(t2Arr[1]);
	time2 += time2_2;
	
	return(time2-time1);
}%>

<%!public void funzione(String g){System.out.println("funzione"+g);}%>

   <header id="navbar_header"></header>

	<script>
		$("#navbar_header").load('components/navbar-ticket.jsp');
	</script>
<a id="stellina"><img id="myImg" src="assets/img/not-favourite.png" style="width:35px; height:35px; margin-top:5%; position:absolute; left:2%;"/></a>
<main>

<div class="column" >
<%!HashMap tickets = new HashMap<Integer,ArrayList<TicketBean>>(); %>
<%!int index=0;%>
<%tickets = (HashMap)session.getAttribute("tickets");%>
	
	<script>var lista = <%=new Gson().toJson(tickets)%>;</script>
	
	<% Iterator<Entry<Integer, ArrayList<TicketBean>>> it = tickets.entrySet().iterator();
	index=0;
	while (it.hasNext()) {
		Map.Entry<Integer, ArrayList<TicketBean>> me = (Map.Entry<Integer, ArrayList<TicketBean>>) it.next();
		
		ArrayList<TicketBean> fermate = (ArrayList<TicketBean>)me.getValue();%>
      <div class="ticket" id="<%=me.getKey()%>">
      	<img class="company-logo" src="assets/img/logo_vcotrasporti.png" width="100px">
      		<p class="company-name"><%=fermate.get(0).getNome_compagnia() %></p>
				<div class="description">
        		 <p class="times" id="time_departure"><%=fermate.get(0).getOrarioR()%></p>
         		 <p class="luoghi" id="partenza"><%=fermate.get(0).getComune()%></p>
         		 
         		 <div class="divisor">
		            <hr class="separator" id="separator_left" noshade>
		            <p class="duration"><%=calcolaDiff(fermate.get(0).getOrario(),fermate.get(fermate.size()-1).getOrario())+"m"%></p>
		            <hr class="separator" id="separator_right" noshade>
        		 </div>

		         <p class="times" id="time_arrival"><%=fermate.get(fermate.size()-1).getOrarioR()%></p>
		         <p class="luoghi" id="arrivo"><%=fermate.get(fermate.size()-1).getComune()%></p>     
    		   </div>
      
      <button class="cambi" onclick="showCambi(<%=index%>,<%=me.getKey() %>,lista,mymap)">0 cambi<img class="arrow" src="assets/img/kdown.png"
            width="25px"></button><div class="ticket-cambi"></div>
   	</div>
   <%index++;}%>
  </div>

   <div class="column">
   	 <div id="mapid" class="map" ></div>
   </div>
</main>
<div class="div1 div2"></div>
<div class="notify"><span id="notifyType" class="notifichina"></span></div>
	<script>

	<%!TicketSalvatiClass Salva = new TicketSalvatiClass();%>
	<%!String partenza;%>
	<%!String arrivo;%>
	<%
	partenza = request.getAttribute("partenza").toString();
	arrivo = request.getAttribute("arrivo").toString();
	    
	if(session.getAttribute("username")!=null){
		boolean check_salva = Salva.check( (int)(session.getAttribute("id_utente")), partenza, arrivo);
		
		if(!check_salva){%>
		
		var url = "tratte_preferite";
		$('#stellina').click(function (e){

				$.post(url,{
				 partenza:'<%=partenza%>',
				 arrivo:'<%=arrivo%>',
				 message:'salva'
			     });
				
				document.getElementById("myImg").src = "assets/img/favourite-star.png"
		   });
		
		<%}else{%>
		document.getElementById("myImg").src = "assets/img/favourite-star.png"
		$('#stellina').click(function (e){ 
			
			var url ="tratte_preferite"
				$.post(url,{
					 partenza:"<%=partenza%>",
					 arrivo:"<%=arrivo%>",
					 message:'elimina'
				});
			
			document.getElementById("myImg").src = "assets/img/not-favourite.png"
			setTimeout(function(){ notifica(1)},90);
		});
		<%}%>
	<%}else{%>;
		
		$('#stellina').click(function (e){ 
			setTimeout(function(){ notifica(0)},90);
		});
	<%}%>
		
      var mymap = L.map('mapid').setView([45.94, 8,58], 10);
   
      L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
         maxZoom: 20,
         attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
            '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
            'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
         id: 'mapbox/streets-v11',
         tileSize: 512,
         zoomOffset: -1
      }).addTo(mymap);
      
      var redIcon = new L.Icon({
    	  iconUrl: 'https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-red.png',
    	  shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
    	  iconSize: [25, 41],
    	  iconAnchor: [12, 41],
    	  popupAnchor: [1, -34],
    	  shadowSize: [41, 41]
    	});
   
      var popup = L.popup();
      
  	function notifica(tipo){
		var frase;
		if(!tipo)
			 frase = "Devi effettuare il login per poter salvare tratte"
		else
			 frase = "Hai eliminato questa tratta"
			 
		  $(".notify").addClass("notificaAttiva");
		  $("#notifyType").addClass("failure");
		  $(".notifichina").append("<p id='pn' style='color:#ff7979'></p>");
		  $("#pn").append(frase);
		  
		  setTimeout(function(){
		    $(".notify").removeClass("notificaAttiva");
		    $("#notifyType").removeClass("failure");
		  },2000);
		};
		
		$("#footer").load('components/footerT.html');
   </script>

</body>
</html>