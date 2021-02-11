

<!DOCTYPE html>
<html lang="it" dir="ltr">

<head>
   <meta charset="utf-8">
   <title>Ticket</title>
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
   <link rel="stylesheet" href="../assets/css/ticket.css">
</head>

<body>
   <header id="navbar_header">
   </header>

   <script>
      $("#navbar_header").load('../components/navbar-login.html');
   </script>
   
<div class="row">
  <div class="col-lg-2">
      
  </div>
  <div class="col-lg-4">

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

       <button class="dropdown">0 Cambi <img src="../assets/img/kdown.png" width="25px"></button> 
      <div class="dropdown">
         <span class="cambi">0 Cambi <img src="../assets/img/kdown.png" width="25px"></span>
         <div class="dropdown-content">

            <div class="card-fermate">
               <div class="description-travel">
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
            </div>
         </div>
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

       <button class="dropdown">0 Cambi <img src="../assets/img/kdown.png" width="25px"></button> 
      <div class="dropdown">
         <span class="cambi">0 Cambi <img src="../assets/img/kdown.png" width="25px"></span>
         <div class="dropdown-content">

            <div class="card-fermate">
               <div class="description-travel">
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
            </div>
         </div>
      </div>
   </div>

  </div>

  <div class="col-lg-4">


  </div>
  <div class="col-lg-2">

  </div>
</div>



   <footer id="footer">
   </footer>

   <script>
      $("#footer").load('../components/footer.html');
   </script>
</body>

</html>