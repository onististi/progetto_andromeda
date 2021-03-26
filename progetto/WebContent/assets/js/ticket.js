      $('button').click(function (e)
    {                       
    	 if(!$(this).parents().hasClass('open'))
              $('li').removeClass('open');    
    		 else
    		 $(this).parent().addClass('open');
    });
  
  var prima =0;
  var showed = false;
  function showCambi(index,chiave,lista) {
     var listTicket = document.getElementsByClassName("ticket");
     var arrows = document.getElementsByClassName("arrow");
     var list = document.getElementsByClassName("ticket-cambi");

    //  if(prima == 0){
    //      console.log("m")
    //     prima = index;
    //  }else{
    //     console.log("sss")
    //     listTicket[prima].style.borderBottomLeftRadius = "10px";
    //     listTicket[prima].style.borderBottomRightRadius = "10px";
    //     arrows[prima].src = "assets/img/kdown.png";
    //     list[prima].innerHTML = "";
    //     list[prima + 1].style.marginTop = "20px";
    //  }

     if (!showed) {
    	 
        showed = true;
        listTicket[index].style.borderBottomLeftRadius = "0px";
        listTicket[index].style.borderBottomRightRadius = "0px";
        arrows[index].src = "assets/img/kup.png";  
        var s="<br>";
        
        var stringa = JSON.stringify(lista);
        var obj = JSON.parse(stringa);
       //console.log(obj);

        let n_fermate = Object.keys(obj[chiave]).length
        console.log(n_fermate+" k");

        var t = obj[chiave];
        console.log(t);

        for(let i=0;i<n_fermate;i++){                   
            s+='<div class="carta"><a class="fermata">'+t[i].nome+' </a> <a class="tempo">'+t[i].orario+'</a></div></div><br>';
        }
       list[index].innerHTML += s;
       
       let carte = document.getElementsByClassName("carta")[n_fermate-1];
       carte.style.borderBottomLeftRadius="10px";
       carte.style.borderBottomRightRadius="10px";
       list[index + 1].style.marginTop = String((n_fermate*66)+15)+"px";
       
    }else{
        showed = false;
        listTicket[index].style.borderBottomLeftRadius = "10px";
        listTicket[index].style.borderBottomRightRadius = "10px";
        arrows[index].src = "assets/img/kdown.png";
        list[index].innerHTML = "";
        list[index + 1].style.marginTop = "20px";
     }
  }