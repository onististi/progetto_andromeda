<!DOCTYPE html>
<html lang="it" dir="ltr">

<head>
   <meta charset="utf-8">
   <title>Ticket</title>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js" charset="utf-8"></script>
   <link rel="stylesheet" href="https://unpkg.com/leaflet@1.6.0/dist/leaflet.css" integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ==" crossorigin=""/>
   <script src="https://unpkg.com/leaflet@1.6.0/dist/leaflet.js" integrity="sha512-gZwIG9x3wUXg2hdXF6+rVkLF/0Vi9U8D2Ntg4Ga5I5BZpVkVxlJWbSQtXPSiUTtC0TjtGOmxa1AJPuV0CPthew==" crossorigin=""></script>
   <link rel="stylesheet" href="../assets/css/ticket.css" >
   <link rel="stylesheet" href="../assets/css/navbar.css" >
</head>
<body>
   <object type="text/html" class ="header"data="../components/navbar-ticket.html"></object>

<div class="row">
   <div class="column" >
   <div class="ticket">
      <img class="company-logo" src="../assets/img/logo_vcotrasporti.png" width="100px">
      <p class="company-name">VCO Trasporti</p>

      <div class="description">
         <p class="times" id="time_departure">7:33</p>
         <p class="luoghi" id="partenza">Cannobio</p>

         <div class="divisor">
            <hr class="separator" id="separator_left" noshade>
            <p class="duration">32m</p>
            <hr class="separator" id="separator_right" noshade>
         </div>

         <p class="times" id="time_arrival">8:05</p>
         <p class="luoghi" id="arrivo">Intra</p>
      </div>

      <button class="cambi" onclick="showCambi(0)">0 cambi<img class="arrow" src="../assets/img/kdown.png"
            width="25px"></button>
      <div class="ticket-cambi">
      </div>
   </div>

   <div class="ticket">
      <img class="company-logo" src="../assets/img/logo_vcotrasporti.png" width="100px">
      <p class="company-name">VCO Trasporti</p>

      <div class="description">
         <p class="times" id="time_departure">7:33</p>
         <p class="luoghi" id="partenza">Cannobio</p>

         <div class="divisor">
            <hr class="separator" id="separator_left" noshade>
            <p class="duration">32m</p>
            <hr class="separator" id="separator_right" noshade>
         </div>

         <p class="times" id="time_arrival">8:05</p>
         <p class="luoghi" id="arrivo">Intra</p>
      </div>

      <button class="cambi" onclick="showCambi(1)">0 cambi<img class="arrow" src="../assets/img/kdown.png"
            width="25px"></button>
      <div class="ticket-cambi">
      </div>
   </div>

   <div class="ticket">
      <img class="company-logo" src="../assets/img/logo_vcotrasporti.png" width="100px">
      <p class="company-name">VCO Trasporti</p>

      <div class="description">
         <p class="times" id="time_departure">7:33</p>
         <p class="luoghi" id="partenza">Cannobio</p>

         <div class="divisor">
            <hr class="separator" id="separator_left" noshade>
            <p class="duration">32m</p>
            <hr class="separator" id="separator_right" noshade>
         </div>

         <p class="times" id="time_arrival">8:05</p>
         <p class="luoghi" id="arrivo">Intra</p>
      </div>

      <button class="cambi" onclick="showCambi(2)">0 cambi<img class="arrow" src="../assets/img/kdown.png"
            width="25px"></button>
      <div class="ticket-cambi">
      </div>
   </div>
</div>
   <div class="column">
   <div id="mapid" class="map" ></div></div></div>

<body>
    <footer>
        <center>
            <div class="container">
                <div class="row">
                    <div class="col-6 col-md-auto order-1 order-md-1">
                        <a class="logo-footer">
                            <img src="../assets/img/logo-logo.png" width="75" height="75" alt="Logo BRT - white">
                        </a>
                    </div>
                    <div class="col order-last order-md-2 text-footer">

                        <div class="viewPar BLOBAlignLeft">
                            <p><strong class="userFormat1">PianetaBus</strong> Tutti i diritti riservati
                            </p>
                        </div>
                    </div>
                </div>
        </center>
    </footer>


   <script>
      var showed = false;
      function showCambi(index) {
         var listTicket = document.getElementsByClassName("ticket");
         var arrows = document.getElementsByClassName("arrow");
         var list = document.getElementsByClassName("ticket-cambi");


         if (!showed) {
            showed = true;
            listTicket[index].style.borderBottomLeftRadius = "0px";
            listTicket[index].style.borderBottomRightRadius = "0px";

            arrows[index].src = "../assets/img/kup.png";

            list[index].innerHTML += "<div class='card-fermate' ><div class='description-travel'><p class='times' id='time_departure'>7:33</p><p class='luoghi' id='partenza'>Cannobio</p><div class='divisor'><hr class='separator' id='separator_left' noshade><p class='duration'>32m</p><hr class='separator' id='separator_right' noshade></div><p class='times' id='time_arrival'>8:05</p><p class='luoghi' id='arrivo'>Intra</p></div></div>";
            list[index + 1].style.marginTop = "170px";
         }
         else {
            showed = false;
            listTicket[index].style.borderBottomLeftRadius = "10px";
            listTicket[index].style.borderBottomRightRadius = "10px";

            arrows[index].src = "../assets/img/kdown.png";

            list[index].innerHTML = "";
            list[index + 1].style.marginTop = "20px";
         }
      }


      var mymap = L.map('mapid').setView([45.94, 8,58], 10);
   
      L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
         maxZoom: 18,
         attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
            '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
            'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
         id: 'mapbox/streets-v11',
         tileSize: 512,
         zoomOffset: -1
      }).addTo(mymap);
   
      L.marker([45.968978, 7.96423]).addTo(mymap)
         .bindPopup("<b>Macugnaga(VB)</b><br />Macugnaga").openPopup();
      L.marker([45.94339, 8.58495]).addTo(mymap)
         .bindPopup("<b>Biganzolo(VB)</b><br />Biganzolo").openPopup();
      L.circle([45.968978, 7.96423], 500, {
         color: 'red',
         fillColor: '#f03',
         fillOpacity: 0.5
      }).addTo(mymap).bindPopup("I am a circle.");
   
      L.polygon([
         [45.968978, 7.96423],
         [45.968978, 7.96423],
         [45.968978, 7.96423]
      ]).addTo(mymap).bindPopup("I am a polygon.");
   
   
      var popup = L.popup();
   
      mymap.on('click', onMapClick);
   </script>

</body>
</html>