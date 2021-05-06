      $('button').click(function (e)
    {                       
    	 if(!$(this).parents().hasClass('open'))
              $('li').removeClass('open');    
    		 else
    		 $(this).parent().addClass('open');
    });
  
  var prima =0;
  var prec =99;
  var showed = false;
  var markers = L.layerGroup();
  function showCambi(index,chiave,lista,map) {
  console.log("index"+index+"prec"+prec);
     var listTicket = document.getElementsByClassName("ticket");
     var arrows = document.getElementsByClassName("arrow");
     var list = document.getElementsByClassName("ticket-cambi");
     markers.clearLayers();
     
     
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

        prec = index;

		var n =0;
       if(n_fermate > 5){
       		n = 5;
       		list[index].style.overflowY= "scroll";
       }else
       		n = n_fermate

        var t = obj[chiave];
       //console.log(t);
  
        for(let i=0;i<n_fermate;i++){
            let orario = t[i].orario.substring(0, 5);        
            s+='<div class="carta"><a class="fermata">'+t[i].nome+' </a> <a class="tempo">'+orario+'</a></div></div><br>';
        
        if(!i)
        	map.panTo(new L.LatLng(t[i].coordinata1, t[i].coordinata2)); //zoom centrale alla partenza
        else
        	var marker= L.marker([t[i].coordinata1,t[i].coordinata2], {icon: redIcon})
        		.bindPopup("<b>"+t[i].comune+"</b><br>"+t[i].nome).openPopup();
        		
        if(!i)    	        	
        	var marker= L.marker([t[i].coordinata1,t[i].coordinata2]) //colori inizio e fine
        		.bindPopup("<b>"+t[i].comune+"</b><br>"+t[i].nome).openPopup();
        else if(i==n_fermate-1)
        	var marker= L.marker([t[i].coordinata1,t[i].coordinata2])
        		.bindPopup("<b>"+t[i].comune+"</b><br>"+t[i].nome).openPopup();
        	
        		markers.addLayer(marker);
        }
       markers.addTo(map);
        
       list[index].innerHTML += s;
       
       let carte = document.getElementsByClassName("carta")[n_fermate-1];
       carte.style.borderBottomLeftRadius="10px";
       carte.style.borderBottomRightRadius="10px";
       
       if(index != listTicket.length-1) //sposta in basso quello dopo
       	list[index + 1].style.marginTop = String((n*66)+20)+"px";
       
    }else{
        
       console.log(prec)
       console.log("index"+index);
       list[prec].innerHTML="";

       if(prec +1 <= listTicket.length -1)//se ci sono 2 bigliettti o 1
       		list[prec+1].style.marginTop ="20px";
		
		listTicket[prec].style.borderBottomLeftRadius = "10px";
        listTicket[prec].style.borderBottomRightRadius = "10px";
       	
       	if(index != listTicket.length-1)
       			list[index + 1].style.marginTop = "20px";
       	
        showed = false;
        listTicket[index].style.borderBottomLeftRadius = "10px";
        listTicket[index].style.borderBottomRightRadius = "10px";
        arrows[index].src = "assets/img/kdown.png";
        arrows[prec].src = "assets/img/kdown.png";
        list[index].innerHTML = "";
        
     }
  }