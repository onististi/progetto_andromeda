<!DOCTYPE html>
<html lang="it" dir="ltr">

<head>
   <meta charset="utf-8">
   <title>Ticket</title>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js" charset="utf-8"></script>
   <link rel="stylesheet" href="../assets/css/ticket.css" >
</head>

<body>
   <header id="navbar_header">
   </header>

   <script>
      $("#navbar_header").load('../components/navbar-login.html');
   </script>

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

   <footer id="footer">
   </footer>

   <script>
      $("#footer").load('../components/footer.html');
   </script>

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
   </script>

</body>
</html>